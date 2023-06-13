package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.garden.model.GardenSensor;
import frentz.daniel.garden.model.SensorDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDetailsConverterImpl implements SensorDetailsConverter{

    private SensorConverter sensorConverter;

    public SensorDetailsConverterImpl(SensorConverter sensorConverter){
        this.sensorConverter = sensorConverter;
    }

    @Override
    public SensorDetails toModel(List<Sensor> sensors) {
        SensorDetails result = new SensorDetails();
        for(Sensor sensor : sensors){
            GardenSensor gardenSensor = this.sensorConverter.toGardenSensor(sensor);
            result.addSensor(gardenSensor);
        }
        return result;
    }

    @Override
    public List<Sensor> toSensors(SensorDetails sensorDetails) {
        List<Sensor> result = this.sensorConverter.toSensors(sensorDetails.getSensors());
        return result;
    }
}
