package com.scex.testapp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
public class TestAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}


