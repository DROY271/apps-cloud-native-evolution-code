package com.example.billing;

import com.example.payments.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BillingController {

    @Autowired
    private Gateway gateway;

    @PostMapping("/reoccurringPayment")
    public ResponseEntity<Response> createRecurringPayment(@RequestBody int monthlyAmount) {
        if (gateway.createReocurringPayment(monthlyAmount)) {
            return new ResponseEntity(new Response(), new HttpHeaders(), HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(new Response().error("The payment gateway did not accept the request"));
        }
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
