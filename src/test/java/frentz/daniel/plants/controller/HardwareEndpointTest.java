package frentz.daniel.plants.controller;

import frentz.daniel.garden.model.Heater;
import frentz.daniel.garden.model.Light;
import frentz.daniel.garden.model.Water;
import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.plants.service.GardenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HardwareEndpointTest {

    @Mock
    private GardenService gardenService;
    @InjectMocks
    private HardwareEndpoint hardwareEndpoint;

    @Test
    /**
     *  When the addLight method is called on the HardwareEndpoint with a valid GardenLight:
     *  1. The gardenService.addLight method is called with the GardenLight
     *  2. The gardenService.addLight method is called exactly once
     *  3. The addLight method should return a ResponseEntity.
     *  4. The ResponseEntity should have a status of 201.
     *  5. The ResponseEntity should have a body of the GardenLight which was returned by the gardenService.addLight method.
     */
    void addLight() {
        Light requestLight = new Light();
        Light responseLight = new Light();

        when(this.gardenService.addHardware(any(Long.class), same(requestLight))).thenReturn(responseLight);

        ResponseEntity<Light> response = this.hardwareEndpoint.addLight(1L, requestLight);

        assertSame(responseLight, response.getBody());
        assertEquals(201, response.getStatusCodeValue());
    }

    /**
     *  When the addWater method is called on the HardwareEndpoint with a valid GardenWater:
     *  1. The gardenService.addWater method is called with the GardenWater
     *  2. The gardenService.addWater method is called exactly once
     *  3. The addWater method should return a ResponseEntity.
     *  4. The ResponseEntity should have a status of 201.
     *  5. The ResponseEntity should have a body of the GardenWater which was returned by the gardenService.addWater method.
     */
    @Test
    void addWater() {
        Water requestWater = new Water();
        Water responseWater = new Water();

        when(this.gardenService.addHardware(any(Long.class), same(requestWater))).thenReturn(responseWater);

        ResponseEntity<Water> response = this.hardwareEndpoint.addWater(1L, requestWater);

        assertSame(responseWater, response.getBody());
        assertEquals(201, response.getStatusCodeValue());
    }



    /**
     *  When the addHeater method is called on the HardwareEndpoint with a valid GardenHeater:
     *  1. The gardenService.addHeater method is called with the GardenHeater
     *  2. The gardenService.addHeater method is called exactly once
     *  3. The addHeater method should return a ResponseEntity.
     *  4. The ResponseEntity should have a status of 201.
     *  5. The ResponseEntity should have a body of the GardenWater which was returned by the gardenService.addHeater method.
     */
    @Test
    void addHeater() {
        Heater requestHeater = new Heater();
        Heater responseHeater = new Heater();

        when(this.gardenService.addHardware(any(Long.class), same(requestHeater))).thenReturn(responseHeater);

        ResponseEntity<Heater> response = this.hardwareEndpoint.addHeater(1L, requestHeater);

        assertSame(responseHeater, response.getBody());
        assertEquals(201, response.getStatusCodeValue());
    }

    /*
    * When the setHardwareState method is called on the HardwareEndpoint with a valid HardwareState:
    * 1. The gardenService.setHardwareState method is called with the HardwareState
    * 2. The gardenService.setHardwareState method is called exactly once
    * 3. The setHardwareState method should return a ResponseEntity.
    * 4. The ResponseEntity should have a status of 200.
    * 5. The ResponseEntity should have a body of the HardwareState which was returned by the gardenService.setHardwareState method.
     */
    @Test
    void setHardwareState() {
        HardwareState requestHeater = new HardwareState();
        HardwareState responseHeater = new HardwareState();

        when(this.gardenService.setHardwareState(any(Long.class), any(Long.class), same(requestHeater))).thenReturn(responseHeater);

        ResponseEntity<HardwareState> response = this.hardwareEndpoint.setHardwareState(1L, 1L, requestHeater);

        assertSame(responseHeater, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
