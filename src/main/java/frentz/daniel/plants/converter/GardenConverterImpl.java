package frentz.daniel.plants.converter;

import frentz.daniel.controllerclient.service.HardwareClient;
import frentz.daniel.model.Garden;
import frentz.daniel.model.GardenHardwareController;
import frentz.daniel.plants.entity.GardenEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GardenConverterImpl implements GardenConverter{

    private PlantConverter plantConverter;
    private HardwareClient hardwareClient;
    private GardenHardwareControllerConverter gardenHardwareControllerConverter;

    public GardenConverterImpl(PlantConverter plantConverter,
                               HardwareClient hardwareClient,
                               GardenHardwareControllerConverter gardenHardwareControllerConverter){
        this.plantConverter = plantConverter;
        this.hardwareClient = hardwareClient;
        this.gardenHardwareControllerConverter = gardenHardwareControllerConverter;
    }

    @Override
    public Garden toModel(GardenEntity gardenEntity, boolean fetchHardwareControllers) {
        Garden result = new Garden();
        result.setId(gardenEntity.getId());
        result.setName(gardenEntity.getName());
        result.setDescription(gardenEntity.getDescription());
        result.setPlants(plantConverter.toModels(gardenEntity.getPlants()));
        if (gardenEntity.getControllerId() != 0) {
            GardenHardwareController gardenHardwareController = this.gardenHardwareControllerConverter.toModel(gardenEntity.getControllerId());
            result.setHardwareController(gardenHardwareController);
        }

        return result;
    }

    @Override
    public List<Garden> toModels(List<GardenEntity> gardenEntities) {
        List<Garden> result = new ArrayList<>();
        return gardenEntities.parallelStream().map((gardenEntity -> this.toModel(gardenEntity, true))).collect(Collectors.toList());
    }
}
