package software.craftsmanship.scuser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.craftsmanship.scuser.dtos.UserDto;
import software.craftsmanship.scuser.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/connected-user")
    public UserDto getConnectedUser(){
        return userService.getConnectedUserDto();
    }

}
