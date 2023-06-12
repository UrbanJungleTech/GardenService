package frentz.daniel.plants.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import frentz.daniel.model.Garden;
import frentz.daniel.model.Plant;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Service
public class GardenAdditionServiceImpl implements GardenAdditionService{

    private GardenService gardenService;
    private List<SseEmitter> subscribers;
    private ObjectMapper objectMapper;

    public GardenAdditionServiceImpl(GardenService gardenService, ObjectMapper objectMapper){
        this.gardenService = gardenService;
        this.subscribers = new ArrayList<>();
        this.objectMapper = objectMapper;
    }

    @Override
    public Garden addGarden(Garden garden) {
        Garden result = this.gardenService.create(garden);
        this.sendToSubscribers(result, "gardenAdded");
        return result;
    }

    private void sendToSubscribers(Object data, String topic) {
        try {
        String json = this.objectMapper.writeValueAsString(data);
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .data(json, MediaType.APPLICATION_JSON)
                .name(topic);
        for(SseEmitter subscriber : this.subscribers) {
                subscriber.send(event);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void addSubscriber(SseEmitter sseEmitter) {
        this.subscribers.add(sseEmitter);
        sseEmitter.onCompletion(() -> {
            this.subscribers.remove(sseEmitter);
        });
    }

    @Override
    public void addPlant(long gardenId, Plant plant) {
        Garden garden = this.gardenService.addPlant(gardenId, plant);
        this.sendToSubscribers(garden, "plantAdded");
    }

    @Override
    public Garden removePlant(long gardenId, long plantId) {
        Garden garden = this.gardenService.removePlant(gardenId, plantId);
        this.sendToSubscribers(garden, "plantRemoved");
        return garden;
    }

    @Override
    public void deleteGarden(long gardenId) {
        this.gardenService.deleteGarden(gardenId);
        this.sendToSubscribers(new Long(gardenId), "gardenRemoved");
    }
}
