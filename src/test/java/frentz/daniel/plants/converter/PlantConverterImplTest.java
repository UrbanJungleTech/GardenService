package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.entity.PlantEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlantConverterImplTest {

    @InjectMocks
    private PlantConverterImpl plantConverter;

    @Test
    void toModel_whenTheGardenIsNull_shouldSetTheGardenIdToNull(){
        PlantEntity plantEntity = new PlantEntity();
        plantEntity.setGarden(null);
        Plant result = this.plantConverter.toModel(plantEntity);
        assertNull(result.getGardenId());
    }

    @Test
    void toModel_whenGivenAPlantEntity_shouldReturnAPlant() {
        PlantEntity plantEntity = new PlantEntity();
        String name = "test name";
        String species = "test species";
        LocalDateTime created = LocalDateTime.now();
        Long id = 1L;
        GardenEntity gardenEntity = new GardenEntity();
        Long gardenId = 2L;

        plantEntity.setGarden(gardenEntity);
        gardenEntity.setId(gardenId);
        plantEntity.setId(id);
        plantEntity.setName(name);
        plantEntity.setSpecies(species);
        plantEntity.setCreated(created);

        Plant result = this.plantConverter.toModel(plantEntity);

        assertEquals(name, result.getName());
        assertEquals(species, result.getSpecies());
        assertEquals(created, result.getCreated());
        assertEquals(id, result.getId());
        assertEquals(gardenId, result.getGardenId());
    }

    @Test
    void toModels_whenGivenAListOf1PlantEntity_shouldReturnAListOf1Plant(){
        PlantEntity plantEntity = new PlantEntity();
        String name = "test name";
        String species = "test species";
        LocalDateTime created = LocalDateTime.now();
        Long id = 1L;
        GardenEntity gardenEntity = new GardenEntity();
        Long gardenId = 2L;

        plantEntity.setGarden(gardenEntity);
        gardenEntity.setId(gardenId);
        plantEntity.setId(id);
        plantEntity.setName(name);
        plantEntity.setSpecies(species);
        plantEntity.setCreated(created);

        Plant result = this.plantConverter.toModels(List.of(plantEntity)).get(0);

        assertEquals(name, result.getName());
        assertEquals(species, result.getSpecies());
        assertEquals(created, result.getCreated());
        assertEquals(id, result.getId());
        assertEquals(gardenId, result.getGardenId());
    }

    @Test
    void toModels_whenGivenAListOf2PlantEntities_shouldReturnAListOf2Plants(){
        PlantEntity plantEntity1 = new PlantEntity();
        String name1 = "test name 1";
        String species1 = "test species 1";
        LocalDateTime created1 = LocalDateTime.now();
        Long id1 = 1L;
        GardenEntity gardenEntity1 = new GardenEntity();
        Long gardenId1 = 2L;

        plantEntity1.setGarden(gardenEntity1);
        gardenEntity1.setId(gardenId1);
        plantEntity1.setId(id1);
        plantEntity1.setName(name1);
        plantEntity1.setSpecies(species1);
        plantEntity1.setCreated(created1);

        PlantEntity plantEntity2 = new PlantEntity();
        String name2 = "test name 2";
        String species2 = "test species 2";
        LocalDateTime created2 = LocalDateTime.now();
        Long id2 = 3L;
        GardenEntity gardenEntity2 = new GardenEntity();
        Long gardenId2 = 4L;

        plantEntity2.setGarden(gardenEntity2);
        gardenEntity2.setId(gardenId2);
        plantEntity2.setId(id2);
        plantEntity2.setName(name2);
        plantEntity2.setSpecies(species2);
        plantEntity2.setCreated(created2);

        Plant result1 = this.plantConverter.toModels(List.of(plantEntity1, plantEntity2)).get(0);
        Plant result2 = this.plantConverter.toModels(List.of(plantEntity1, plantEntity2)).get(1);

        assertEquals(name1, result1.getName());
        assertEquals(species1, result1.getSpecies());
        assertEquals(created1, result1.getCreated());
        assertEquals(id1, result1.getId());
        assertEquals(gardenId1, result1.getGardenId());

        assertEquals(name2, result2.getName());
        assertEquals(species2, result2.getSpecies());
        assertEquals(created2, result2.getCreated());
        assertEquals(id2, result2.getId());
        assertEquals(gardenId2, result2.getGardenId());
    }

    @Test
    void fillEntity_whenGivenAEmptyEntityAndAPlant_shouldFillTheEntityExceptionTheId() {
        Plant plant = new Plant();
        String name = "test name";
        String species = "test species";
        LocalDateTime created = LocalDateTime.now();
        Long plantId = 1L;

        plant.setName(name);
        plant.setId(plantId);
        plant.setSpecies(species);
        plant.setCreated(created);

        PlantEntity plantEntity = new PlantEntity();
        Long plantEntityId = 3L;
        plantEntity.setId(plantEntityId);

        this.plantConverter.fillEntity(plantEntity, plant);

        assertEquals(name, plantEntity.getName());
        assertEquals(species, plantEntity.getSpecies());
        assertEquals(created, plantEntity.getCreated());
        assertEquals(plantEntityId, plantEntity.getId());
    }
}
