package frentz.daniel.plants.dao;

import frentz.daniel.plants.entity.PlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
}
