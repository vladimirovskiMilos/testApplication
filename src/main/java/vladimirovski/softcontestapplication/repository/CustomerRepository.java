package vladimirovski.softcontestapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vladimirovski.softcontestapplication.entity.CustomerEntity;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT * FROM CUSTOMER WHERE first_name LIKE %:name% OR last_name LIKE %:name%",
            nativeQuery = true)
    List<CustomerEntity> findByFirstNameAndLastName(@Param("name")String name);
}
