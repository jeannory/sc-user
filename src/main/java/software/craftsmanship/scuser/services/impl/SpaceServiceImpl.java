package software.craftsmanship.scuser.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import software.craftsmanship.scuser.converters.SuperConverter;
import software.craftsmanship.scuser.dtos.SpaceDto;
import software.craftsmanship.scuser.entities.Space;
import software.craftsmanship.scuser.repositories.SpaceRepository;
import software.craftsmanship.scuser.services.SpaceService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    @Qualifier("SpaceSpaceDtoConverter")
    private SuperConverter<Space, SpaceDto> converter;

    @Override
    public List<SpaceDto> getAllSpaces(Integer pageNo, Integer pageSize, String sortBy) {
        final Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        final Page<Space> pagedResult = spaceRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return converter.toDtos(pagedResult.getContent());
        } else {
            return new ArrayList<>();
        }
    }
}
