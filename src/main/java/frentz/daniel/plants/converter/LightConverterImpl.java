package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.garden.model.GardenHardware;
import frentz.daniel.garden.model.Light;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LightConverterImpl implements SpecificGardenHardwareConverter {
    @Override
    public List<GardenHardware> toModels(List<Hardware> hardwares) {
        return hardwares.stream().map((hardware) -> {
            return this.toModel(hardware);
        }).collect(Collectors.toList());
    }

    @Override
    public Hardware toHardware(GardenHardware hardware) {
        Hardware result = new Hardware();
        Light light = (Light)hardware;
        result.getMetadata().put("power", String.valueOf(light.getPower()));
        result.getMetadata().put("colour", light.getColour());
        result.setType("LIGHT");
        result.setTimers(light.getTimers());
        return result;
    }

    @Override
    public String getHardwareCategory() {
        return "LIGHT";
    }

    @Override
    public GardenHardware toModel(Hardware hardware) {
        Light result = new Light();
        result.setPower(Long.valueOf(hardware.getMetadata().getOrDefault("power", "0")));
        result.setColour(hardware.getMetadata().get("colour"));
        result.setTimers(hardware.getTimers());
        return result;
    }
}
