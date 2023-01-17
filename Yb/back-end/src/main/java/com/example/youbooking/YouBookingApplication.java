package com.example.youbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class YouBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouBookingApplication.class, args);
    }

}
