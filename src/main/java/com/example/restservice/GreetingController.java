package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

	private final PlayersProvider playersProvider;

    public GreetingController(PlayersProvider playersProvider) {
        this.playersProvider = playersProvider;
    }

    @GetMapping("/api/players")
	public List<Map<?, ?>> getPlayers() {
		return playersProvider.getAllPlayers();
	}

	@GetMapping("/api/players/{playerId}")
	public Map<?, ?> getPlayer(@PathVariable(value = "playerId") String playerId) {
		return playersProvider.getPlayer(playerId);
	}
}
