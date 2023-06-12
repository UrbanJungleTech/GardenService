package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.model.GardenHardware;
import frentz.daniel.model.Water;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterConverterImpl implements SpecificGardenHardwareConverter{

    @Override
    public String getHardwareCategory() {
        return "WATER";
    }

    @Override
    public GardenHardware toModel(Hardware hardware) {
        Water result = new Water();
        result.setPressure(Long.valueOf(hardware.getMetadata().get("pressure")));
        return result;
    }

    @Override
    public List<GardenHardware> toModels(List<Hardware> hardwares) {
        return hardwares.stream().map((hardware) -> {
            return this.toModel(hardware);
        }).collect(Collectors.toList());
    }

    @Override
    public Hardware toHardware(GardenHardware hardware) {
        Hardware result = new Hardware();
        Water water = (Water)hardware;
        result.getMetadata().put("pressure", String.valueOf(water.getPressure()));
        result.setType("WATER");
        return result;
    }
}
