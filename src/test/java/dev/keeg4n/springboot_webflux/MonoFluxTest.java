package dev.keeg4n.springboot_webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        // to initialize mono we use Mono.just(any data type)
        Mono<String> monoString = Mono.just("Keegan");
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testFlux() {
        Flux<String> fluxStrings = Flux.just("Keegan", "P", "Russ").log();
        fluxStrings.subscribe(System.out::println);
    }

}
