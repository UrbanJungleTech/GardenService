package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.garden.model.Heater;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeaterConverterImpl implements SpecificGardenHardwareConverter<Heater>{

    @Override
    public String getHardwareCategory() {
        return "HEATER";
    }

    @Override
    public Heater toSpecificHardware(Hardware hardware) {
        Heater result = new Heater();
        result.setPower(Long.valueOf(hardware.getMetadata().get("power")));
        result.setTimers(hardware.getTimers());
        return result;
    }

    @Override
    public Hardware toHardware(Heater hardware) {
        Hardware result = new Hardware();
        Heater heater = (Heater)hardware;
        result.setType("HEATER");
        result.getMetadata().put("power", String.valueOf(heater.getPower()));
        return result;
    }
}
