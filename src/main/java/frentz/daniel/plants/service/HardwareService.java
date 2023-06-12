package frentz.daniel.plants.service;

import frentz.daniel.controllerclient.model.Hardware;
import frentz.daniel.controllerclient.model.HardwareState;

public interface HardwareService {
    Hardware setHardwareState(long hardwareId, HardwareState hardwareState);
}
