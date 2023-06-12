package frentz.daniel.plants.repository;

import frentz.daniel.plants.entity.HardwareControllerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenHardwareControllerRepository extends JpaRepository<HardwareControllerEntity, Long> {
}
