package net.gpu.grouterbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GrouterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrouterBackendApplication.class, args);
    }

}
