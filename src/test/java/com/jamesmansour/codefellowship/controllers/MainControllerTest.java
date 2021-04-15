package com.jamesmansour.codefellowship.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testLanding() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("<h3><a href=\"/\">Home</a> | <a href=\"/feed\">feed</a></h3>")))
                .andExpect(content().string(containsString("<h1>Code Friends</h1>")))
                .andExpect(content().string(containsString("<h1>Welcome to Code Friends!</h1>")))
                .andExpect(content().string(containsString("<P>Here you can sing up make posts and see other as well!</P>")))
                .andExpect(status().isOk());
    }

}