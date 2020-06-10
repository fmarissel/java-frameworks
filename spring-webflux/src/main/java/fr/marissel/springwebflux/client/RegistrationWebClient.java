package fr.marissel.springwebflux.client;

import fr.marissel.springwebflux.domain.Registration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class RegistrationWebClient {

    private final WebClient client = WebClient.create("http://localhost:8080");

    public void consume() {

        final Flux<Registration> registrationFlux = client.get()
                .uri("/registration")
                .retrieve()
                .bodyToFlux(Registration.class);

        registrationFlux.subscribe(System.out::println);
    }
}
