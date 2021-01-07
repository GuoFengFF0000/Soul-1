package com.qf.utils;
import javax.servlet.http.Cookie;


public class CookieUtils {


    public String getToken(Cookie[] cookies){
            String token ="";
        if (cookies==null||cookies.length==0){
            return null;
        }
        for (Cookie cook:cookies
             ) {
            String name = cook.getName();
            if(name.equals("token")){
                token=cook.getValue();
                return token;
            }
        }
        return null;
    }
}
