package frentz.daniel.plants.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import frentz.daniel.model.Garden;
import frentz.daniel.plants.service.GardenService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class GardenEndpointTest {

    @Mock
    GardenService gardenService;

    @InjectMocks
    private GardenEndpoint gardenEndpoint;

    @Test
    public void testGardenControllerNameRequired(){
        try {
            MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.gardenEndpoint).build();
            Garden requestBody = this.setupBasicGarden();
            requestBody.setName(null);
            String gardenJson = this.gardenToJson(requestBody);
            mockMvc.perform(MockMvcRequestBuilders.post("/garden/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gardenJson))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testCreatesId(){
        try {
            MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.gardenEndpoint).build();
            Garden responseGarden = this.setupBasicGarden();
            responseGarden.setId(1L);
            Mockito.when(this.gardenService.create(any())).thenReturn(responseGarden);
            String requestJson = this.gardenToJson(this.setupBasicGarden());
            mockMvc.perform(MockMvcRequestBuilders.post("/garden/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))
                    .andExpect(jsonPath("$.id", Matchers.is(1)));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private Garden setupBasicGarden(){
        Garden result = new Garden();
        result.setName("garden 1");
        result.setDescription("first garden");
        return result;
    }

    private String gardenToJson(Garden garden){
        try {
            return new ObjectMapper().writeValueAsString(garden);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
