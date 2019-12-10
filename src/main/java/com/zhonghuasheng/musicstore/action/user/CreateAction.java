package com.zhonghuasheng.musicstore.action.user;

import com.zhonghuasheng.musicstore.common.Constants;
import com.zhonghuasheng.musicstore.model.User;
import com.zhonghuasheng.musicstore.service.UserService;
import com.zhonghuasheng.musicstore.service.impl.UserServiceImpl;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

import static com.zhonghuasheng.musicstore.action.user.RegisterAction.validateParameters;

@WebServlet(urlPatterns = "/admin/user/create")
@MultipartConfig
public class CreateAction extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/html/user/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();

        if (!validateParameters(request, response, user)) {
            request.setAttribute("user", user);
            doGet(request, response);
            // 不加return的话后面的代码还会继续执行
            return;
        }

        if (userService.isEmailExisted(user.getEmail())) {
            request.setAttribute("user", user);
            request.setAttribute("msg-email", Constants.EMAIL_EXISTED);
            doGet(request, response);
            return;
        } else {
            User result = userService.signUp(user);

            if (result != null) {
                response.sendRedirect(request.getContextPath() + "/admin/user/list");
            } else {
                doGet(request, response);
            }
        }
    }
}