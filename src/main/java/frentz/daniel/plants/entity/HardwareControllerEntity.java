package frentz.daniel.plants.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HardwareControllerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //this is the remote id.
    private long controllerId;

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
}
