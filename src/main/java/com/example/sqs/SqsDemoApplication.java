package com.example.sqs;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqsDemoApplication {
  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SqsDemoApplication.class);
    springApplication.setBannerMode(Banner.Mode.OFF);
    springApplication.run(args);
  }
}