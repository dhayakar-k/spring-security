package com.springsecurity.employeemanagement.consumers;

import com.springsecurity.employeemanagement.models.Candidate;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqListener {

    public void listen(byte[] message) {
        String msg = new String(message);
        log.info("Received payLoad "+ msg);

        // Consume and convert the message to Organization class
        /*Organization organization = new Gson().fromJson(msg, Organization.class);
        log.info("Received a newly created organization "+organization.toString());*/

        // Consume and convert the message to Candidate class
        Candidate candidatePayLoad = new Gson().fromJson(msg, Candidate.class);
        log.info("Received pay Load from a queue is "+candidatePayLoad.toString());
    }
}
