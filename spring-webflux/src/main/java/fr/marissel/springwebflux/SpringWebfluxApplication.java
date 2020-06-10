package fr.marissel.springwebflux;

import fr.marissel.springwebflux.client.RegistrationWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);

        final RegistrationWebClient registrationWebClient = new RegistrationWebClient();
        registrationWebClient.consume();
    }

}
