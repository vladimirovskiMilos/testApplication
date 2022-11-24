package vladimirovski.softcontestapplication.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vladimirovski.softcontestapplication.dto.CustomerDto;
import vladimirovski.softcontestapplication.entity.CustomerEntity;
import vladimirovski.softcontestapplication.exception.NonExistingIdException;
import vladimirovski.softcontestapplication.mapper.CustomerMapper;
import vladimirovski.softcontestapplication.repository.CustomerRepository;
import vladimirovski.softcontestapplication.service.CustomerService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Page<CustomerDto> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

        Page<CustomerDto> customers = customerRepository.findAll(pageable).map(customerMapper::toDto);

        return customers;
    }

    @Override
    public CustomerDto save(CustomerDto computer) throws EntityExistsException {

        return customerMapper.toDto(customerRepository.save(customerMapper.toEntityNoId(computer)));
    }

    @Override
    public Optional<CustomerDto> findById(Long id) throws NonExistingIdException {

        Optional<CustomerEntity> customer = customerRepository.findById(id);

        if(customer.isEmpty()){
            log.error("We catch error in service! User try to find customer with wrong ID {}, ", id);

            throw new NonExistingIdException("Sorry incorrect ID '" + id + "'");

        }

        log.info(" User call method 'findById' from service successfully.  "+
               "\n Application send information for user: {}" ,
                 customer.get().getFirstName());


        return customer.map(customerMapper::toDto)  ;
    }

    @Override
    public void delete(Long id) throws NonExistingIdException {
        Optional<CustomerEntity> customer = customerRepository.findById(id);

        if(customer.isEmpty()){
            log.error("\nWe catch error in service! User try to delete customer with wrong ID {}, ", id);
            throw new NonExistingIdException("Incorrect customer ID ' " + id + " '");
        }
        log.info("Deleted customer  {} {} from database", customer.get().getFirstName() + " ",  customer.get().getLastName());
        customerRepository.delete(customer.get());
    }

    @Override
    public CustomerDto edit(CustomerDto customer) throws NonExistingIdException {

        if(customerRepository.existsById(customer.getId())){

            return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customer)));
        }
        log.error("\nWe catch error in service! User try to edit customer with wrong ID {}, ", customer.getId());
        throw new NonExistingIdException("Customer with name ' " + customer.getFirstName() + " " + customer.getLastName() +" ' does not exist.");

    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<CustomerEntity> computers = customerRepository.findAll();

        return computers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> findByFirstNameAndLastName(String name) {
        return customerRepository.findByFirstNameAndLastName(name).stream().map(customerMapper::toDto).collect(Collectors.toList());

    }
}
