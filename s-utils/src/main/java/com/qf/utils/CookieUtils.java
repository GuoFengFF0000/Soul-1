package com.qf.utils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
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
