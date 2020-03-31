package software.craftsmanship.scuser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.craftsmanship.scuser.dtos.UserDto;
import software.craftsmanship.scuser.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(defaultValue = "0") final Integer pageNo,
            @RequestParam(defaultValue = "10") final Integer pageSize,
            @RequestParam(defaultValue = "id") final String sortBy)
    {
        final List<UserDto> userDtos = userService.getAllUsers(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<UserDto>>(userDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/connected-user")
    public ResponseEntity<UserDto> getConnectedUser(){
        final UserDto userDto = userService.getConnectedUserDto();
        return new ResponseEntity<UserDto>(userDto, new HttpHeaders(), HttpStatus.OK);
    }

}
