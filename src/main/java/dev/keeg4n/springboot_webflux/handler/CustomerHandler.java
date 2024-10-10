package dev.keeg4n.springboot_webflux.handler;

import dev.keeg4n.springboot_webflux.dao.CustomerDao;
import dev.keeg4n.springboot_webflux.dto.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private final CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> getCustomer(ServerRequest serverRequest) {
        Flux<Customer> customerFlux = customerDao.getCustomerList();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> getCustomerFromInput(ServerRequest request) {
        Integer customerId = Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> customer = customerDao.getCustomerList().filter(c -> c.getId().equals(customerId)).single();
        return ServerResponse.ok().body(customer, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> newCustomer = request.bodyToMono(Customer.class);
        return ServerResponse.ok().body(newCustomer, Customer.class);
    }

}
