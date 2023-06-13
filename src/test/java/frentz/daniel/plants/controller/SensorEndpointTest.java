package frentz.daniel.plants.controller;

import frentz.daniel.hardwareservice.client.model.SensorReading;
import frentz.daniel.plants.service.SensorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorEndpointTest {
    @Mock
    SensorService sensorService;
    @InjectMocks
    private SensorEndpoint sensorEndpoint;


    @Test
    /**
     * when the readSensor method on the SensorEndpoint is called with a valid sensorId:
     * 1. the sensorService.readSensor method is called with the sensorId
     * 2. the result of the sensorService.readSensor method is returned
     * 3. the returned ResponseEntity has a status code of 200
     * 4. the returned ResponseEntity has a body that is equal to the result of the sensorService.readSensor method
     */
    void readSensor() {
        SensorReading sensorReading = new SensorReading();
        when(sensorService.readSensor(1L)).thenReturn(sensorReading);

        ResponseEntity<SensorReading> result = sensorEndpoint.readSensor(1L);

        assertEquals(200, result.getStatusCodeValue());
        assertSame(sensorReading, result.getBody());
    }
}
