package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Sensor;
import frentz.daniel.model.GardenSensor;
import frentz.daniel.model.SensorDetails;
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
            switch(sensor.getSensorType()){
                case "moisture":
                    result.addMoisture(gardenSensor);
                    break;
                case "temperature":
                    result.addTemperature(gardenSensor);
                    break;
            }
        }
        return result;
    }

    @Override
    public List<Sensor> toSensors(SensorDetails sensorDetails) {
        List<Sensor> result = new ArrayList<>();
        List<Sensor> moistureSensors = this.sensorConverter.toSensors(sensorDetails.getMoisture());
        result.addAll(moistureSensors);
        List<Sensor> temperatureSensors = this.sensorConverter.toSensors(sensorDetails.getTemperature());
        result.addAll(temperatureSensors);
        return result;
    }
}
