package software.craftsmanship.scuser.services;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import software.craftsmanship.scuser.converters.SuperConverter;
import software.craftsmanship.scuser.dtos.UserDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.entities.User;
import software.craftsmanship.scuser.exceptions.CustomServiceException;
import software.craftsmanship.scuser.exceptions.CustomTransactionalException;
import software.craftsmanship.scuser.repositories.SpaceRepository;
import software.craftsmanship.scuser.repositories.UserRepository;
import software.craftsmanship.scuser.security.KeycloakSecurityContext;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private KeycloakSecurityContext keycloakSecurityContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    @Qualifier("UserUserDtoConverter")
    private SuperConverter<User, UserDto> converter;

    @Override
    public UserDto getConnectedUserDto() {
        try {
            return converter.toDto(getConnectedUser());
        } catch (CustomServiceException ex) {
            return null;
        }
    }

    //catch CustomTransactionalException in this method
    private User getConnectedUser() {
        final AccessToken accessToken = keycloakSecurityContext.getAccessToken();
        if (accessToken == null) {
            throw new CustomServiceException("error getAccessToken failed");
        } else if (accessToken.getPreferredUsername() == null || accessToken.getPreferredUsername().isEmpty()) {
            throw new CustomServiceException("error preferredUsername cannot be null or empty");
        }
        final String username = accessToken.getPreferredUsername();
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            try {
                user = testCreateUser(accessToken);
            } catch (CustomTransactionalException ex) {
                return null;
            }
            user.toString();
        }
        return user;
    }

    // Do not catch CustomTransactionalException in this method
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = CustomTransactionalException.class)
    private User testCreateUser(final AccessToken accessToken) {
        return validateCreateUser(accessToken);
    }

    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY)
    private User validateCreateUser(final AccessToken accessToken) {
        try {
            final Space space = new Space(null, accessToken.getPreferredUsername() + "_space", null);
            spaceRepository.save(space);
            final User user = new User();
            user.setUsername(accessToken.getPreferredUsername());
            user.setEmail(accessToken.getEmail());
            user.setFirstName(accessToken.getGivenName());
            user.setLastName(accessToken.getFamilyName());
            user.setSpace(space);
            userRepository.save(user);
            return userRepository.findByUsernameIgnoreCase(accessToken.getPreferredUsername());
        } catch (Exception ex) {
            throw new CustomTransactionalException("persistence failed : " + ex.getMessage());
        }
    }

}
