package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.SensorDetails;
import frentz.daniel.hardwareservice.client.model.Sensor;

import java.util.List;

public interface SensorDetailsConverter {
    SensorDetails toSensorDetails(List<Sensor> sensors);
    List<Sensor> toSensors(SensorDetails sensorDetails);
}
