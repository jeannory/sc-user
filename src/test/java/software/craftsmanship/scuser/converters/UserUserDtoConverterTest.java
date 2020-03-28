package software.craftsmanship.scuser.converters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.dtos.UserDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.entities.User;
import software.craftsmanship.scuser.singleton.SingletonBean;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserUserDtoConverterTest {

    @InjectMocks
    private UserUserDtoConverter userUserDtoConverter;

    @Mock
    private SingletonBean singletonBean;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToDto() {
        //given
        final User user = Mockito.spy(new User());
        Mockito.when(user.getUsername()).thenReturn("john");
        final Space space = new Space();
        space.setName("john_space");
        user.setSpace(space);
        Mockito.when(singletonBean.getModelMapper()).thenReturn(new ModelMapper());

        //when
        final UserDto result = userUserDtoConverter.toDto(user);

        //then
        assertEquals("john",result.getUsername());
        assertEquals("john_space", result.getSpace().getName());
    }

    @Test
    public void toEntity() {
        //given
        final SpaceDto spaceDto = new SpaceDto();
        spaceDto.setName("john_space");
        final UserDto userDto = new UserDto();
        userDto.setUsername("john");
        userDto.setSpace(spaceDto);
        Mockito.when(singletonBean.getModelMapper()).thenReturn(new ModelMapper());

        //when
        final User result = userUserDtoConverter.toEntity(userDto);

        //then
        assertEquals("john",result.getUsername());
        assertEquals("john_space", result.getSpace().getName());
    }
}