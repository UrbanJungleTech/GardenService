package frentz.daniel.plants.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HardwareControllerEntityTest {

    @Test
    void getId() {
        HardwareControllerEntity hardwareControllerEntity = new HardwareControllerEntity();
        long expectedId = 1L;
        hardwareControllerEntity.setId(expectedId);
        long id = hardwareControllerEntity.getId();
        assertEquals(expectedId, id);
    }

    @Test
    void setId() {
        HardwareControllerEntity hardwareControllerEntity = new HardwareControllerEntity();
        long expectedId = 1L;
        hardwareControllerEntity.setId(expectedId);
        long id = hardwareControllerEntity.getId();
        assertEquals(expectedId, id);
    }

    @Test
    void getControllerId() {
        HardwareControllerEntity hardwareControllerEntity = new HardwareControllerEntity();
        long expectedControllerId = 1L;
        hardwareControllerEntity.setControllerId(expectedControllerId);
        long controllerId = hardwareControllerEntity.getControllerId();
        assertEquals(expectedControllerId, controllerId);
    }

    @Test
    void setControllerId() {
        HardwareControllerEntity hardwareControllerEntity = new HardwareControllerEntity();
        long expectedControllerId = 1L;
        hardwareControllerEntity.setControllerId(expectedControllerId);
        long controllerId = hardwareControllerEntity.getControllerId();
        assertEquals(expectedControllerId, controllerId);
    }
}
