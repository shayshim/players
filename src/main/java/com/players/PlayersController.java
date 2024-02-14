package com.players;

import com.players.exceptions.NoSuchPlayerException;
import com.players.repository.SimplePlayersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PlayersController {

	private final SimplePlayersRepository playersRepository;

    public PlayersController(SimplePlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @GetMapping("/api/players")
	public List<Map<?, ?>> getPlayers(
			@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "10") int limit) {
		return playersRepository.findAll(offset, limit);
	}

	@GetMapping("/api/players/{playerId}")
	public Map<?, ?> getPlayer(@PathVariable(value = "playerId") String playerId) {
		return playersRepository.findById(playerId)
				.orElseThrow(() -> new NoSuchPlayerException("No such player: " + playerId));
	}
}
