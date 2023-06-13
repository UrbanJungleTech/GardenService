package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.Water;
import frentz.daniel.hardwareservice.client.model.Hardware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WaterConverterImplTest {
    @InjectMocks
    private WaterConverterImpl waterConverter;

    @Test
    /**
     * Test that when the toSpecificHardware method is called on waterConverter and passed a Hardware object it:
     * 1. Returns a Water object
     * 2. The Water object has pressure field filled by the hardware metadata
     * 3. The Water object has the common hardware fields filled by the hardware fields
     */
    public void testToSpecificHardware() {
        String hardwareType = "WATER";
        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.getMetadata().put("pressure", "1");
        hardware.setType(hardwareType);
        hardware.setName("testName");

        Water water = waterConverter.toSpecificHardware(hardware);

        assertEquals(Long.valueOf(hardware.getMetadata().get("pressure")), water.getPressure());
        assertEquals(hardwareType, water.getHardwareCategory());
        assertEquals(hardware.getName(), water.getName());
        assertEquals(hardware.getId(), water.getId());
    }

    @Test
    /**
     * Test that when the toHardware method is called on waterConverter and passed a Water object it:
     * 1. Returns a Hardware object
     * 2. The Hardware object has pressure added to the metadata.
     * 3. The Hardware object has the name, type and id filled by the water objects fields.
     */
    void testToHardware(){
        Water water = new Water();
        water.setPressure(1);
        water.setHardwareCategory("WATER");
        water.setName("testName");
        water.setId(1L);

        Hardware hardware = waterConverter.toHardware(water);

        assertEquals(String.valueOf(water.getPressure()), hardware.getMetadata().get("pressure"));
        assertEquals(water.getName(), hardware.getName());
        assertEquals(water.getHardwareCategory(), hardware.getType());
        assertEquals(water.getId(), hardware.getId());
    }

    @Test
    /**
     * Test that when the getHardwareCategory method is called on waterConverter it returns "WATER"
     */
    void testGetHardwareCategory() {
        assertEquals("WATER", waterConverter.getHardwareCategory());
    }
}
