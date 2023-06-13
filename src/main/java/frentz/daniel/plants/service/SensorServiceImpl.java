package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.hardwareservice.client.model.SensorReading;
import frentz.daniel.hardwareservice.client.service.HardwareClient;
import frentz.daniel.plants.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class SensorServiceImpl implements SensorService{

    private HardwareClient hardwareClient;

    public SensorServiceImpl(HardwareClient hardwareClient){
        this.hardwareClient = hardwareClient;
    }

    @Override
    public SensorReading readSensor(long sensorId) {
        try {
            return this.hardwareClient.readSensor(sensorId);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException(Sensor.class, sensorId);
        }
    }

    @Override
    public SensorReading readAverageSensor(long hardwareControllerId, String sensorType) {
        return this.hardwareClient.readAverageSensor(hardwareControllerId, sensorType);
    }
}
