package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.GardenSensor;
import frentz.daniel.hardwareservice.client.model.Sensor;

import java.util.List;

public interface SensorConverter {
    GardenSensor toGardenSensor(Sensor sensor);
    Sensor toSensor(GardenSensor gardenSensor);
    List<Sensor> toSensors(List<GardenSensor> moisture);
}
