package dev.keeeg4n.spring_graphql.service;

import dev.keeeg4n.spring_graphql.model.Player;
import dev.keeeg4n.spring_graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlayerService {

    public List<Player> players = new ArrayList<>();

    AtomicLong id = new AtomicLong(0);

    public List<Player> findAll() {
        return players;
    }

    public Player findOne(Long playerId) {
        return players.stream()
                .filter(player -> player.id().equals(playerId))
                .findFirst()
                .orElseThrow();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Long playerId) {
        Player remove = players.stream()
                .filter(player -> player.id().equals(playerId))
                .findFirst()
                .orElseThrow();
        players.remove(remove);
        return remove;
    }

    public Player update(Long playerId, String name, Team team) {
        Player updatedPlayer = new Player(playerId, name, team);

        Player player = players.stream()
                .filter(p -> p.id().equals(playerId))
                .findFirst()
                .orElseThrow();

        int index = players.indexOf(player);

        players.set(index, updatedPlayer);

        return updatedPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Jasprit Bumarah", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Rishabh Pant", Team.GT));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.DC));
    }

}
