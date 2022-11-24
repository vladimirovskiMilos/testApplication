package vladimirovski.softcontestapplication.mapper;

import org.springframework.stereotype.Component;
import vladimirovski.softcontestapplication.dto.ComputerDto;
import vladimirovski.softcontestapplication.entity.ComputerEntity;

@Component
public class ComputerMapper implements GenericMapper<ComputerEntity, ComputerDto>{
    @Override
    public ComputerEntity toEntity(ComputerDto dto) {
        return new ComputerEntity(dto.getId(), dto.getName(), dto.getMark(), dto.getRamMemory(), dto.getHardDisc());
    }

    @Override
    public ComputerDto toDto(ComputerEntity entity) {
        return new ComputerDto(entity.getId(), entity.getName(), entity.getMark(), entity.getRamMemory(), entity.getHardDisc());
    }

    @Override
    public ComputerEntity toEntityNoId(ComputerDto dto) {
        return new ComputerEntity(dto.getName(), dto.getMark(), dto.getRamMemory(), dto.getHardDisc());
    }

    @Override
    public ComputerDto toDtoNoId(ComputerEntity entity) {
        return new ComputerDto(entity.getName(), entity.getMark(), entity.getRamMemory(), entity.getHardDisc());
    }
}
