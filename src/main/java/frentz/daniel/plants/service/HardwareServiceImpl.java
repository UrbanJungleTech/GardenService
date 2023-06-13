package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.hardwareservice.client.service.HardwareClient;
import org.springframework.stereotype.Service;

@Service
public class HardwareServiceImpl implements HardwareService{

    private HardwareClient hardwareClient;

    public HardwareServiceImpl(HardwareClient hardwareClient){
        this.hardwareClient = hardwareClient;
    }

    @Override
    public HardwareState setHardwareState(long hardwareId, HardwareState hardwareState) {
        return this.hardwareClient.setHardwareState(hardwareId, hardwareState);
    }
}
