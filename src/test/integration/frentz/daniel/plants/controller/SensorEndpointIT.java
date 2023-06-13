package frentz.daniel.plants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.GardenHardwareController;
import frentz.daniel.garden.model.GardenSensor;
import frentz.daniel.plants.repository.GardenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("spring.profiles.active=test")
@AutoConfigureMockMvc
@DirtiesContext
public class SensorEndpointIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GardenRepository gardenRepository;


    @BeforeEach
    void setUp() {
        gardenRepository.deleteAll();
    }
    /**
     * Given the /garden/ endpoint has been called to create a garden
     * Given the /garden/{gardenId}/sensor endpoint has been called to create a sensor in the garden
     * when a GET request is made to /sensor/{sensorId}/reading
     * 1. a 200 status code is returned
     * 2. the sensor reading is returned
     * 3. the sensor reading has a timestamp
     * 4. the sensor reading has a value
     */
    @Test
    void readSensor_whenGivenAValidSensorId_shouldReturnTheSensorReading() throws Exception {
        String serialNumber = String.valueOf(new Random().nextInt(1000000));
        Garden garden = new Garden();
        GardenHardwareController hardwareController = new GardenHardwareController();
        hardwareController.setSerialNumber(serialNumber);

        garden.setHardwareController(hardwareController);
        String gardenJson = objectMapper.writeValueAsString(garden);

        MvcResult gardenResultBody = mockMvc.perform(MockMvcRequestBuilders.post("/garden/")
                        .contentType("application/json")
                        .content(gardenJson))
                        .andExpect(status().isCreated())
                        .andReturn();
        Garden gardenResult = objectMapper.readValue(gardenResultBody.getResponse().getContentAsString(), Garden.class);

        GardenSensor sensor = new GardenSensor();
        String sensorJson = objectMapper.writeValueAsString(sensor);
        MvcResult resultSensorBody = mockMvc.perform(MockMvcRequestBuilders.post("/garden/" + gardenResult.getId() + "/hardwareController/sensor")
                        .contentType("application/json")
                        .content(sensorJson))
                        .andExpect(status().isCreated())
                        .andReturn();
        GardenSensor sensorResult = objectMapper.readValue(resultSensorBody.getResponse().getContentAsString(), GardenSensor.class);

        mockMvc.perform(get("/sensor/" + sensorResult.getId() + "/reading"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.readingTime").isNotEmpty())
                .andExpect(jsonPath("$.reading").isNotEmpty());
    }

    /**
     * Given a sensor id which is not associated with a sensor
     * when a GET request is made to /sensor/{sensorId}/reading
     * 1. a 404 status code is returned
     */
    @Test
    void readSensor_whenGivenAnInvalidSensorId_shouldReturn404() throws Exception {
        mockMvc.perform(get("/sensor/1234/reading"))
                .andExpect(status().isNotFound());
    }


}
