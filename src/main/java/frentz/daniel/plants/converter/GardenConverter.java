package frentz.daniel.plants.converter;

import frentz.daniel.model.Garden;
import frentz.daniel.plants.entity.GardenEntity;

import java.util.List;

public interface GardenConverter {
    Garden toModel(GardenEntity gardenEntity, boolean fetchHardwareControllers);
    List<Garden> toModels(List<GardenEntity> gardenEntities);
}
