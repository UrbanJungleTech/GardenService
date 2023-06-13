package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.entity.PlantEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantConverterImpl implements PlantConverter{

    @Override
    public Plant toModel(PlantEntity plantEntity) {
        Plant result = new Plant();
        result.setId(plantEntity.getId());
        result.setName(plantEntity.getName());
        result.setCreated(plantEntity.getCreated());
        result.setSpecies(plantEntity.getSpecies());
        GardenEntity gardenEntity = plantEntity.getGarden();
        if(gardenEntity != null) {
            result.setGardenId(gardenEntity.getId());
        }
        return result;
    }

    @Override
    public List<Plant> toModels(List<PlantEntity> plants) {
        return plants.parallelStream().map((plantEntity -> this.toModel(plantEntity))).collect(Collectors.toList());
    }

    @Override
    public void fillEntity(PlantEntity plantEntity, Plant plant) {
        plantEntity.setName(plant.getName());
        plantEntity.setSpecies(plant.getSpecies());
        plantEntity.setCreated(plant.getCreated());
    }
}
