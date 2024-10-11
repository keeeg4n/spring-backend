package dev.keeg4n.springboot_webflux.repository;

import dev.keeg4n.springboot_webflux.model.entity.Product;
import org.apache.commons.lang3.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Long> {
    Flux<Product> findByPriceBetween(Range<Double> priceRange);
}
