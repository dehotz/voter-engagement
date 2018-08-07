package org.launchcode.voterengagement.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.http.Cookie;

public class CookieReader {

    static String MethodReadCookie (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String clientAddressCookie = null;
        for(Cookie cookie : cookies){
            if("address".equals(cookie.getName())){
                clientAddressCookie = cookie.getValue();
            }
        } return clientAddressCookie;
    }

}
