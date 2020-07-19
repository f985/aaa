package am.rockstars.service;

import am.rockstars.dto.privacypolicy.PrivacyPolicyRequest;
import am.rockstars.dto.privacypolicy.PrivacyPolicyResponse;
import am.rockstars.entity.PrivacyPolicy;
import am.rockstars.mapper.PrivacyPolicyMapper;
import am.rockstars.repository.PrivacyPolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
public class PrivacyPolicyService {

    private final PrivacyPolicyRepository repository;
    private final PrivacyPolicyMapper contactMapper;


    public PrivacyPolicyService(final PrivacyPolicyRepository repository,
                                final PrivacyPolicyMapper contactMapper) {
        this.repository = repository;
        this.contactMapper = contactMapper;
    }

    public List<PrivacyPolicyResponse> getAll() {
        log.debug("Requested to get all Privacy Policy");
        return contactMapper.map(repository.findAll());
    }

    @Transactional
    public void createPrivacyPolicy(final PrivacyPolicyRequest request) {
        Assert.notNull(request, "PrivacyPolicyRequest should not be null");
        log.debug("Requested to add PrivacyPolicy with request '{}'", request);
        final PrivacyPolicy privacyPolicy = contactMapper.map(request);
        final PrivacyPolicy savedPrivacyPolicy = repository.save(privacyPolicy);
        log.debug("Successfully added PrivacyPolicy with id '{}' and request '{}'", savedPrivacyPolicy.getId(), request);
    }

    @Transactional
    public void edit(final PrivacyPolicyRequest request, final Long id) {
        Assert.notNull(request, "Argument Privacy Policy Request should not be null");
        Assert.notNull(id, "Argument id should not be null");
        log.debug("Requested to edit Privacy Policy with request '{}'", request);
        final PrivacyPolicy privacyPolicy = contactMapper.map(request);
        privacyPolicy.setId(id);
        final PrivacyPolicy savedMissionVision = repository.save(privacyPolicy);
        log.debug("Successfully added PrivacyPolicy with id '{}' and request '{}'", savedMissionVision.getId(), request);
    }

    @Transactional
    public void delete(final Long id) {
        Assert.notNull(id, "Argument Id should not be null");
        log.debug("Requested to delete MissionVision with id '{}'", id);
        final PrivacyPolicy privacyPolicy = findById(id);
        repository.delete(privacyPolicy);
        log.debug("Successfully deleted PrivacyPolicy by id '{}'", id);
    }

    private PrivacyPolicy findById(final Long id) {
        return repository.findById(id).orElseThrow(illegalArg("Cannot find PrivacyPolicy by id", id));
    }
}
