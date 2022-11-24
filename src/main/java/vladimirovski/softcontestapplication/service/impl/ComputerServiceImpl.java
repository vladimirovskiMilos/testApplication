package vladimirovski.softcontestapplication.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vladimirovski.softcontestapplication.dto.ComputerDto;
import vladimirovski.softcontestapplication.entity.ComputerEntity;
import vladimirovski.softcontestapplication.exception.NonExistingIdException;
import vladimirovski.softcontestapplication.mapper.ComputerMapper;
import vladimirovski.softcontestapplication.repository.ComputerRepository;
import vladimirovski.softcontestapplication.service.ComputerService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ComputerServiceImpl implements ComputerService {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;
    @Override
    public Page<ComputerDto> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<ComputerDto> computers = computerRepository.findAll(pageable).map(computerMapper::toDto);

        return computers;
    }

    @Override
    public ComputerDto save(ComputerDto computer) throws EntityExistsException {

        return computerMapper.toDto(computerRepository.save(computerMapper.toEntityNoId(computer)));
    }

    @Override
    public Optional<ComputerDto> findById(Long id) throws NonExistingIdException {

        Optional<ComputerEntity> computer = computerRepository.findById(id);
        if(computer.isEmpty()){
            log.error("We catch error in service! User try to find computer with wrong ID {}, ", id);

            throw new NonExistingIdException("Sorry incorrect ID '" + id + "'");
        }
        log.info(" User call method 'findById' from service successfully.  "+
                        "\n Application send information for computer: {}" ,
                computer.get().getName());

        return computer.map(computerMapper::toDto)  ;
    }

    @Override
    public void delete(Long id) throws NonExistingIdException {
        Optional<ComputerEntity> computer = computerRepository.findById(id);

        if(computer.isEmpty()){
            log.error("\nWe catch error in service! User try to delete computer with wrong ID {}, ", id);
            throw new NonExistingIdException("Incorrect computer ID ' " + id + " '");
        }
        log.info("Deleted computer  {}  from database", computer.get().getName());
        computerRepository.delete(computer.get());
    }

    @Override
    public ComputerDto edit(ComputerDto computer) throws NonExistingIdException {

        if(computerRepository.existsById(computer.getId())){
            return computerMapper.toDto(computerRepository.save(computerMapper.toEntity(computer)));
        }
        log.error("\nWe catch error in service. User try to edit computer with wrong ID {} ", computer.getId());
        throw new NonExistingIdException("Computer with name ' " + computer.getName() + " ' does not exist.");

    }

    @Override
    public List<ComputerDto> findAllComputers() {
        List<ComputerEntity> computers = computerRepository.findAll();

        return computers.stream().map(computerMapper::toDto).collect(Collectors.toList());
    }
}
