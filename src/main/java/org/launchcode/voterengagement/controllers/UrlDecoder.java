package org.launchcode.voterengagement.controllers;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UrlDecoder {

    static String CookieDecoder (String encodedCookie) throws Exception {

        return URLDecoder.decode(encodedCookie, StandardCharsets.UTF_8.toString());

    }

}
