package frentz.daniel.plants.entity;


import jakarta.persistence.*;

@Entity
public class HardwareControllerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long controllerId;
    @OneToOne
    private HardwareDetailsEntity lightDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getControllerId() {
        return controllerId;
    }

    public void setControllerId(long controllerId) {
        this.controllerId = controllerId;
    }

    public HardwareDetailsEntity getLightDetails() {
        return lightDetails;
    }

    public void setLightDetails(HardwareDetailsEntity lightDetails) {
        this.lightDetails = lightDetails;
    }
}
