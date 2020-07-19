package am.rockstars.service;

import am.rockstars.dto.AboutInformationPayload;
import am.rockstars.entity.AboutInformation;
import am.rockstars.mapper.AboutInformationMapper;
import am.rockstars.repository.AboutInformationRepository;
import am.rockstars.response.AboutInformationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
public class AboutInformationService {

    private final AboutInformationRepository repository;

    private final AboutInformationMapper aboutInformationMapper;

    public AboutInformationService(final AboutInformationRepository repository, final AboutInformationMapper aboutInformationMapper) {
        this.repository = repository;
        this.aboutInformationMapper = aboutInformationMapper;
    }

    public AboutInformationResponse get(final Long id) {
        log.debug("Requested to get about data by id -> '{}'", id);
        Assert.notNull(id, "Argument id should not be null");
        final AboutInformation aboutInformation = repository.findById(id).orElseThrow(
                illegalArg("Cannot find About by id -> %s", id));
        return aboutInformationMapper.mapToResponse(aboutInformation);

    }

    public void update(final AboutInformationPayload payload) {
        log.debug("Requested to update about data -> '{}'", payload);
        Assert.notNull(payload, "Argument payload should not be null");
        final AboutInformation aboutInformation = aboutInformationMapper.map(payload);
        repository.save(aboutInformation);
    }
}
