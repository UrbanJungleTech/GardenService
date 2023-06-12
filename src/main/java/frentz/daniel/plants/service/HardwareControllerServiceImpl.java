package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.Hardware;
import frentz.daniel.controllerclient.model.HardwareController;
import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.controllerclient.service.HardwareClient;
import frentz.daniel.model.GardenHardwareController;
import frentz.daniel.model.Heater;
import frentz.daniel.model.Light;
import frentz.daniel.model.Water;
import frentz.daniel.plants.converter.GardenHardwareControllerConverter;
import frentz.daniel.plants.converter.GardenHardwareConverter;
import org.springframework.stereotype.Service;

@Service
public class HardwareControllerServiceImpl implements HardwareControllerService{

    private HardwareClient hardwareClient;
    private GardenHardwareControllerConverter gardenHardwareControllerConverter;
    private GardenHardwareConverter gardenHardwareConverter;

    public HardwareControllerServiceImpl(HardwareClient hardwareClient,
                                         GardenHardwareControllerConverter gardenHardwareControllerConverter,
                                         GardenHardwareConverter gardenHardwareConverter){
        this.hardwareClient = hardwareClient;
        this.gardenHardwareControllerConverter = gardenHardwareControllerConverter;
        this.gardenHardwareConverter = gardenHardwareConverter;
    }

    @Override
    public HardwareController createHardwareController(GardenHardwareController gardenHardwareController) {
        HardwareController hardwareController = this.gardenHardwareControllerConverter.toHardwareController(gardenHardwareController);
        HardwareController result = this.hardwareClient.createHardwareController(hardwareController);
        return result;
    }

    @Override
    public void createLight(Long controllerId, Light light) {
        Hardware hardware = this.gardenHardwareConverter.toHardware(light);
        this.hardwareClient.addHardware(controllerId, hardware);
    }

    @Override
    public void createWater(Long controllerId, Water water) {
        Hardware hardware = this.gardenHardwareConverter.toHardware(water);
        this.hardwareClient.addHardware(controllerId, hardware);
    }

    @Override
    public void createHeater(Long controllerId, Heater heater) {
        Hardware hardware = this.gardenHardwareConverter.toHardware(heater);
        this.hardwareClient.addHardware(controllerId, hardware);
    }

    @Override
    public void createSensor(long controllerId, Sensor sensor) {
        this.hardwareClient.addSensor(controllerId, sensor);
    }

}
