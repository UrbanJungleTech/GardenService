package frentz.daniel.plants.service;


import frentz.daniel.hardwareservice.client.model.SensorReading;

public interface SensorService {
    SensorReading readSensor(long sensorId);
    SensorReading readAverageSensor(long gardenId, String sensorType);
}
