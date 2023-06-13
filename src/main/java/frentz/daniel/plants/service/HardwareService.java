package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.model.Hardware;
import frentz.daniel.hardwareservice.client.model.HardwareState;

public interface HardwareService {
    HardwareState setHardwareState(long hardwareId, HardwareState hardwareState);
}
