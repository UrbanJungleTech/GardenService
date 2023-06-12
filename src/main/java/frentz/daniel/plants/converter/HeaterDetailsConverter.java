package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.garden.model.Details;
import frentz.daniel.garden.model.Heater;

import java.util.List;

public interface HeaterDetailsConverter {
    Details<Heater> toModel(List<Hardware> hardwares);
}
