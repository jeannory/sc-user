package software.craftsmanship.scuser.converters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.dtos.SuperDto;
import software.craftsmanship.scuser.dtos.UserDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.entities.SuperEntity;
import software.craftsmanship.scuser.entities.User;

import java.lang.reflect.Type;

@Service
@Qualifier("UserUserDtoConverter")
public class UserUserDtoConverter <E extends SuperEntity, D extends SuperDto> extends SuperConverter {

    @Override
    public UserDto toDto(SuperEntity entity) {
        final User user = (User) entity;
        final UserDto userDto = getSingletonBean().getModelMapper().map(user, (Type) UserDto.class);
        if(user.getSpace()!=null){
            final SpaceDto spaceDto = getSingletonBean().getModelMapper().map(user.getSpace(), (Type) SpaceDto.class);
            userDto.setSpace(spaceDto);
        }
        return userDto;
    }

    @Override
    public User toEntity(SuperDto dto) {
        final UserDto userDto = (UserDto) dto;
        final User user = getSingletonBean().getModelMapper().map(userDto, (Type) User.class);
        if(userDto.getSpace()!=null){
            final Space space = getSingletonBean().getModelMapper().map(userDto.getSpace(), (Type) Space.class);
            user.setSpace(space);
        }
        return user;
    }

}
