package frentz.daniel.plants.dao;

import frentz.daniel.plants.entity.GardenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenRepository extends JpaRepository<GardenEntity, Long> {
}
