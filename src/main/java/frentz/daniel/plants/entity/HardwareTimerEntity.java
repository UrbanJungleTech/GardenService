package frentz.daniel.plants.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
