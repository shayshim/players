/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.players;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final JsonMapper mapper = new JsonMapper();

    @Test
    public void getPlayers() throws Exception {
        String expectedResponse = getExpectedResponseStr("all_players_response.json");
        this.mockMvc.perform(
                get("/api/players")
                        .queryParam("pageNumber", "1")
                        .queryParam("pageSize", "3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void getPlayer() throws Exception {
        String expectedResponse = getExpectedResponseStr("aaronto01_player_response.json");
        this.mockMvc.perform(get("/api/players/aaronto01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void getPlayer_NoSuchPlayer() throws Exception {
        this.mockMvc.perform(get("/api/players/cookieMonster"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private String getExpectedResponseStr(String expectedResponseFileName) throws IOException {
        URL responseResource = getClass().getClassLoader().getResource("responses/" + expectedResponseFileName);
        Assertions.assertNotNull(responseResource);
        File responseFile = new File(responseResource.getFile());
        JsonNode expectedResponse = mapper.readValue(responseFile, JsonNode.class);
        return mapper.writeValueAsString(expectedResponse);
    }
}
