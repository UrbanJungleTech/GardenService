package frentz.daniel.plants.controller;

import frentz.daniel.garden.model.Light;
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

    @Test
    void addWater() {
    }

    @Test
    void addHeater() {
    }

    @Test
    void setHardwareState() {
    }
}
