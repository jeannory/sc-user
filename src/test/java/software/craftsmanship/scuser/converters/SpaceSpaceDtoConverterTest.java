package software.craftsmanship.scuser.converters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.modelmapper.ModelMapper;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.singleton.SingletonBean;

import static org.junit.Assert.*;

public class SpaceSpaceDtoConverterTest {

    private SpaceSpaceDtoConverter spaceSpaceDtoConverter;

    private SingletonBean singletonBean;

    @Before
    public void setUp() throws Exception{
        this.spaceSpaceDtoConverter = new SpaceSpaceDtoConverter();
        singletonBean = Mockito.mock(SingletonBean.class);
        Whitebox.setInternalState(spaceSpaceDtoConverter, "singletonBean", singletonBean);
    }

    @Test
    public void toDto() {
        //given
        final Space space = new Space();
        space.setName("john_space");
        Mockito.when(singletonBean.getModelMapper()).thenReturn(new ModelMapper());

        //when
        final SpaceDto result = spaceSpaceDtoConverter.toDto(space);

        //then
        assertEquals("john_space", result.getName());
    }

    @Test
    public void toEntity() {
        //given
        final SpaceDto spaceDto = new SpaceDto();
        spaceDto.setName("john_space");
        Mockito.when(singletonBean.getModelMapper()).thenReturn(new ModelMapper());

        //when
        final Space result = spaceSpaceDtoConverter.toEntity(spaceDto);

        //then
        assertEquals("john_space", result.getName());
    }
}