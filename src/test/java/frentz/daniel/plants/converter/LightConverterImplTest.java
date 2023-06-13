package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Light;
import frentz.daniel.hardwareservice.client.model.Hardware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LightConverterImplTest {

    @InjectMocks
    private LightConverterImpl lightConverter;


    @Test
    void toHardware_whenGivenALight_shouldReturnAHardwareWithLightAsTypeAndPowerAndColorSet(){
        String colour = "red";
        int power = 100;
        String hardwareType = "LIGHT";
        Light light = new Light();
        light.setPower(power);
        light.setColour(colour);
        Hardware result = this.lightConverter.toHardware(light);
        assertEquals(hardwareType, result.getType());
        assertEquals(String.valueOf(power), result.getMetadata().get("power"));
        assertEquals(colour, result.getMetadata().get("colour"));
    }

    @Test
    void getHardwareCategory_shouldReturnLight() {
        String expectedValue = "LIGHT";
        assertEquals(expectedValue, this.lightConverter.getHardwareCategory());
    }

    @Test
    void toSpecificHardware_whenGivenAHardware_shouldReturnALight() {
        String colour = "red";
        int power = 100;
        String hardwareType = "LIGHT";
        Hardware hardware = new Hardware();
        hardware.setType(hardwareType);
        hardware.getMetadata().put("power", String.valueOf(power));
        hardware.getMetadata().put("colour", colour);

        Light result = this.lightConverter.toSpecificHardware(hardware);

        assertEquals(power, result.getPower());
        assertEquals(colour, result.getColour());
    }
}
