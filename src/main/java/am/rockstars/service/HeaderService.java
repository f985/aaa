package am.rockstars.service;

import am.rockstars.dto.header.*;
import am.rockstars.entity.Header;
import am.rockstars.entity.HeaderChild;
import am.rockstars.entity.HeaderChildElement;
import am.rockstars.mapper.HeaderMapper;
import am.rockstars.repository.HeaderChildElementRepository;
import am.rockstars.repository.HeaderChildRepository;
import am.rockstars.repository.HeaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HeaderService {

    private final HeaderRepository headerRepository;
    private final HeaderChildRepository childRepository;
    private final HeaderChildElementRepository elementRepository;
    private final HeaderMapper mapper;

    public HeaderService(HeaderRepository headerRepository,
                         HeaderChildRepository childRepository,
                         HeaderChildElementRepository elementRepository,
                         HeaderMapper mapper
    ) {
        this.headerRepository = headerRepository;
        this.childRepository = childRepository;
        this.elementRepository = elementRepository;
        this.mapper = mapper;
    }

    public List<HeaderResponse> get() {
        return mapper.map(headerRepository.findAll());
    }

    public List<HeaderEditResponse> getEditResponse() {
        return mapper.mapToEdit(headerRepository.findAll());
    }

    @Transactional
    public List<HeaderEditResponse> addHeader(final CreateHeaderRequest headerRequest) {
        final Header header = mapper.map(headerRequest);
        headerRepository.save(header);
        return this.getEditResponse();
    }

    @Transactional
    public List<HeaderEditResponse> addHeaderChild(
            final CreateHeaderChildRequest childRequest,
            final Long headerId
    ) {
        return headerRepository.findById(headerId).map(header -> {
            createHeaderChild(childRequest, header);
            return this.getEditResponse();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find header by id -> %s", headerId)));
    }

    @Transactional
    public List<HeaderEditResponse> addHeaderChildElement(
            final CreateHeaderChildElementRequest childRequest,
            final Long childId
    ) {
        return childRepository.findById(childId).map(header -> {
            createHeaderChildElement(childRequest, header);
            return this.getEditResponse();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find headerChild by id -> %s", childId)));
    }

    public List<HeaderEditResponse> deleteHeader(final Long headerId) {
        return headerRepository.findById(headerId).map(header -> {
            headerRepository.delete(header);
            return this.getEditResponse();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find header for delete by id  -> %s", headerId)));
    }

    public List<HeaderEditResponse> deleteChild(final Long childId) {
        return childRepository.findById(childId).map(child -> {
            childRepository.delete(child);
            return this.getEditResponse();
        }).orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find header child for delete by id  -> %s", childId)));
    }

    public List<HeaderEditResponse> deleteChildElement(final Long childElementId) {
        return elementRepository.findById(childElementId).map(child -> {
            elementRepository.delete(child);
            return this.getEditResponse();
        }).orElseThrow(() ->
                new EntityNotFoundException(String.format("Cannot find header child element for delete by id  -> %s", childElementId)));
    }

    private void createHeaderChild(CreateHeaderChildRequest childRequest, Header header) {
        final HeaderChild child = mapper.map(childRequest);
        child.setHeader(header);
        childRepository.save(child);
    }

    private void createHeaderChildElement(CreateHeaderChildElementRequest elementRequest, HeaderChild child) {
        final HeaderChildElement element = mapper.map(elementRequest);
        element.setChild(child);
        elementRepository.save(element);
    }
}
