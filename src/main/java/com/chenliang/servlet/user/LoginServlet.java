package com.chenliang.servlet.user;

import com.chenliang.pojo.User;
import com.chenliang.service.user.UserService;
import com.chenliang.service.user.UserServiceImp;
import com.chenliang.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    UserService userService;
    public LoginServlet() {
        userService = new UserServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        User user = userService.Login(userCode, userPassword);

        // 查到了用户名和密码都匹配的user，将该user存储到session中
        if (user != null){
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }

        // 登陆失败，转发请求到Login页面，并携带一些错误提示信息
        else {
            req.setAttribute("error","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
