package frentz.daniel.plants.service;

import frentz.daniel.garden.model.Garden;
import frentz.daniel.garden.model.Plant;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface GardenAdditionService {
    Garden addGarden(Garden garden);
    void addSubscriber(SseEmitter sseEmitter);
    Plant addPlant(long gardenId, Plant plant);
    void deleteGarden(long gardenId);
}
