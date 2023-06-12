package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.garden.model.HardwareDetails;

import java.util.List;

public interface HardwareDetailsConverter {
    public HardwareDetails toModel(List<Hardware> hardwares);
    public List<Hardware> toHardware(HardwareDetails hardwareDetails);
}
