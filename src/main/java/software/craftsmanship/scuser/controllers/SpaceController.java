package software.craftsmanship.scuser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.services.SpaceService;

import java.util.List;

@RestController
@RequestMapping("api/spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @RequestMapping
    public ResponseEntity<List<SpaceDto>> getSpaces(
            @RequestParam(defaultValue = "0") final Integer pageNo,
            @RequestParam(defaultValue = "10") final Integer pageSize,
            @RequestParam(defaultValue = "id") final String sortBy)
    {
        final List<SpaceDto> userDtos = spaceService.getAllSpaces(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<SpaceDto>>(userDtos, new HttpHeaders(), HttpStatus.OK);
    }
}
