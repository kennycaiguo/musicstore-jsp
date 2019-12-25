package com.zhonghuasheng.musicstore.dao.impl;

import com.zhonghuasheng.musicstore.common.JDBCUtils;
import com.zhonghuasheng.musicstore.dao.MusicDAO;
import com.zhonghuasheng.musicstore.model.Music;
import com.zhonghuasheng.musicstore.model.Pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MusicDAOImpl extends AbstractBaseDAOImpl<Music> implements MusicDAO {

    private static final String CREATE_MUSIC = "INSERT INTO music (uuid, title, artist_uuid, publish_date, publish_company, create_time, last_modified_time, last_modified_by, deleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?, 0)";
    private static final String FIND_MUSICS = "SELECT * FROM music WHERE title LIKE ? LIMIT ? OFFSET ?;";
    private static final String SELECT_COUNT = "SELECT COUNT(1) FROM music";

    @Override
    public Music create(Music music) {
        Connection connection = JDBCUtils.getConnection();
        boolean result = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MUSIC);
            preparedStatement.setString(1, String.valueOf(music.getUuid()));
            preparedStatement.setString(2, music.getTitle());
            preparedStatement.setString(3, String.valueOf(music.getArtistUuid()));
            preparedStatement.setTimestamp(4, music.getPublishDate());
            preparedStatement.setString(5, music.getPublishCompany());
            preparedStatement.setTimestamp(6, music.getCreateTime());
            preparedStatement.setTimestamp(7, music.getLastModifiedTime());
            preparedStatement.setString(8, music.getLastModifiedBy());
            result = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == true ? music : null;
    }

    @Override
    public int count() {
        return count(SELECT_COUNT);
    }

    @Override
    public List<Music> find(Pagination pagination) {
        Connection connection = JDBCUtils.getConnection();
        List<Music> musics = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_MUSICS);
            preparedStatement.setString(1, pagination.getKey());
            preparedStatement.setInt(2, pagination.getPageSize());
            preparedStatement.setInt(3, (pagination.getCurrentPage() - 1) * pagination.getPageSize());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Music music = new Music();
                music.setUuid(UUID.fromString(resultSet.getString("uuid")));
                music.setTitle(resultSet.getString("title"));
                music.setCreateTime(resultSet.getTimestamp("create_time"));
                music.setDeleted(resultSet.getBoolean("deleted"));
                music.setLastModifiedBy(resultSet.getString("last_modified_by"));
                music.setLastModifiedTime(resultSet.getTimestamp("last_modified_time"));
                music.setPublishCompany(resultSet.getString("publish_company"));
                music.setPublishDate(resultSet.getTimestamp("publish_date"));
                musics.add(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return musics;
    }
}
