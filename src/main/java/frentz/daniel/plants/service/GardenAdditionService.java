package frentz.daniel.plants.service;

import frentz.daniel.model.Garden;
import frentz.daniel.model.Plant;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface GardenAdditionService {
    Garden addGarden(Garden garden);
    void addSubscriber(SseEmitter sseEmitter);
    void addPlant(long gardenId, Plant plant);
    Garden removePlant(long gardenId, long plantId);
    void deleteGarden(long gardenId);
}
