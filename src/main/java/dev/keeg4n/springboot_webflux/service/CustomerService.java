package dev.keeg4n.springboot_webflux.service;

import dev.keeg4n.springboot_webflux.dao.CustomerDao;
import dev.keeg4n.springboot_webflux.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("(Non-Reactive) Total execution time :" + (end-start));
        return customers;
    }

    public Flux<Customer> loadAllCustomerStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customerFlux = customerDao.getCustomerFlux();
        long end = System.currentTimeMillis();
        System.out.println("(Reactive) Total execution time : " + (end - start));
        return customerFlux;
    }

}
