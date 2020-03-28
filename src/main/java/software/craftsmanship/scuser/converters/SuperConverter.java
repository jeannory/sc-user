package software.craftsmanship.scuser.converters;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import software.craftsmanship.scuser.dtos.SuperDto;
import software.craftsmanship.scuser.entities.SuperEntity;
import software.craftsmanship.scuser.singleton.SingletonBean;

public abstract class SuperConverter <E extends SuperEntity, D extends SuperDto>  {

    @Autowired
    @Getter
    private SingletonBean singletonBean;

    public abstract D toDto(final E entity);

    public abstract E toEntity(final D dto);

}
