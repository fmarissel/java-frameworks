package fr.marissel.springwebflux.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.BaseStream;

@Slf4j
@EnableR2dbcRepositories
@Configuration
public class R2dbcConfiguration {

    @Bean
    public ApplicationRunner seeder(final DatabaseClient client) {
        return args -> getSql("db/schema.sql")
                .flatMap(sql -> executeSql(client, sql))
                .then(getSql("db/data.sql"))
                .flatMap(sql -> executeSql(client, sql))
                .subscribe(count -> log.info("Schema created"));
    }

    private Mono<String> getSql(final String pathStr) throws URISyntaxException {
        final Path path = Paths.get(ClassLoader.getSystemResource(pathStr).toURI());
        return Flux
                .using(() -> Files.lines(path), Flux::fromStream, BaseStream::close)
                .reduce((line1, line2) -> line1 + "\n" + line2);
    }

    private Mono<Integer> executeSql(final DatabaseClient client, final String sql) {
        return client.execute(sql).fetch().rowsUpdated();
    }
}
