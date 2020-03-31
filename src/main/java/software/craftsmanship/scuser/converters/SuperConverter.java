package software.craftsmanship.scuser.converters;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import software.craftsmanship.scuser.dtos.SuperDto;
import software.craftsmanship.scuser.entities.SuperEntity;
import software.craftsmanship.scuser.singleton.SingletonBean;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class SuperConverter <E extends SuperEntity, D extends SuperDto>  {

    @Autowired
    @Getter
    private SingletonBean singletonBean;

    public abstract D toDto(final E entity);

    public abstract E toEntity(final D dto);

    public List<D> toDtos(final List<E> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList()).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<E> toEntities(final List<D> dtos){
        return dtos.stream().map(this::toEntity).collect(Collectors.toList()).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

}
