package vladimirovski.softcontestapplication.service;

import org.springframework.data.domain.Page;
import vladimirovski.softcontestapplication.dto.ComputerDto;
import vladimirovski.softcontestapplication.exception.NonExistingIdException;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

public interface ComputerService {

    Page<ComputerDto> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ComputerDto save(ComputerDto computer) throws EntityExistsException;

    Optional<ComputerDto> findById(Long id) throws NonExistingIdException;

    void delete(Long id) throws NonExistingIdException;

    ComputerDto edit(ComputerDto computer) throws NonExistingIdException;

    List<ComputerDto> findAllComputers();
}
