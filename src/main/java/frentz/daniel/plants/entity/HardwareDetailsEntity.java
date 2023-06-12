package frentz.daniel.plants.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class HardwareDetailsEntity {
    @Id
    private long id;
    @OneToMany
    private List<HardwareTimerEntity> timers;

    public List<HardwareTimerEntity> getTimers() {
        return timers;
    }

    public void setTimers(List<HardwareTimerEntity> timers) {
        this.timers = timers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
