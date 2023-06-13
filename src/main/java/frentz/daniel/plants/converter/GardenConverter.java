package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.plants.entity.GardenEntity;

import java.util.List;

public interface GardenConverter {
    Garden toGarden(GardenEntity gardenEntity);
    List<Garden> toGardens(List<GardenEntity> gardenEntities);
    void fillGardenEntity(GardenEntity gardenEntity, Garden garden);
}
