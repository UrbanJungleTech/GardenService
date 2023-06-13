package frentz.daniel.plants.converter;

import frentz.daniel.garden.model.GardenHardware;
import frentz.daniel.garden.model.HardwareDetails;
import frentz.daniel.hardwareservice.client.model.Hardware;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HardwareDetailsConverterImplTest {

    @Mock
    private GardenHardwareConverter hardwareConverter;
    @InjectMocks
    private HardwareDetailsConverterImpl hardwareDetailsConverter;
    @Test
    /**
     * Given a single Hardware:
     * 1. the hardwareDetailsConverter's toHardwareDetails method should be called.
     * 2. the hardwareDetailsConverter toHardwareDetails method should call the gardenHardwareConverter toModel method
     * 3. the hardware should be passed into the toModel method
     * 4. the toHardware method should return a HardwareDetails object
     * 5. the resulting HardwareDetails object should have a single hardware in it, of type LIGHT
     */
    void toHardwareDetails() {
        Hardware hardware = new Hardware();

        GardenHardware gardenHardware = new GardenHardware();

        when(this.hardwareConverter.toModel(same(hardware))).thenReturn(gardenHardware);

        HardwareDetails result = this.hardwareDetailsConverter.toHardwareDetails(List.of(hardware));

        assertEquals(1, result.getHardware().size());
        assertSame(gardenHardware, result.getHardware().get(0));
        verify(this.hardwareConverter).toModel(eq(hardware));
        verify(this.hardwareConverter, times(1)).toModel(eq(hardware));
    }


    /**
     * Given a HardwareDetails with a single light:
     * 1. the hardwaredetailsconverter's toHardware method should be called.
     * 2. the hardwareDetailsConverter should call the garden hardware converter's toHardware method
     * 3. the light should be passed into the toHardware method
     * 4. the toHardware method should return a list with a single hardware in it
     * 5. the resulting hardware should have its fields filled to match the light object
     */
    @Test
    void toHardwareWithSingleHardware() {
        HardwareDetails hardwareDetails = new HardwareDetails();
        GardenHardware light = new GardenHardware();
        light.setId(1L);
        light.setName("light");
        light.setHardwareCategory("LIGHT");
        light.setPort(1L);
        hardwareDetails.addHardware(light);

        Hardware hardware = new Hardware();

        when(this.hardwareConverter.toHardware(eq(light))).thenReturn(hardware);

        List<Hardware> result = this.hardwareDetailsConverter.toHardware(hardwareDetails);

        assertEquals(1, result.size());
        assertSame(hardware, result.get(0));
        verify(this.hardwareConverter).toHardware(eq(light));
        verify(this.hardwareConverter, times(1)).toHardware(eq(light));
    }
}
