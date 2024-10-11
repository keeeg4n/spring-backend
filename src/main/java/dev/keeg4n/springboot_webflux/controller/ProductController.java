package dev.keeg4n.springboot_webflux.controller;

import dev.keeg4n.springboot_webflux.model.request.ProductRequest;
import dev.keeg4n.springboot_webflux.model.response.ProductResponse;
import dev.keeg4n.springboot_webflux.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Mono<ProductResponse> createProduct(@RequestBody Mono<ProductRequest> request) {
        return productService.createProduct(request);
    }

    @GetMapping("all")
    public Flux<ProductResponse> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("range")
    public Flux<ProductResponse> getProductsInRange(
            @RequestParam(name = "min") Double min, @RequestParam(name = "max") Double max
    ) {
        return productService.getProductInRange(min, max);
    }

    @GetMapping("{productId}")
    public Mono<ProductResponse> getProduct(@PathVariable(name = "productId") Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("{productId}")
    public String deleteProduct(@PathVariable(name = "productId") Long productId) {
        productService.deleteProduct(productId);
        return "Product Deleted";
    }

}
