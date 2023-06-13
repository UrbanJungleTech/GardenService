package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.hardwareservice.client.model.HardwareController;
import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.hardwareservice.client.service.HardwareClient;
import frentz.daniel.garden.model.GardenHardwareController;
import frentz.daniel.garden.model.HardwareDetails;
import frentz.daniel.garden.model.SensorDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenHardwareControllerConverterImpl implements GardenHardwareControllerConverter{

    private HardwareClient hardwareClient;
    private HardwareDetailsConverter hardwareDetailsConverter;
    private SensorDetailsConverter sensorDetailsConverter;

    public GardenHardwareControllerConverterImpl(HardwareClient hardwareClient,
                                                 HardwareDetailsConverter hardwareDetailsConverter,
                                                 SensorDetailsConverter sensorDetailsConverter){
        this.hardwareClient = hardwareClient;
        this.hardwareDetailsConverter = hardwareDetailsConverter;
        this.sensorDetailsConverter = sensorDetailsConverter;
    }

    @Override
    public GardenHardwareController toGardenHardwareController(long controllerId){
        GardenHardwareController result = new GardenHardwareController();
        HardwareController hardwareController = this.hardwareClient.getHardwareController(controllerId);

        result.setName(hardwareController.getName());
        result.setSerialNumber(hardwareController.getSerialNumber());
        result.setId(hardwareController.getId());

        HardwareDetails hardwareDetails = this.hardwareDetailsConverter.toHardwareDetails(hardwareController.getHardware());
        result.setHardwareDetails(hardwareDetails);

        SensorDetails sensorDetails = this.sensorDetailsConverter.toSensorDetails(hardwareController.getSensors());
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
