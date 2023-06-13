package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.GardenHardwareController;
import frentz.daniel.hardwareservice.client.service.HardwareClient;
import frentz.daniel.plants.entity.GardenEntity;
import org.springframework.stereotype.Service;

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
    public Garden toGarden(GardenEntity gardenEntity) {
        Garden result = new Garden();
        result.setId(gardenEntity.getId());
        result.setName(gardenEntity.getName());
        result.setDescription(gardenEntity.getDescription());
        result.setPlants(plantConverter.toModels(gardenEntity.getPlants()));
        if (gardenEntity.getControllerId() != 0) {
            GardenHardwareController gardenHardwareController = this.gardenHardwareControllerConverter.toGardenHardwareController(gardenEntity.getControllerId());
            result.setHardwareController(gardenHardwareController);
        }

        return result;
    }

    @Override
    public List<Garden> toGardens(List<GardenEntity> gardenEntities) {
        return gardenEntities.parallelStream().map((gardenEntity -> this.toGarden(gardenEntity))).collect(Collectors.toList());
    }

    @Override
    public void fillGardenEntity(GardenEntity gardenEntity, Garden garden) {
        gardenEntity.setDescription(garden.getDescription());
        gardenEntity.setName(garden.getName());
    }
}
