package com.example.restservice;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class PlayersProvider {
    private final Map<String, Map<?, ?>> players;

    public PlayersProvider() throws IOException {
        ClassLoader classLoader = PlayersProvider.class.getClassLoader();
        File file = new File(classLoader.getResource("player.csv").getFile());
        this.players = readObjectsFromCsv(file).stream()
                .collect(toMap(player ->
                                player.get("playerID"),
                        Function.identity())
                );
    }

    public Map<?, ?> getPlayer(String playerId) {
        return players.get(playerId);
    }

    public List<Map<?, ?>> getAllPlayers() {
        return players.values().stream().toList();
    }

    private static List<Map<String, String>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try (MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class)
                .with(bootstrap)
                .readValues(file)) {
            return mappingIterator.readAll();
        }
    }
}
