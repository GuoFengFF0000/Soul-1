package com.qf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.qf.pojo.resp.BaseResp;
import com.qf.utils.CookieUtils;
import com.qf.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;


public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //声明返回值
        BaseResp baseResp = new BaseResp();
        //从request中获取token
        Cookie[] cookies = request.getCookies();
        if (cookies==null||cookies.length==0){
            response.setContentType("application/json;charset=utf-8");
            baseResp.setMessage("未登录");
            baseResp.setCode(20001);
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.toJSON(baseResp));
            return false;
        }
        CookieUtils cookieUtils = new CookieUtils();
        String token = cookieUtils.getToken(cookies);
        //使用jwt进行解密
        JWTUtils jwtUtils = new JWTUtils();
        Map verify = jwtUtils.Verify(token);

        if (verify==null||verify.get("username")==null){
            response.setContentType("application/json;charset=utf-8");
            baseResp.setMessage("登录失效，重新登录");
            baseResp.setCode(20001);
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.toJSON(baseResp));
            return false;
        }
       return true;
    }
}
