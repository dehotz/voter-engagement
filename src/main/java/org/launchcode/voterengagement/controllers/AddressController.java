package org.launchcode.voterengagement.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.voterengagement.models.data.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "address")
public class AddressController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayEnterAddressForm(Model model) {

        model.addAttribute("title", "Address Form");
        model.addAttribute(new Address());
        return "address/entry";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String processEnterAddressForm(@ModelAttribute @Valid Address newAddress, Errors errors, Model model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Address Form");
            return "address/entry";
        }

        String requestString = newAddress.getAddress1() + " " +
                            newAddress.getAddress2() + " " +
                            newAddress.getCity() + " " +
                            newAddress.getState() + " " +
                            newAddress.getZipCode();

        Address address = new Address(newAddress.getAddress1(),
                                    newAddress.getAddress2(),
                                    newAddress.getCity(),
                                    newAddress.getState(),
                                    newAddress.getZipCode());

        String googleUrl = "https://www.googleapis.com/civicinfo/v2/voterinfo?";
        String apiKey = "AIzaSyDzr5q6VWKBmj9LopsS5dEDUZ7nngRQ2B0";

        //String googleUrlApi = googleUrl + apiKey;

        Map<String, String> parameters = new HashMap<>();
        parameters.put("address", requestString);

        String cookieName = "address";
        String cookieValue = ParameterStringBuilder.getCookiesString(parameters);

        CookieBuilder.MethodSetCookie(request, response, cookieName, cookieValue);

        parameters.put("key", apiKey);

        String googleUrlParams = googleUrl + ParameterStringBuilder.getParamsString(parameters);

        System.out.println(googleUrlParams);

        URL url = new URL(googleUrlParams);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestProperty("Acceptcharset", "en-us");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("charset", "EN-US");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        //con.setDoOutput(true);
        //con.setDoInput(true);
        con.setRequestMethod("GET");

        //DataOutputStream out = new DataOutputStream(con.getOutputStream());
        //out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        //out.flush();
        //out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        System.out.println(content.toString());


        model.addAttribute("title", "Current Address");
        model.addAttribute("currentAddress", address);
        model.addAttribute("inputLine", inputLine);
        model.addAttribute("requestString", requestString);
        return "address/current-address";

    }

}
