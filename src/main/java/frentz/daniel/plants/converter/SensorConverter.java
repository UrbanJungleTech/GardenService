package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.model.GardenSensor;

import java.util.List;

public interface SensorConverter {
    GardenSensor toGardenSensor(Sensor sensor);
    Sensor toSensor(GardenSensor gardenSensor);
    List<Sensor> toSensors(List<GardenSensor> moisture);
}
