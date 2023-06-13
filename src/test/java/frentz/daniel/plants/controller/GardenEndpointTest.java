package frentz.daniel.plants.controller;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.GardenSensor;
import frentz.daniel.garden.model.Plant;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.plants.service.GardenAdditionService;
import frentz.daniel.plants.service.GardenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GardenEndpointTest {

    @Mock
    GardenService gardenService;
    @Mock
    GardenAdditionService gardenAdditionService;

    @InjectMocks
    private GardenEndpoint gardenEndpoint;

    @Test
    /**
     * Given the create method of the GardenEndpoint is pass a Garden object:
     * 1. The create method should return a ResponseEntity with a Garden object as the body.
     * 2. The garden object should be returned from the GardenService.
     * 3. The ResponseEntity should have a status of 201.
     */
    public void testCreate(){
        Garden garden = new Garden();
        garden.setId(1L);
        when(this.gardenAdditionService.addGarden(any(Garden.class))).thenReturn(garden);

        ResponseEntity<Garden> responseEntity = this.gardenEndpoint.create(garden);

        assertSame(garden, responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCodeValue());
        verify(this.gardenAdditionService, times(1)).addGarden(any(Garden.class));
    }

    @Test
    /**
     * Given the addPlant method of the GardenEndpoint is passed a plant object and a gardenId:
     * 1. The addPlant method should return a ResponseEntity with a Plant object as the body.
     * 2. The Plant object should be returned from the GardenService.
     * 3. The ResponseEntity should have a status of 201.
     */
    public void testAddPlant() {
        long gardenId = 1L;
        Plant plant = new Plant();
        Plant responsePlant = new Plant();
        when(this.gardenAdditionService.addPlant(any(Long.class), any(Plant.class))).thenReturn(responsePlant);

        ResponseEntity<Plant> responseEntity = this.gardenEndpoint.addPlant(plant, 1L);

        assertSame(responsePlant, responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCodeValue());
        verify(this.gardenAdditionService, times(1)).addPlant(anyLong(), eq(plant));
    }

    @Test
    /**
     * Given the getGardens method of the GardenEndpoint is called:
     * 1. The getGardens method should return a ResponseEntity with a List of Garden objects as the body.
     * 2. The list of Garden objects should be returned from the GardenService.
     */
    public void testGetGardens() {
        Garden garden1 = new Garden();
        Garden garden2 = new Garden();
        List<Garden> gardens = List.of(garden1, garden2);
        when(this.gardenService.getGardens()).thenReturn(gardens);

        ResponseEntity responseEntity = this.gardenEndpoint.getGardens();

        verify(this.gardenService, times(1)).getGardens();
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertSame(gardens, responseEntity.getBody());
    }

    @Test
    /**
     * Given the getGarden method of the GardenEndpoint is called with a gardenId:
     * 1. The getGarden method should return a ResponseEntity with a Garden object as the body.
     * 2. The Garden object should be returned from the GardenService.
     */
    public void testGetGarden() {
        Garden garden = new Garden();
        when(this.gardenService.getGarden(anyLong())).thenReturn(garden);

        ResponseEntity<Garden> responseEntity = this.gardenEndpoint.getGarden(1L);

        verify(this.gardenService, times(1)).getGarden(anyLong());
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertSame(garden, responseEntity.getBody());
    }

    @Test
    /**
     * Given the deleteGarden method of the GardenEndpoint is called with a gardenId:
     * 1. The deleteGarden method should return a ResponseEntity with 204 (no content) as its status code.
     * 2. The deleteGarden method should call the deleteGarden method of GardenAdditionService.
     */
    public void testDeleteGarden(){
        ResponseEntity responseEntity = this.gardenEndpoint.deleteGarden(1L);

        verify(this.gardenAdditionService, times(1)).deleteGarden(anyLong());
        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    /**
     * Given the addSensor method of the GardenEndpoint is called with a gardenId and a Sensor object:
     * 1. The addSensor method should return a ResponseEntity with 201 (created) as its status code.
     * 2. The addSensor method should call the addSensor method of GardenService.
     * 3. The addSensor method should return a ResponseEntity with a Sensor object as the body.
     * 4. The Sensor object should be returned from the GardenAdditionService.
     */
    public void testAddSensor(){
        long gardenId = 1L;
        GardenSensor requestSensor = new GardenSensor();
        GardenSensor responseSensor = new GardenSensor();

        when(this.gardenService.addSensor(anyLong(), any(GardenSensor.class))).thenReturn(responseSensor);

        ResponseEntity responseEntity = this.gardenEndpoint.addSensor(gardenId, requestSensor);

        verify(this.gardenService, times(1)).addSensor(anyLong(), same(requestSensor));
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertSame(responseSensor, responseEntity.getBody());
    }

}
