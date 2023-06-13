package frentz.daniel.plants.converter;

import frentz.daniel.hardwareservice.client.service.HardwareClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GardenHardwareControllerConverterImplTest {

    @Mock
    private HardwareClient hardwareClient;
    private HardwareDetailsConverter hardwareDetailsConverter;
    @Mock
    private SensorDetailsConverter sensorDetailsConverter;
    @Mock
    private GardenHardwareControllerConverterImpl gardenHardwareControllerConverter;

    /**
     *
     */
    @Test
    void toGardenHardwareController_mapAllFields() {
    }

    @Test
    void toHardwareController() {
    }
}
