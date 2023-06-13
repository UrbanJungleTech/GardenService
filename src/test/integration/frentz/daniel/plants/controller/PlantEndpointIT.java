package frentz.daniel.plants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.repository.GardenRepository;
import frentz.daniel.plants.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class PlantEndpointIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private GardenRepository gardenRepository;

    @BeforeEach
    void clearDatabase() {
        gardenRepository.deleteAll();
        plantRepository.deleteAll();
    }


    /**
     * Given a Garden was created through /garden/ and returned an id
     * Given a POST request is made to /garden/{gardenId}/plants with a valid Plant to create a new plant and returns an id
     * When a GET request is made to /plants/{plantId}
     * Then a 200 status code is returned
     * And the plant is returned
     */
    @Test
    public void getPlant_whenGivenAValidPlantId_shouldReturnThePlant() throws Exception {
        //create the garden
        Garden garden = new Garden();
        String gardenJson = objectMapper.writeValueAsString(garden);
        MvcResult gardenMvcResult = this.mockMvc.perform(post("/garden/")
                .contentType("application/json")
                .content(gardenJson))
                .andReturn();
        garden = objectMapper.readValue(gardenMvcResult.getResponse().getContentAsString(), Garden.class);
        //create the plant
        Plant plant = new Plant();
        String plantJson = objectMapper.writeValueAsString(plant);
        MvcResult plantMvcResult = this.mockMvc.perform(post("/garden/" + garden.getId() + "/plants/")
                .contentType("application/json")
                .content(plantJson))
                .andReturn();
        plant = objectMapper.readValue(plantMvcResult.getResponse().getContentAsString(), Plant.class);
        //get the plant
        this.mockMvc.perform(get("/plants/" + plant.getId()))
                .andExpect(jsonPath("$.id").value(plant.getId()))
                .andExpect(jsonPath("$.name").value(plant.getName()))
                .andExpect(jsonPath("$.gardenId").value(garden.getId()));

    }

    /**
     * Given a Garden was created through /garden/ and returned an id
     * Given a POST request is made to /garden/{gardenId}/plants twice, with a valid Plant to create a new plant and returns an id
     * When a GET request is made to /plants/
     * Then a 200 status code is returned
     * Both of the plants are returned
     */
    @Test
    public void getPlants_whenGivenAValidGardenId_shouldReturnAllPlants() throws Exception {
        //create the garden
        Garden garden = new Garden();
        String gardenJson = objectMapper.writeValueAsString(garden);
        MvcResult gardenMvcResult = this.mockMvc.perform(post("/garden/")
                .contentType("application/json")
                .content(gardenJson))
                .andReturn();
        garden = objectMapper.readValue(gardenMvcResult.getResponse().getContentAsString(), Garden.class);
        //create the plant
        Plant plant = new Plant();
        String plantJson = objectMapper.writeValueAsString(plant);
        MvcResult plantMvcResult = this.mockMvc.perform(post("/garden/" + garden.getId() + "/plants/")
                .contentType("application/json")
                .content(plantJson))
                .andReturn();
        plant = objectMapper.readValue(plantMvcResult.getResponse().getContentAsString(), Plant.class);
        //create the plant
        Plant plant2 = new Plant();
        String plantJson2 = objectMapper.writeValueAsString(plant2);
        MvcResult plantMvcResult2 = this.mockMvc.perform(post("/garden/" + garden.getId() + "/plants/")
                .contentType("application/json")
                .content(plantJson2))
                .andReturn();
        plant2 = objectMapper.readValue(plantMvcResult2.getResponse().getContentAsString(), Plant.class);
        //get the plants
        this.mockMvc.perform(get("/plants/"))
                .andExpect(jsonPath("$[0].id").value(plant.getId()))
                .andExpect(jsonPath("$[0].name").value(plant.getName()))
                .andExpect(jsonPath("$[0].gardenId").value(garden.getId()))
                .andExpect(jsonPath("$[1].id").value(plant2.getId()))
                .andExpect(jsonPath("$[1].name").value(plant2.getName()))
                .andExpect(jsonPath("$[1].gardenId").value(garden.getId()));

    }

    /**
     * Given a Garden was created through /garden/ and returned an id
     * Given a POST request is made to /garden/{gardenId}/plants with a valid Plant to create a new plant and returns an id
     * When a DELETE request is made to /plants/{plantId}
     * Then a 204 status code is returned
     * And the plant is deleted and cannot be retrieved
     */
    @Test
    public void deletePlant_whenGivenAValidPlantId_shouldDeleteThePlant() throws Exception {
        //create the garden
        Garden garden = new Garden();
        String gardenJson = objectMapper.writeValueAsString(garden);
        MvcResult gardenMvcResult = this.mockMvc.perform(post("/garden/")
                .contentType("application/json")
                .content(gardenJson))
                .andReturn();
        garden = objectMapper.readValue(gardenMvcResult.getResponse().getContentAsString(), Garden.class);
        //create the plant
        Plant plant = new Plant();
        String plantJson = objectMapper.writeValueAsString(plant);
        MvcResult plantMvcResult = this.mockMvc.perform(post("/garden/" + garden.getId() + "/plants/")
                .contentType("application/json")
                .content(plantJson))
                .andReturn();
        plant = objectMapper.readValue(plantMvcResult.getResponse().getContentAsString(), Plant.class);
        //delete the plant
        this.mockMvc.perform(delete("/plants/" + plant.getId()))
                .andExpect(status().isNoContent());
        //get the plant
        this.mockMvc.perform(get("/plants/" + plant.getId()))
                .andExpect(status().isNotFound());

    }

    /**
     * Given there does not exists a plant with a given id
     * When a GET request is made to /plants/{plantId}
     * Then a 404 status code is returned
     */
    @Test
    public void getPlant_whenGivenAnInvalidPlantId_shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/plants/1"))
                .andExpect(status().isNotFound());
    }

    /**
     * Given there does not exists a plant with a given id
     * When a DELETE request is made to /plants/{plantId}
     * Then a 404 status code is returned
     */
    @Test
    public void deletePlant_whenGivenAnInvalidPlantId_shouldReturn404() throws Exception {
        this.mockMvc.perform(delete("/plants/1"))
                .andExpect(status().isNotFound());
    }
}

