package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.model.Details;
import frentz.daniel.model.Heater;

import java.util.List;

public interface HeaterDetailsConverter {
    Details<Heater> toModel(List<Hardware> hardwares);
}
