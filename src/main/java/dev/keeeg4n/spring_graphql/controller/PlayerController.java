package dev.keeeg4n.spring_graphql.controller;

import dev.keeeg4n.spring_graphql.model.Player;
import dev.keeeg4n.spring_graphql.model.Team;
import dev.keeeg4n.spring_graphql.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Player findOne(@Argument Long id) {
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String name, @Argument Team team) {
        return playerService.create(name, team);
    }

    @MutationMapping
    public Player updatePlayer(@Argument Long id, @Argument String name, @Argument Team team) {
        return playerService.update(id, name, team);
    }

    @MutationMapping
    public Player deletePlayer(@Argument Long id) {
        return playerService.delete(id);
    }
}
