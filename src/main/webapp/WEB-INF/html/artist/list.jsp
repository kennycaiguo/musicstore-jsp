<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
String baseP = request.getContextPath();
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Artist</title>
    <link rel="shortcut icon" href="<%=baseP%>/static/image/favicon.ico">
  </head>
  <body>
    <%@ include file="/WEB-INF/html/admin/header.jsp" %>
    <div class="content float-left">
      <div class="panel">
        <div class="row ml-0 mr-0">
          <div class="float-left col-sm-3">
            <h4>艺术家</h4>
          </div>
          <div class="col-sm-8">
            <div class="float-right">
              <div class="app-search">
                <input class="app-search_input" type="search" placeholder="搜索">
                <button class="app-search_button">
                  <i class="fa fa-search"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="action col-sm-1">
            <div class="float-right">
              <a class="btn btn-sm btn-success" href="<%=basePath%>/admin/artist/create">创建</a>
            </div>
          </div>
        </div>

        <div class="row ml-0 mr-0">
           <div class="col-lg-12">
             <div class="line-head"></div>
           </div>
        </div>

        <div class="row ml-0 mr-0">
          <div id="page-content" class="col-lg-12">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Birthday</th>
                  <th>Region</th>
                  <th>Is Deleted?</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${artists}" var="artist">
                    <tr>
                      <td>${artist.name}</td>
                      <td>${artist.birthday}</td>
                      <td>${artist.region}</td>
                      <td>${artist.deleted}</td>
                      <td>
                        <a class="btn btn-sm btn-secondary" href="<%=basePath%>/admin/artist/edit?uuid=${artist.uuid}">修改</a>
                        <button type="button" class="btn btn-sm btn-danger" onclick="showDeleteModal('${artist.uuid}')">删除</button>
                      </td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>

      <!-- Modal -->
      <div class="modal fade" id="deleteModal" role="dialog">
        <div class="modal-dialog">
          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">删除</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
              <p>是否要删除这条记录？</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary btn-sm btn-yes" data-uuid="" data-dismiss="modal" onclick="deleteArtist()">是</button>
              <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">否</button>
            </div>
          </div>
        </div>
      </div>
      </div>
      <%@include file="/WEB-INF/html/admin/footer.jsp" %>
      <script src="<%=basePath%>/static/bootstrap/js/bootstrap.min.js"></script>
      <script src="<%=basePath%>/static/js/admin/admin-management.js"></script>
      <script src="<%=basePath%>/static/js/artist/list.js"></script>
    </div>
  </body>
  <script>
      $(document).ready(function() {
          $('.menu li').find('.menu-item-active').removeClass('menu-item-active');
          $('.menu li').find('.menu-artist').addClass('menu-item-active');
      });
  </script>
</html>