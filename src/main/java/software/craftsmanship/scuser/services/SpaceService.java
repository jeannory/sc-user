package software.craftsmanship.scuser.services;

import software.craftsmanship.scuser.dtos.SpaceDto;

import java.util.List;

public interface SpaceService {
    List<SpaceDto> getAllSpaces(Integer pageNo, Integer pageSize, String sortBy);
}
