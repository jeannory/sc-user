package software.craftsmanship.scuser.services;

import org.junit.Before;
import org.junit.Test;
import org.keycloak.representations.AccessToken;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import software.craftsmanship.scuser.converters.SuperConverter;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.dtos.UserDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.entities.User;
import software.craftsmanship.scuser.exceptions.CustomTransactionalException;
import software.craftsmanship.scuser.repositories.SpaceRepository;
import software.craftsmanship.scuser.repositories.UserRepository;
import software.craftsmanship.scuser.security.KeycloakSecurityContext;
import software.craftsmanship.scuser.services.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private KeycloakSecurityContext keycloakSecurityContext;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SpaceRepository spaceRepository;

    @Mock
    private SuperConverter<User, UserDto> converter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetConnectedUserDtoWhenUserExistsReturnDto() {
        //given
        final AccessToken accessToken = Mockito.spy(new AccessToken());
        accessToken.setPreferredUsername("XLD");
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(accessToken);
        final User user = new User();
        user.setUsername("XLD");
        Mockito.when(userRepository.findByUsernameIgnoreCase(Mockito.anyString())).thenReturn(user);
        final UserDto userDto = new UserDto();
        userDto.setUsername("XLD");
        Mockito.when(converter.toDto(user)).thenReturn(userDto);

        //when
        final UserDto result = userService.getConnectedUserDto();

        //then
        assertEquals("XLD", result.getUsername());
    }

    @Test
    public void testGetConnectedUserDtoWhenUserNoExistsReturnDto() {
        //given
        final AccessToken accessToken = Mockito.spy(new AccessToken());
        accessToken.setPreferredUsername("XLD");
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(accessToken);
        Mockito.when(userRepository.findByUsernameIgnoreCase(Mockito.anyString())).thenReturn(null);
        final Space space = new Space();
        space.setId(1L);
        space.setName("XLD_space");

        Mockito.when(spaceRepository.save(Mockito.any(Space.class))).thenReturn(space);
        final User user = new User();
        user.setId(1L);
        user.setUsername("XLD");
        user.setSpace(space);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findByUsernameIgnoreCase(Mockito.anyString())).thenReturn(user);

        final SpaceDto spaceDto = new SpaceDto();
        spaceDto.setName("XLD_space");

        final UserDto userDto = new UserDto();
        userDto.setUsername("XLD");
        userDto.setSpace(spaceDto);
        Mockito.when(converter.toDto(user)).thenReturn(userDto);

        //when
        final UserDto result = userService.getConnectedUserDto();
        result.toString();

        //then
        assertEquals("XLD", result.getUsername());
    }

    @Test
    public void testGetConnectedUserWhenGetAccessTokenIsNullThenThrowExceptionReturnNull(){
        //given
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(null);

        //when
        final UserDto result = userService.getConnectedUserDto();

        //then
        assertNull("return null", result);
    }

    @Test
    public void testGetConnectedUserWhenPreferredUsernameIsNullThenThrowExceptionReturnNull() {
        //given
        final AccessToken accessToken = Mockito.spy(new AccessToken());
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(accessToken);

        //when
        final UserDto result = userService.getConnectedUserDto();

        //then
        assertNull("return null", result);
    }

    @Test
    public void testGetConnectedUserWhenPreferredUsernameIsEmptyThenThrowExceptionReturnNull() {
        //given
        final AccessToken accessToken = Mockito.spy(new AccessToken());
        accessToken.setPreferredUsername("");
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(accessToken);

        //when
        final UserDto result = userService.getConnectedUserDto();

        //then
        assertNull("return null", result);
    }

    @Test
    public void testGetConnectedUserWhenSpaceSaveFailedThenThrowExceptionReturnNull() {
        //given
        final AccessToken accessToken = Mockito.spy(new AccessToken());
        accessToken.setPreferredUsername("XLD");
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(accessToken);
        Mockito.when(userRepository.findByUsernameIgnoreCase(Mockito.anyString())).thenReturn(null);
        final Space space = new Space();
        space.setName("XLD_space");
        Mockito.when(spaceRepository.save(Mockito.any(Space.class))).thenThrow(new CustomTransactionalException());

        //when
        final UserDto result = userService.getConnectedUserDto();

        //then
        assertNull("return null", result);
    }

    @Test
    public void testGetConnectedUserWhenUserSaveFailedThenThrowExceptionReturnNull() {
        //given
        final AccessToken accessToken = Mockito.spy(new AccessToken());
        accessToken.setPreferredUsername("XLD");
        Mockito.when(keycloakSecurityContext.getAccessToken()).thenReturn(accessToken);
        Mockito.when(userRepository.findByUsernameIgnoreCase(Mockito.anyString())).thenReturn(null);
        final User user = new User();
        user.setUsername("XLD");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new CustomTransactionalException());
        final Space space = new Space();
        space.setName("XLD_space");
        space.setUser(user);
        Mockito.when(spaceRepository.save(Mockito.any(Space.class))).thenReturn(space);

        //when
        final UserDto result = userService.getConnectedUserDto();

        //then
        assertNull("return null", result);
    }
}