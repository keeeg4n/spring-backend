package dev.keeg4n.springboot_webflux.model.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collation = "products")
public record Product(
        @Id
        Long id,

        String name,

        Integer quantity,

        Double price

) {
}
