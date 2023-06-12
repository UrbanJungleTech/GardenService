package frentz.daniel.plants.entity;

import frentz.daniel.controllerclient.model.Timer;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class HardwareTimerEntity {
    @Id
    private long id;
    private long timerId;

    public long getTimerId() {
        return timerId;
    }

    public void setTimerId(long timerId) {
        this.timerId = timerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
