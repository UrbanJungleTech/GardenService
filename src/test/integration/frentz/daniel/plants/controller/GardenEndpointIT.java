package frentz.daniel.plants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.repository.GardenRepository;
import frentz.daniel.plants.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class GardenEndpointIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GardenRepository gardenRepository;
    @Autowired
    private PlantRepository plantRepository;


    @BeforeEach
    void setUp() {
        gardenRepository.deleteAll();
    }
    /**
     * Given a valid Garden object
     * when a POST request is made to /garden/
     * then a 201 status code is returned
     * and the garden is persisted
     * and the garden has an id
     */
    @Test
    void addGarden_whenGivenAValidGarden_shouldPersistTheGarden_andReturnTheGarden() throws Exception {
        Garden garden = new Garden();
        ObjectMapper objectMapper = new ObjectMapper();
        String gardenJson = objectMapper.writeValueAsString(garden);
        mockMvc.perform(post("/garden/")
                .contentType("application/json")
                .content(gardenJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());

        List<GardenEntity> gardens = this.gardenRepository.findAll();
        assertEquals(1, gardens.size());
        assertNotEquals(0, gardens.get(0).getId());
    }

    /**
     * Given a valid plant object and an already existing garden
     * when a POST request is made to /garden/{gardenId}/plant
     * then a 201 status code is returned
     * and the plant is persisted
     * and the plant has an id
     */
    @Test
    void addPlant_whenGivenAValidPlantAndAnExistingGarden_shouldPersistThePlant_andReturnThePlant() throws Exception {
        GardenEntity garden = new GardenEntity();
        this.gardenRepository.save(garden);
        Plant plant = new Plant();
        ObjectMapper objectMapper = new ObjectMapper();
        String plantJson = objectMapper.writeValueAsString(plant);
        mockMvc.perform(post("/garden/" + garden.getId() + "/plants/")
                .contentType("application/json")
                .content(plantJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());

        List<GardenEntity> gardens = this.gardenRepository.findAll();
        assertEquals(1, gardens.size());
        assertNotEquals(0, gardens.get(0).getId());
        assertEquals(1, gardens.get(0).getPlants().size());
    }

    /**
     * Given an existing garden
     * when a GET request is made to /garden/{gardenId}
     * then a 200 status code is returned
     * and the garden is returned
     * and the garden has the same id as the one requested
     */
    @Test
    void getGarden_whenGivenAnExistingGarden_shouldReturnTheGarden() throws Exception {
        GardenEntity garden = new GardenEntity();
        this.gardenRepository.save(garden);
        mockMvc.perform(get("/garden/" + garden.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(garden.getId()));
    }

    /**
     * Given an id which does not map to any existing GardenEntities
     * when a GET request is made to /garden/{gardenId}
     * then a 404 status code is returned
     */
    @Test
    void getGarden_whenGivenAnIdWhichDoesNotMapToAnyExistingGarden_shouldReturn404() throws Exception {
        mockMvc.perform(get("/garden/1"))
                .andExpect(status().isNotFound());
    }



}
