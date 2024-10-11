package dev.keeg4n.springboot_webflux.model.response;

import lombok.Builder;

@Builder
public record ProductResponse(
        Long id,
        String name,
        Integer quantity,
        Double price
) {
}
