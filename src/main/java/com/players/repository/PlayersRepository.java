package com.players.repository;

import com.players.repository.moel.Player;
import org.springframework.data.repository.CrudRepository;

// Preferred approach, but not used, because I encountered  the following issue: data.sql does not insert the data if
// the table name is the same as of the @Entity (Player)
public interface PlayersRepository extends CrudRepository<Player, String> {}