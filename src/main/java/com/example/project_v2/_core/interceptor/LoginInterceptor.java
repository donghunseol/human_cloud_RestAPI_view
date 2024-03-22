package com.example.project_v2._core.interceptor;

import com.example.project_v2._core.errors.exception.Exception401;
import com.example.project_v2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null){
            throw new Exception401("로그인 하셔야 해요");
        }
        return true;
    }
}

