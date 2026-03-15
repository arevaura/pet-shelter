package com.petshelter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetShelterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String PET_JSON = """
            {
                "id": 1,
                "name": "Buddy",
                "species": "Dog",
                "breed": "Golden Retriever",
                "date_of_birth": "2020-01-01",
                "sex": "male",
                "adoption_status": "available"
            }
            """;

    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("healthy"));
    }

    @Test
    void testCreatePet() throws Exception {
        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PET_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Buddy"))
                .andExpect(jsonPath("$.species").value("Dog"));
    }

    @Test
    void testListPets() throws Exception {
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}