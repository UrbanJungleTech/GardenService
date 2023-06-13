package frentz.daniel.plants.service;

import frentz.daniel.hardwareservice.client.model.HardwareState;
import frentz.daniel.hardwareservice.client.service.HardwareClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HardwareServiceImplTest {
    @Mock
    private HardwareClient hardwareClient;
    @InjectMocks
    private HardwareServiceImpl hardwareServiceImpl;

    /**
     * When the setHardwareState method is called on the HardwareServiceImpl with a valid HardwareState:
     * 1. The hardwareClient.setHardwareState method is called with the HardwareState
     * 2. The hardwareClient.setHardwareState method is called exactly once
     * 3. The setHardwareState method should return a HardwareState.
     * 4. The HardwareState should be the same as the HardwareState which was returned by the hardwareClient.setHardwareState method.
     */
    @Test
    void setHardwareState() {
        HardwareState requestHardwareState = new HardwareState();
        HardwareState responseHardwareState = new HardwareState();

        when(this.hardwareClient.setHardwareState(anyLong(), same(requestHardwareState))).thenReturn(responseHardwareState);

        HardwareState result = this.hardwareServiceImpl.setHardwareState(1L, requestHardwareState);

        assertSame(responseHardwareState, result);
        verify(this.hardwareClient, times(1)).setHardwareState(anyLong(), same(requestHardwareState));
    }
}
