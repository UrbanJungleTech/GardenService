package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.model.Sensor;
import frentz.daniel.model.GardenSensor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorConverterImpl implements SensorConverter{
    @Override
    public GardenSensor toGardenSensor(Sensor sensor) {
        GardenSensor result = new GardenSensor();
        result.setSensorType(sensor.getSensorType());
        result.setPort(sensor.getPort());
        result.setId(sensor.getId());
        return result;
    }

    @Override
    public Sensor toSensor(GardenSensor gardenSensor) {
        Sensor result = new Sensor();
        result.setPort(gardenSensor.getPort());
        result.setId(gardenSensor.getId());
        result.setSensorType(gardenSensor.getSensorType());
        return result;
    }

    @Override
    public List<Sensor> toSensors(List<GardenSensor> gardenSensors) {
        return gardenSensors.stream().map((sensor) -> {
            return this.toSensor(sensor);
        }).collect(Collectors.toList());
    }
}
