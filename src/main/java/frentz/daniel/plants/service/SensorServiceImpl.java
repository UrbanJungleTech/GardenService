package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.SensorType;
import frentz.daniel.controllerclient.service.HardwareClient;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService{

    private HardwareClient hardwareClient;

    public SensorServiceImpl(HardwareClient hardwareClient){
        this.hardwareClient = hardwareClient;
    }

    @Override
    public double readSensor(long sensorId) {
        return this.hardwareClient.readSensor(sensorId);
    }

    @Override
    public double readAverageSensor(long hardwareControllerId, SensorType sensorType) {
        return this.hardwareClient.readAverageSensor(hardwareControllerId, sensorType);
    }
}
