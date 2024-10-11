package dev.keeg4n.springboot_webflux.model.request;

import lombok.Builder;

@Builder
public record ProductRequest(
        String name,
        Integer quantity,
        Double price
) {
}
