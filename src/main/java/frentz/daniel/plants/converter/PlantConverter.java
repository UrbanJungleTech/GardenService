package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.entity.PlantEntity;

import java.util.List;

public interface PlantConverter{
    Plant toModel(PlantEntity plantEntity);
    List<Plant> toModels(List<PlantEntity> plants);
}
