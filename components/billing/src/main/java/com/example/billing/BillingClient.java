package com.example.billing;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class BillingClient {
    private String serviceUri;
    private RestTemplate template;

    public BillingClient(String service, RestTemplate template) {
        this.serviceUri = service;
        this.template = template;
    }

    public void billUser(String userId, int monthlyPayment) {
        String opUri = serviceUri + "/reoccurringPayment";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("---> Calling service Url" + opUri);

        Response resp = template.postForObject(opUri, new HttpEntity(String.valueOf(monthlyPayment), headers), Response.class);

    }






    public static class Response {
        List<String> errors = new ArrayList<>();

        public Response error(String error) {
            errors.add(error);
            return this;
        }

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }
    }
}
