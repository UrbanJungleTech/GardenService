package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.GardenSensor;
import frentz.daniel.garden.model.SensorDetails;
import frentz.daniel.hardwareservice.client.model.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorDetailsConverterImplTest {

    @Mock
    private SensorConverter sensorConverter;
    @InjectMocks
    private SensorDetailsConverterImpl sensorDetailsConverter;

    /**
     * Given a List of Sensors with a single sensor
     * when the toSensorDetails method is called on the sensorDetailsConverter with the list of sensors
     * 1. the sensorConverter.toGardenSensor should be called with the single sensor instance
     * 2. the sensorConverter should return a SensorDetails
     * 3. the SensorDetails should contain a single GardenSensor of the same type as the sensor
     */
    @Test
    void toSensorDetails() {
        Sensor sensor = new Sensor();
        sensor.setSensorType("temperature");
        List<Sensor> sensors = List.of(sensor);

        GardenSensor gardenSensor = new GardenSensor();
        gardenSensor.setSensorType(sensor.getSensorType());

        when(this.sensorConverter.toGardenSensor(sensor)).thenReturn(gardenSensor);
        SensorDetails result = this.sensorDetailsConverter.toSensorDetails(sensors);

        verify(this.sensorConverter).toGardenSensor(sensor);
        assertNotNull(result);
        assertEquals(1, result.getSensors().size());
        assertEquals(1, result.getSensorsByType(sensor.getSensorType()).size());
        assertSame(gardenSensor, result.getSensorsByType(sensor.getSensorType()).get(0));
    }

    /**
     * Given a List of Sensors with 2 sensors, each of a different type
     * when the toSensorDetails method is called on the sensorDetailsConverter with the list of sensors
     * 1. the sensorConverter.toGardenSensor should be called with the single sensor instance
     * 2. the sensorConverter should return a SensorDetails
     * 3. the SensorDetails should contain 2 GardenSensors of the same types as the sensors
     */
    @Test
    void toSensorDetails2() {
        Sensor sensor1 = new Sensor();
        sensor1.setSensorType("temperature");
        Sensor sensor2 = new Sensor();
        sensor2.setSensorType("humidity");
        List<Sensor> sensors = List.of(sensor1, sensor2);

        GardenSensor gardenSensor1 = new GardenSensor();
        gardenSensor1.setSensorType(sensor1.getSensorType());
        GardenSensor gardenSensor2 = new GardenSensor();
        gardenSensor2.setSensorType(sensor2.getSensorType());

        when(this.sensorConverter.toGardenSensor(sensor1)).thenReturn(gardenSensor1);
        when(this.sensorConverter.toGardenSensor(sensor2)).thenReturn(gardenSensor2);
        SensorDetails result = this.sensorDetailsConverter.toSensorDetails(sensors);

        verify(this.sensorConverter).toGardenSensor(sensor1);
        verify(this.sensorConverter).toGardenSensor(sensor2);
        assertNotNull(result);
        assertEquals(2, result.getSensors().size());
        assertEquals(1, result.getSensorsByType(sensor1.getSensorType()).size());
        assertSame(gardenSensor1, result.getSensorsByType(sensor1.getSensorType()).get(0));
        assertEquals(1, result.getSensorsByType(sensor2.getSensorType()).size());
        assertSame(gardenSensor2, result.getSensorsByType(sensor2.getSensorType()).get(0));
    }

    /**
     * Given a SensorDetails with a single GardenSensor
     * when the toSensors method is called on the sensorDetailsConverter with the SensorDetails
     * 1. the sensorConverter.toSensors should be called with the single GardenSensor instance
     * 2. the sensorConverter should return a List of Sensors
     * 3. the List of Sensors should contain a single Sensor of the same type as the GardenSensor
     * 4. the Sensor should have the same sensorType as the GardenSensor
     */
    @Test
    void toSensors() {
        GardenSensor gardenSensor = new GardenSensor();
        gardenSensor.setSensorType("temperature");
        SensorDetails sensorDetails = new SensorDetails();
        sensorDetails.addSensor(gardenSensor);

        Sensor sensor = new Sensor();
        sensor.setSensorType(gardenSensor.getSensorType());

        when(this.sensorConverter.toSensors(sensorDetails.getSensors())).thenReturn(List.of(sensor));
        List<Sensor> result = this.sensorDetailsConverter.toSensors(sensorDetails);

        verify(this.sensorConverter).toSensors(sensorDetails.getSensors());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(sensor, result.get(0));
        assertEquals(gardenSensor.getSensorType(), result.get(0).getSensorType());
    }

    /**
     * Given a SensorDetails with 2 GardenSensors of different types
     * when the toSensors method is called on the sensorDetailsConverter with the SensorDetails
     * 1. the sensorConverter.toSensors should be called with the single GardenSensor instance
     * 2. the sensorConverter should return a List of Sensors
     * 3. the List of Sensors should contain 2 Sensors of the same types as the GardenSensors
     * 4. the Sensors should have the same sensorTypes as the GardenSensors
     * 5. the Sensors should be in the same order as the GardenSensors
     */
    @Test
    void toSensors2() {
        GardenSensor gardenSensor1 = new GardenSensor();
        gardenSensor1.setSensorType("temperature");
        GardenSensor gardenSensor2 = new GardenSensor();
        gardenSensor2.setSensorType("humidity");
        SensorDetails sensorDetails = new SensorDetails();
        sensorDetails.addSensor(gardenSensor1);
        sensorDetails.addSensor(gardenSensor2);

        Sensor sensor1 = new Sensor();
        sensor1.setSensorType(gardenSensor1.getSensorType());
        Sensor sensor2 = new Sensor();
        sensor2.setSensorType(gardenSensor2.getSensorType());

        when(this.sensorConverter.toSensors(sensorDetails.getSensors())).thenReturn(List.of(sensor1, sensor2));
        List<Sensor> result = this.sensorDetailsConverter.toSensors(sensorDetails);

        verify(this.sensorConverter).toSensors(sensorDetails.getSensors());
        assertNotNull(result);
        assertEquals(2, result.size());
        assertSame(sensor1, result.get(0));
        assertEquals(gardenSensor1.getSensorType(), result.get(0).getSensorType());
        assertSame(sensor2, result.get(1));
        assertEquals(gardenSensor2.getSensorType(), result.get(1).getSensorType());
    }
}
