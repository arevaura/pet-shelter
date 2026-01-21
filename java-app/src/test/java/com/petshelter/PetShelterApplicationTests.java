package com.petshelter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PetShelterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePet() throws Exception {
        // This JSON matches the schema in your openapi.yaml
        String petJson = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Buddy\",\n" +
                "    \"species\": \"Dog\",\n" +
                "    \"breed\": \"Golden Retriever\",\n" +
                "    \"date_of_birth\": \"2020-01-01\",\n" +
                "    \"sex\": \"male\",\n" +
                "    \"adoption_status\": \"available\"\n" +
                "}";

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(petJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }
}