package vladimirovski.softcontestapplication.mapper;

import org.springframework.stereotype.Component;
import vladimirovski.softcontestapplication.dto.CustomerDto;
import vladimirovski.softcontestapplication.entity.CustomerEntity;

@Component
public class CustomerMapper implements GenericMapper<CustomerEntity, CustomerDto> {
    @Override
    public CustomerEntity toEntity(CustomerDto dto) {
        return new CustomerEntity(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getComputers());
    }

    @Override
    public CustomerDto toDto(CustomerEntity entity) {
        return new CustomerDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getComputers());
    }

    @Override
    public CustomerEntity toEntityNoId(CustomerDto dto) {
        return new CustomerEntity(dto.getFirstName(), dto.getLastName(), dto.getComputers());
    }

    @Override
    public CustomerDto toDtoNoId(CustomerEntity entity) {
        return new CustomerDto(entity.getFirstName(), entity.getLastName(), entity.getComputers());
    }
}
