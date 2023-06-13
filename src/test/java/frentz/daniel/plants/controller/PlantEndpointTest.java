package frentz.daniel.plants.controller;

import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.service.GardenService;
import frentz.daniel.plants.service.PlantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantEndpointTest {

    @Mock
    private GardenService gardenService;
    @Mock
    private PlantService plantService;
    @InjectMocks
    private PlantEndpoint plantEndpoint;
    @Test
    /**
     * When the getPlant method is called with the ID of a plant:
     * 1. The plantService.getPlant method is called with the ID of the plant.
     * 2. The getPlant method returns a ResponseEntity.
     * 3. The ResponseEntity contains the plant returned by the plantService.getPlant method.
     * 4. The ResponseEntity contains a status code of 200.
     */
    void getPlant() {
        Plant plant = new Plant();
        when(plantService.getPlant(1L)).thenReturn(plant);

        ResponseEntity<Plant> result = plantEndpoint.getPlant(1L);

        assertSame(plant, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    /**
     * When the getPlants method of the PlantEndpoint is called:
     * 1. The plantService.getPlants method is called.
     * 2. The getPlants method returns a ResponseEntity.
     * 3. The ResponseEntity contains the list of plants returned by the plantService.getPlants method.
     * 4. The ResponseEntity contains a status code of 200.
     */
    void getPlants() {
        List plants = new ArrayList();
        when(plantService.getPlants()).thenReturn(plants);

        ResponseEntity result = plantEndpoint.getPlants();

        verify(plantService, times(1)).getPlants();
        assertEquals(200, result.getStatusCodeValue());
        assertSame(plants, result.getBody());
    }

    @Test
    /**
     * When the delete method of the PlantEndpoint is called with the ID of a plant:
     * 1. The plantService.deletePlant method is called with the ID of the plant.
     * 2. The delete method returns a ResponseEntity.
     * 3. The ResponseEntity contains a status code of 204.
     */
    void delete() {
        ResponseEntity result = plantEndpoint.delete(1L);

        verify(plantService).deletePlant(1L);
        assertEquals(204, result.getStatusCodeValue());
    }
}
