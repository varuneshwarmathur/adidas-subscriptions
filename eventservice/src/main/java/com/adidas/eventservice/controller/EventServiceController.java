
package com.adidas.eventservice.controller;

import org.springframework.web.bind.annotation.*;


/**
 * @author  varmathu0
 */
@RestController
public class EventServiceController {

@ResponseBody
    @RequestMapping(path = "/event", method = RequestMethod.POST , produces = "application/json")

    /** Invokes the Service Implementation which then publishes
     * messages to Downstream Kafka Topics
     * and a Consumer Service Implementation
     * then polls the queue to save the events in an auditing system  */
    public String index() {


        return "{\n" +
                "    \"eventNotification\": \"Message Queued - RequestID : 2119\"\n" +
                "}";
    }

}
