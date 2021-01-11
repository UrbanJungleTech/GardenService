package frentz.daniel.plants.converter;

import frentz.daniel.plants.entity.PlantEntity;
import frentz.daniel.plants.model.Plant;

import java.util.List;

public interface PlantConverter{
    Plant toModel(PlantEntity plantEntity);
    List<Plant> toModels(List<PlantEntity> plants);
}
