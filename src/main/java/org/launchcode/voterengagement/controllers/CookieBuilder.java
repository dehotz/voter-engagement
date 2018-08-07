package org.launchcode.voterengagement.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieBuilder {

    static void MethodSetCookie(HttpServletRequest request, HttpServletResponse response, String name, String value){
        final String cookieName = name;
        final String cookieValue = value;
        final Boolean useSecureCookie = false;
        final int expiryTime = 60 * 60 * 24;
        final String cookiePath = "/";

        Cookie cookie = new Cookie(cookieName, cookieValue);

        cookie.setSecure(useSecureCookie);  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL

        cookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.

        cookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories

        response.addCookie(cookie);
    }


}
