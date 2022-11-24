package vladimirovski.softcontestapplication.mapper;

import vladimirovski.softcontestapplication.dto.ApplicationDto;
import vladimirovski.softcontestapplication.entity.ApplicationEntity;

public interface GenericMapper <E extends ApplicationEntity, D extends ApplicationDto>{

    E toEntity(D dto);
    D toDto(E entity);
    E toEntityNoId(D dto);
    D toDtoNoId(E entity);
}
