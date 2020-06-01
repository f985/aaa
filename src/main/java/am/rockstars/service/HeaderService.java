package am.rockstars.service;

import am.rockstars.dto.header.Header;
import am.rockstars.dto.header.edit.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildRequest;
import am.rockstars.dto.header.edit.CreateHeaderRequest;
import am.rockstars.dto.header.edit.HeaderEdit;
import am.rockstars.entity.HeaderChild;
import am.rockstars.entity.HeaderChildElement;
import am.rockstars.mapper.HeaderMapper;
import am.rockstars.repository.HeaderChildElementRepository;
import am.rockstars.repository.HeaderChildRepository;
import am.rockstars.repository.HeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
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

    public List<Header> getAll() {
        log.debug("Requested to get all headers");
        return mapper.map(headerRepository.findAll(getSorting()));
    }

    public List<HeaderEdit> getEditHeaderResponse() {
        log.debug("Requested to get all headers for edit");
        return mapper.mapEditResponse(headerRepository.findAll(getSorting()));
    }

    @Transactional
    public List<HeaderEdit> addHeader(final CreateHeaderRequest headerRequest) {
        Assert.notNull(headerRequest, "Argument headerRequest should not be null");
        log.debug("Requested to add header with request '{}'", headerRequest);
        final am.rockstars.entity.Header header = mapper.map(headerRequest);
        final am.rockstars.entity.Header savedHeader = headerRepository.save(header);
        log.debug("Successfully added header with id '{}' and request '{}'", savedHeader.getId(), headerRequest);
        return this.getEditHeaderResponse();
    }

    @Transactional
    public List<HeaderEdit> addHeaderChild(
            final CreateHeaderChildRequest childRequest,
            final Long headerId
    ) {
        Assert.notNull(headerId, "Argument headerId should not be null");
        Assert.notNull(childRequest, "Argument childRequest should not be null");
        log.debug("Requested to add Child with header id '{}'", headerId);
        final am.rockstars.entity.Header header = headerRepository.findById(headerId)
                .orElseThrow(illegalArg("Cannot find header by id -> %s", headerId));
        createHeaderChild(childRequest, header);
        log.debug("Successfully added Child by header id '{}' and request '{}'", headerId, childRequest);
        return this.getEditHeaderResponse();
    }

    @Transactional
    public List<HeaderEdit> addHeaderChildElement(
            final CreateHeaderChildElementRequest childRequest,
            final Long childId
    ) {
        Assert.notNull(childId, "Argument childId should not be null");
        Assert.notNull(childId, "Argument childRequest should not be null");
        log.debug("Requested to add Child Element with child id '{}'", childId);
        final HeaderChild child = childRepository.findById(childId)
                .orElseThrow(illegalArg("Cannot find headerChild by id -> %s", childId));
        createHeaderChildElement(childRequest, child);
        log.debug("Successfully added Child Element '{}' by request '{}'", childId, childRequest);
        return this.getEditHeaderResponse();
    }

    public List<HeaderEdit> deleteHeader(final Long headerId) {
        Assert.notNull(headerId, "Argument headerId should not be null");
        log.debug("Requested to delete header with child id '{}'", headerId);
        final am.rockstars.entity.Header header = headerRepository.findById(headerId)
                .orElseThrow(illegalArg("Cannot find header for delete by id  -> %s", headerId));
        headerRepository.delete(header);
        log.debug("Successfully deleted header by id '{}'", headerId);
        return this.getEditHeaderResponse();
    }

    public List<HeaderEdit> deleteChild(final Long childId) {
        Assert.notNull(childId, "Argument childId should not be null");
        log.debug("Requested to delete Child  with child id '{}'", childId);
        final HeaderChild child = childRepository.findById(childId)
                .orElseThrow(illegalArg("Cannot find header child for delete by id  -> %s", childId));
        childRepository.delete(child);
        log.debug("Successfully deleted header Child by Child id '{}'", childId);
        return this.getEditHeaderResponse();
    }

    public List<HeaderEdit> deleteChildElement(final Long childElementId) {
        Assert.notNull(childElementId, "Argument childElementId should not be null");
        log.debug("Requested to delete Child Element with id '{}'", childElementId);
        final HeaderChildElement element = elementRepository.findById(childElementId)
                .orElseThrow(illegalArg("Cannot find header child element for delete by id -> %s",
                        childElementId));
        elementRepository.delete(element);
        log.debug("Successfully deleted header Child element by id '{}'", childElementId);
        return this.getEditHeaderResponse();
    }

    private void createHeaderChild(CreateHeaderChildRequest childRequest, am.rockstars.entity.Header header) {
        final HeaderChild child = mapper.map(childRequest);
        header.addChild(child);
        headerRepository.save(header);
        childRepository.save(child);
    }

    private void createHeaderChildElement(CreateHeaderChildElementRequest elementRequest, HeaderChild child) {
        final HeaderChildElement element = mapper.map(elementRequest);
        child.addElement(element);
        childRepository.save(child);
        elementRepository.save(element);
    }

    private Sort getSorting() {
        return Sort.by(Sort.Direction.ASC, "orderNumber");
    }
}
