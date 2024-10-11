package dev.keeg4n.springboot_webflux.service;

import dev.keeg4n.springboot_webflux.model.entity.Product;
import dev.keeg4n.springboot_webflux.model.request.ProductRequest;
import dev.keeg4n.springboot_webflux.model.response.ProductResponse;
import dev.keeg4n.springboot_webflux.repository.ProductRepository;
import dev.keeg4n.springboot_webflux.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<ProductResponse> createProduct(Mono<ProductRequest> newProduct) {
        return newProduct
                .map(AppUtils::requestToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToResponse);
    }

    public Flux<ProductResponse> getAllProducts() {
        return productRepository.findAll().map(AppUtils::entityToResponse);
    }

    public Mono<ProductResponse> getProductById(Long productId) {
        return productRepository.findById(productId).map(AppUtils::entityToResponse);
    }

    public Flux<ProductResponse> getProductInRange(Double min, Double max) {
        return productRepository.findByPriceBetween(Range.of(min, max)).map(AppUtils::entityToResponse);
    }

    public void deleteProduct(Long productId) {
        productRepository.findById(productId).flatMap(productRepository::delete);
    }
}
