package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.model.HardwareController;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.model.Garden;
import org.springframework.stereotype.Service;
import service.HardwareClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GardenConverterImpl implements GardenConverter{

    private PlantConverter plantConverter;
    private HardwareClient hardwareClient;

    public GardenConverterImpl(PlantConverter plantConverter, HardwareClient hardwareClient){
        this.plantConverter = plantConverter;
        this.hardwareClient = hardwareClient;
    }

    @Override
    public Garden toModel(GardenEntity gardenEntity, boolean fetchHardwareControllers) {
        Garden result = new Garden();
        result.setId(gardenEntity.getId());
        result.setName(gardenEntity.getName());
        result.setDescription(gardenEntity.getDescription());
        result.setPlants(plantConverter.toModels(gardenEntity.getPlants()));
        if(gardenEntity.getControllerId() != null) {
            result.setHardwareController(this.hardwareClient.getHardwareController(gardenEntity.getControllerId()));
        }
        return result;
    }

    @Override
    public List<Garden> toModels(List<GardenEntity> gardenEntities) {
        List<Garden> result = new ArrayList<>();
        return gardenEntities.parallelStream().map((gardenEntity -> this.toModel(gardenEntity, true))).collect(Collectors.toList());
    }
}
