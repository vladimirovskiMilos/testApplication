package vladimirovski.softcontestapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vladimirovski.softcontestapplication.entity.ComputerEntity;

@Repository
public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {

}
