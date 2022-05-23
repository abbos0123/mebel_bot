package uz.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findLocationById(Long id);
}
