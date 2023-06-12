package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.model.Hardware;
import frentz.daniel.controllerclient.model.HardwareController;
import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.controllerclient.service.HardwareClient;
import frentz.daniel.model.GardenHardwareController;
import frentz.daniel.model.HardwareDetails;
import frentz.daniel.model.SensorDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenHardwareControllerConverterImpl implements GardenHardwareControllerConverter{

    private HardwareClient hardwareClient;
    private GardenHardwareConverter gardenHardwareConverter;
    private HardwareDetailsConverter hardwareDetailsConverter;
    private SensorDetailsConverter sensorDetailsConverter;

    public GardenHardwareControllerConverterImpl(HardwareClient hardwareClient,
                                                 GardenHardwareConverter gardenHardwareConverter,
                                                 HardwareDetailsConverter hardwareDetailsConverter,
                                                 SensorDetailsConverter sensorDetailsConverter){
        this.hardwareClient = hardwareClient;
        this.gardenHardwareConverter = gardenHardwareConverter;
        this.hardwareDetailsConverter = hardwareDetailsConverter;
        this.sensorDetailsConverter = sensorDetailsConverter;
    }

    @Override
    public GardenHardwareController toModel(long controllerId){
        GardenHardwareController result = new GardenHardwareController();
        HardwareController hardwareController = this.hardwareClient.getHardwareController(controllerId);

        result.setName(hardwareController.getName());
        result.setSerialNumber(hardwareController.getSerialNumber());
        result.setId(hardwareController.getId());

        HardwareDetails hardwareDetails = this.hardwareDetailsConverter.toModel(hardwareController.getHardware());
        result.setHardwareDetails(hardwareDetails);

        SensorDetails sensorDetails = this.sensorDetailsConverter.toModel(hardwareController.getSensors());
        result.setSensorDetails(sensorDetails);
        return result;
    }

    @Override
    public HardwareController toHardwareController(GardenHardwareController gardenHardwareController) {
        HardwareController result = new HardwareController();
        List<Hardware> hardwares = this.hardwareDetailsConverter.toHardware(gardenHardwareController.getHardwareDetails());
        result.setHardware(hardwares);
        List<Sensor> sensors = this.sensorDetailsConverter.toSensors(gardenHardwareController.getSensorDetails());
        result.setSensors(sensors);
        result.setName(gardenHardwareController.getName());
        result.setSerialNumber(gardenHardwareController.getSerialNumber());
        return result;
    }
}
