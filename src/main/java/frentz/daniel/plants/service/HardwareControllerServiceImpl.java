package frentz.daniel.plants.service;

import frentz.daniel.garden.model.*;
import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.hardwareservice.client.service.HardwareClient;
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
    public Hardware createHardware(long controllerId, GardenHardware gardenHardware) {
        Hardware hardware = this.gardenHardwareConverter.toHardware(gardenHardware);
        hardware = this.hardwareClient.addHardware(controllerId, hardware);
        return hardware;
    }

    @Override
    public Sensor createSensor(long controllerId, Sensor sensor) {
        Sensor result = this.hardwareClient.addSensor(controllerId, sensor);
        return result;
    }

}
