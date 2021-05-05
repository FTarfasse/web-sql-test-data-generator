package com.sqltestdataapi.controllers;

import com.sqltestdataapi.ApiApplication;
import com.sqltestdataapi.services.TestDataGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ApiApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "demo")
public class QueryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_receive_a_query() throws Exception {
        // mockMvc.perform(get("http://localhost:" + port + "/v0/api/request?query=INSERT%20INTO%20GuitarHero%20VALUES%20(1,%20%27Tosin%27,%20%27Abasi%27)"))
        mockMvc.perform(get("http://localhost:" + port + "/v0/api/request?query=SELECT%20*%20FROM%20GuitarHero"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("INSERT")));
    }

}