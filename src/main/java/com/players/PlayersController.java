package com.players;

import com.players.exceptions.NoSuchPlayerException;
import com.players.repository.moel.Player;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayersController {

	private final JpaRepository<Player, String> playersRepository;

    public PlayersController(JpaRepository<Player, String> playersRepository) {
        this.playersRepository = playersRepository;
    }

    @GetMapping("/api/players")
	public List<Player> getPlayers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
		return playersRepository.findAll(PageRequest.of(pageNumber, pageSize))
				.toList();
	}

	@GetMapping("/api/players/{playerId}")
	public Player getPlayer(@PathVariable(value = "playerId") String playerId) {
		return playersRepository.findById(playerId)
				.orElseThrow(() -> new NoSuchPlayerException("No such player: " + playerId));
	}
}
