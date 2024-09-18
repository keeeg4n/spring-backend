package dev.keeeg4n.spring_graphql.model;

public record Player(
        Long id,
        String name,
        Team team
) {
}
