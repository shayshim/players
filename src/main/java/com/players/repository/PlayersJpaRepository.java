package com.players.repository;

import com.players.repository.moel.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersJpaRepository extends JpaRepository<Player, String> {}