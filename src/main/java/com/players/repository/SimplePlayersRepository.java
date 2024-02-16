package com.players.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.players.repository.moel.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

// Simple hash linked map implementation for players repository.
// Upon construction, it loads the given csv file and inserts its content into a map of player ID to player (json, as a map).
@Service
public class SimplePlayersRepository {
    private final Map<String, Player> playerIdToPlayerMap;

    public SimplePlayersRepository(
            @Value("${players.path:sources/player.csv}") String playersPath) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL playersResource = classLoader.getResource(playersPath);
        if (isNull(playersResource)) {
            throw new IllegalStateException("No such resource: " + playersPath);
        }
        File playersFile = new File(playersResource.getFile());
        this.playerIdToPlayerMap = readObjectsFromCsv(playersFile).stream()
                .collect(toMap(
                        Player::getPlayerID,
                        Function.identity(),
                        (u, v) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", u));
                        },
                        LinkedHashMap::new
                ));
    }

    public Optional<Player> findById(String playerId) {
        return Optional.ofNullable(playerIdToPlayerMap.get(playerId));
    }

    public List<Player> findAll(int offset, int limit) {
        return playerIdToPlayerMap.values().stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }

    private static List<Player> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try (MappingIterator<Player> mappingIterator = csvMapper.readerFor(Player.class)
                .with(bootstrap)
                .readValues(file)) {
            return mappingIterator.readAll();
        }
    }
}
