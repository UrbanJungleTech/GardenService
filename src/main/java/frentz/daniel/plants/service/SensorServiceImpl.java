package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.service.HardwareClient;
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
    public double readAverageSensor(long hardwareControllerId,String sensorType) {
        return this.hardwareClient.readAverageSensor(hardwareControllerId, sensorType);
    }
}
