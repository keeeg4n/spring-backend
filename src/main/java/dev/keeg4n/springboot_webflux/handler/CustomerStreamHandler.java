package dev.keeg4n.springboot_webflux.handler;

import dev.keeg4n.springboot_webflux.dao.CustomerDao;
import dev.keeg4n.springboot_webflux.dto.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    private final CustomerDao customerDao;

    public CustomerStreamHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> getCustomerStream(ServerRequest serverRequest) {
        Flux<Customer> customerList = customerDao.getCustomerFlux();
        return ServerResponse.ok().body(customerList ,Customer.class);
    }


}
