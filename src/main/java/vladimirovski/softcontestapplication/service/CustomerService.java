package vladimirovski.softcontestapplication.service;

import org.springframework.data.domain.Page;
import vladimirovski.softcontestapplication.dto.CustomerDto;
import vladimirovski.softcontestapplication.exception.NonExistingIdException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Page<CustomerDto> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CustomerDto save(CustomerDto computer) throws EntityExistsException;

    Optional<CustomerDto> findById(Long id) throws NonExistingIdException;

    void delete(Long id) throws NonExistingIdException;

    CustomerDto edit(CustomerDto computer) throws NonExistingIdException;

    List<CustomerDto> findAllCustomers();

    List<CustomerDto> findByFirstNameAndLastName(String name);
}
