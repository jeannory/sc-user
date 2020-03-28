package software.craftsmanship.scuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.craftsmanship.scuser.entities.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
}
