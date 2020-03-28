package software.craftsmanship.scuser.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.craftsmanship.scuser.enums.Gender;
import software.craftsmanship.scuser.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends SuperDto {

    private String username;
    private String email;
    private Gender gender;
    private String firstName;
    private String lastName;
    private Status status;
    private SpaceDto space;
}
