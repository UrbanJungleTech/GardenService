package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.model.Hardware;
import frentz.daniel.model.GardenHardware;
import frentz.daniel.model.Heater;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeaterConverterImpl implements SpecificGardenHardwareConverter{

    @Override
    public String getHardwareCategory() {
        return "HEATER";
    }

    @Override
    public GardenHardware toModel(Hardware hardware) {
        Heater result = new Heater();
        result.setPower(Long.valueOf(hardware.getMetadata().get("power")));
        result.setTimers(hardware.getTimers());
        return result;
    }

    @Override
    public List<GardenHardware> toModels(List<Hardware> hardwares) {
        return hardwares.stream().map((heater) -> {
            return this.toModel(heater);
        }).collect(Collectors.toList());
    }

    @Override
    public Hardware toHardware(GardenHardware hardware) {
        Hardware result = new Hardware();
        Heater heater = (Heater)hardware;
        result.setType("HEATER");
        result.getMetadata().put("power", String.valueOf(heater.getPower()));
        return result;
    }
}
