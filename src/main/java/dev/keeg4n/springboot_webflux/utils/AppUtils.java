package dev.keeg4n.springboot_webflux.utils;


import dev.keeg4n.springboot_webflux.model.entity.Product;
import dev.keeg4n.springboot_webflux.model.request.ProductRequest;
import dev.keeg4n.springboot_webflux.model.response.ProductResponse;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductResponse entityToResponse(Product product) {
        ProductResponse productResponse = ProductResponse.builder().build();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    public static Product requestToEntity(ProductRequest request) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(request, product);
        return product;
    }

}
