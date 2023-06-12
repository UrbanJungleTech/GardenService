package frentz.daniel.plants.service;


public interface SensorService {
    double readSensor(long sensorId);
    double readAverageSensor(long gardenId,String sensorType);
}
