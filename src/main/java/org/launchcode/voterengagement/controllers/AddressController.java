package org.launchcode.voterengagement.controllers;

import org.launchcode.voterengagement.models.data.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public String processEnterAddressForm(@ModelAttribute @Valid Address newAddress, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Address Form");
            return "address/entry";
        }

        Address address = new Address(newAddress.getAddress1(),
                                    newAddress.getAddress2(),
                                    newAddress.getCity(),
                                    newAddress.getState(),
                                    newAddress.getZipCode());

        model.addAttribute("title", "Current Address");
        model.addAttribute("currentAddress", address);
        return "address/current-address";

    }

}
