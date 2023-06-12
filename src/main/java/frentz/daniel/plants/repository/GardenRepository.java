package frentz.daniel.plants.repository;

import frentz.daniel.plants.entity.GardenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenRepository extends JpaRepository<GardenEntity, Long> {
}
