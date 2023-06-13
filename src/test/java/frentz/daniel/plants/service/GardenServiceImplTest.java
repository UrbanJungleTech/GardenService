package frentz.daniel.plants.service;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.plants.converter.GardenConverter;
import frentz.daniel.plants.converter.GardenHardwareControllerConverter;
import frentz.daniel.plants.entity.GardenEntity;
import frentz.daniel.plants.repository.GardenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GardenServiceImplTest {
    @Mock
    GardenRepository gardenRepository;
    @Mock
    GardenConverter gardenConverter;
    @Mock
    PlantService plantService;
    @Mock
    HardwareControllerService hardwareControllerService;
    @Mock
    GardenHardwareControllerConverter gardenHardwareControllerConverter;
    @Mock
    HardwareService hardwareService;
    @Mock
    SensorService sensorService;

    @InjectMocks
    private GardenServiceImpl gardenService;
    @Test
    void whenGetGardenIsCalled_withAGardenId_itShouldCallTheGardenRepositoryToGetTheCorrectGardenEntity_thenConvertToGarden() {
        GardenEntity gardenEntity = new GardenEntity();
        gardenEntity.setId(1L);
        when(gardenRepository.findById(anyLong())).thenReturn(Optional.of(gardenEntity));
        Garden garden = new Garden();
        when(gardenConverter.toModel(same(gardenEntity), anyBoolean())).thenReturn(garden);

        Garden result = gardenService.getGarden(1L);

        verify(gardenRepository).findById(1L);
        assertEquals(garden, result);
    }

    @Test
    void getGardens_shouldCallTheGardenRepository_thenReturnAllGardens() {

    }
}
