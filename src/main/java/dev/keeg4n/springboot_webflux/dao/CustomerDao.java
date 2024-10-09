package dev.keeg4n.springboot_webflux.dao;

import dev.keeg4n.springboot_webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleep)
                .peek(i -> System.out.println("(Non-Reactive) Process count " + i))
                .mapToObj(i -> new Customer(i, "Customer " + i))
                .toList();
    }

    public Flux<Customer> getCustomerFlux() {
        return Flux.range(0, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("(Reactive) Processing count " + i))
                .map(i -> new Customer(i, "Customer " + i));
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted");
        }
    }

}
