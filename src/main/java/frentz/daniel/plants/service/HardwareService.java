package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.hardwareservice.client.model.HardwareState;

public interface HardwareService {
    Hardware setHardwareState(long hardwareId, HardwareState hardwareState);
}
