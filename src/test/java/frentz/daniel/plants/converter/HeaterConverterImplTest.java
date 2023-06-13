package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Heater;
import frentz.daniel.hardwareservice.client.model.Hardware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HeaterConverterImplTest {

    @InjectMocks
    private HeaterConverterImpl heaterConverter;
    @Test
    void getHardwareCategory() {
        assertEquals("HEATER", heaterConverter.getHardwareCategory());
    }

    @Test
    void toSpecificHardware_whenGivenAHardware_shouldReturnAHeaterWithFieldsSetFromMetadataAndMapOtherFields() {
        int expectedPower = 100;
        String expectedType = "HEATER";

        Hardware hardware = new Hardware();
        hardware.setType(expectedType);
        hardware.getMetadata().put("power", String.valueOf(expectedPower));

        Heater heater = heaterConverter.toSpecificHardware(hardware);

        assertEquals(expectedPower, heater.getPower());
        assertEquals(expectedType, heater.getHardwareCategory());
    }

    @Test
    void toHardware_whenGivenAHeater_shouldReturnAHardwareWithMetadataSet() {
        int expectedPower = 100;
        String expectedType = "HEATER";

        Heater heater = new Heater();
        heater.setPower(expectedPower);

        Hardware hardware = heaterConverter.toHardware(heater);

        assertEquals(expectedType, hardware.getType());
        assertEquals(expectedPower, Integer.valueOf(hardware.getMetadata().get("power")));
    }
}
