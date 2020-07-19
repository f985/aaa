package am.rockstars.service;

import am.rockstars.dto.missionvision.MissionVisionRequest;
import am.rockstars.dto.missionvision.MissionVisionResponse;
import am.rockstars.entity.MissionVision;
import am.rockstars.mapper.MissionVisionMapper;
import am.rockstars.repository.MissionVisionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
public class MissionVisionService {

    private final MissionVisionRepository repository;
    private final MissionVisionMapper contactMapper;

    public MissionVisionService(final MissionVisionRepository repository, final MissionVisionMapper contactMapper) {
        this.repository = repository;
        this.contactMapper = contactMapper;
    }

    public List<MissionVisionResponse> getAll() {
        log.debug("Requested to get all Mission visions");
        return contactMapper.map(repository.findAll());
    }

    @Transactional
    public void createMissionVision(final MissionVisionRequest request) {
        Assert.notNull(request, "MissionVisionRequest should not be null");
        log.debug("Requested to add MissionVision with request '{}'", request);
        final MissionVision missionVision = contactMapper.map(request);
        final MissionVision savedMissionVision = repository.save(missionVision);
        log.debug("Successfully added MissionVision with id '{}' and request '{}'", savedMissionVision.getId(), request);
    }

    @Transactional
    public void edit(final MissionVisionRequest request, final Long id) {
        Assert.notNull(request, "Argument Mission Vision Request should not be null");
        Assert.notNull(id, "Argument id should not be null");
        log.debug("Requested to edit MissionVision with request '{}'", request);
        final MissionVision missionVision = contactMapper.map(request);
        missionVision.setId(id);
        final MissionVision savedMissionVision = repository.save(missionVision);
        log.debug("Successfully added MissionVision with id '{}' and request '{}'", savedMissionVision.getId(), request);
    }

    @Transactional
    public void delete(final Long id) {
        Assert.notNull(id, "Argument Id should not be null");
        log.debug("Requested to delete MissionVision with id '{}'", id);
        final MissionVision missionVision = findById(id);
        repository.delete(missionVision);
        log.debug("Successfully deleted MissionVision by id '{}'", id);
    }

    private MissionVision findById(final Long id) {
        return repository.findById(id).orElseThrow(illegalArg("Cannot find MissionVision by id", id));
    }
}
