package software.craftsmanship.scuser.services;

import software.craftsmanship.scuser.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto getConnectedUserDto();
    List<UserDto> getAllUsers(Integer pageNo, Integer pageSize, String sortBy);
}
