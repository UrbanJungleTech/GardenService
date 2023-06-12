package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.SensorType;

public interface SensorService {
    double readSensor(long sensorId);
    double readAverageSensor(long gardenId, SensorType sensorType);
}
