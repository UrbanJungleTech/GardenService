package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.Hardware;
import frentz.daniel.controllerclient.model.HardwareState;
import frentz.daniel.controllerclient.service.HardwareClient;
import org.springframework.stereotype.Service;

@Service
public class HardwareServiceImpl implements HardwareService{

    private HardwareClient hardwareClient;

    public HardwareServiceImpl(HardwareClient hardwareClient){
        this.hardwareClient = hardwareClient;
    }

    @Override
    public Hardware setHardwareState(long hardwareId, HardwareState hardwareState) {
        return this.hardwareClient.setHardwareState(hardwareId, hardwareState);
    }
}
