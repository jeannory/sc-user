package software.craftsmanship.scuser.converters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.dtos.SuperDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.entities.SuperEntity;

import java.lang.reflect.Type;

@Service
@Qualifier("SpaceSpaceDtoConverter")
public class SpaceSpaceDtoConverter <E extends SuperEntity, D extends SuperDto> extends SuperConverter{

    @Override
    public SpaceDto toDto(SuperEntity entity) {
        return getSingletonBean().getModelMapper().map(entity, (Type) SpaceDto.class);
    }

    @Override
    public Space toEntity(SuperDto dto) {
        return getSingletonBean().getModelMapper().map(dto, (Type) Space.class);
    }
}
