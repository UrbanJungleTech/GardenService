package frentz.daniel.plants.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.Plant;
import frentz.daniel.plants.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GardenAdditionServiceImplTest {
    @Mock
    private GardenService gardenService;
    @Mock
    private List<SseEmitter> subscribers;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private GardenAdditionServiceImpl gardenAdditionService;

    /**
     * Given a valid garden when addGarden is called then:
     * 1. The gardenService.create method is called with the garden
     * 2. The result of the gardenService.create method is returned
     */
    @Test
    public void addGardenTest() {
        Garden requestGarden = new Garden();
        Garden responseGarden = new Garden();
        when(gardenService.create(requestGarden)).thenReturn(responseGarden);

        Garden result = gardenAdditionService.addGarden(requestGarden);

        verify(gardenService, times(1)).create(requestGarden);
        assertSame(responseGarden, result);
    }

    /**
     * Given a valid Plant and a gardenId
     * when addPlant is called then:
     * 1. The gardenService.addPlant method is called with the gardenId and the Plant
     * 2. The result of the gardenService.addPlant method is returned
     */
    @Test
    public void addPlantTest() {
        Plant requestPlant = new Plant();
        long gardenId = 1L;
        Plant responsePlant = new Plant();
        when(gardenService.addPlant(gardenId, requestPlant)).thenReturn(responsePlant);

        Plant result = gardenAdditionService.addPlant(gardenId, requestPlant);

        verify(gardenService, times(1)).addPlant(gardenId, requestPlant);
        assertSame(responsePlant, result);
    }

    /**
     * Given a valid gardenId
     * When the deleteGarden method is called then:
     * 1. The gardenService.delete method is called with the gardenId
     */
    @Test
    public void deleteGardenTest() {
        long gardenId = 1L;

        gardenAdditionService.deleteGarden(gardenId);

        verify(gardenService, times(1)).deleteGarden(gardenId);
    }

    /**
     * Given an invalid gardenid
     * When the deleteGarden method is called then:
     * 1. The gardenService.delete method is called with the gardenId
     * 2. The gardenService.delete method throws an exception
     */
    @Test
    public void deleteGardenExceptionTest() {
        long gardenId = 1L;
        doThrow(new NotFoundException(Garden.class, gardenId)).when(gardenService).deleteGarden(gardenId);

        assertThrows(NotFoundException.class, () -> gardenService.deleteGarden(gardenId));

        verify(gardenService, times(1)).deleteGarden(gardenId);

    }

}
