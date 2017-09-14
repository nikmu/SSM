package com.ssm.demo.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.ssm.demo.model.TokenModel;

public class StatelessAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // 从 request header中获取当前 username token
    	HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-Token");
        String username = req.getHeader("username");
        
        TokenModel tokenModel = new TokenModel(username, token);
        try {
            //5、委托给Realm进行认证
            getSubject(request, response).login(tokenModel);
        } catch (Exception e) {
//            e.printStackTrace();
            onLoginFail(response); //6、认证失败
            return false;
        }
        return true;
    }

    //认证失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("auth error");
    }
}
