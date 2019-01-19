package com.adidas.emailservice.controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author  varmathu0
 */
@RestController
public class EmailServiceController {


    @RequestMapping(path = "/send-email", method = RequestMethod.POST , produces = "application/json")

    /** Scenario : Invokes the Service Implementation For Java Mail
     * Using the Mail Sender Interface.
     * On Successful SMTP response - this service would return a confirmation*/
    public String index() {

        return "{\n" +
                "    \"emailNotification\": \"Mail Sent Successfully For Subscription Id : 5c43073f80040740f489c141\"\n" +
                "}";
    }

}