package am.rockstars.service;

import am.rockstars.dto.privacypolicy.PrivacyPolicyRequest;
import am.rockstars.dto.privacypolicy.PrivacyPolicyResponse;
import am.rockstars.entity.PrivacyPolicy;
import am.rockstars.mapper.PrivacyPolicyMapper;
import am.rockstars.repository.PrivacyPolicyRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class PrivacyPolicyServiceTest extends AbstractServiceUnitTest {

    @InjectMocks
    private PrivacyPolicyService service;

    @Mock
    private PrivacyPolicyRepository repository;

    @Mock
    private PrivacyPolicyMapper mapper;

    final EasyRandom easyRandom = new EasyRandom();

    @DisplayName("Should create privacy policy")
    @Test
    void thatCanCreatePrivacyPolicy() {
        //Test data
        final PrivacyPolicyRequest request = easyRandom.nextObject(PrivacyPolicyRequest.class);
        final PrivacyPolicy PrivacyPolicy = easyRandom.nextObject(PrivacyPolicy.class);
        //Mock
        when(mapper.map(any(PrivacyPolicyRequest.class))).thenReturn(PrivacyPolicy);
        when(repository.save(PrivacyPolicy)).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        service.createPrivacyPolicy(request);
        //Verify
        verify(repository).save(PrivacyPolicy);
        verify(mapper).map(request);
        verifyNoMoreInteractions(repository, mapper);
    }

    @DisplayName("Should edit mission vision")
    @Test
    void thatCanEditPrivacyPolicy() {
        //Test data
        final PrivacyPolicyRequest request = easyRandom.nextObject(PrivacyPolicyRequest.class);
        final PrivacyPolicy PrivacyPolicy = easyRandom.nextObject(PrivacyPolicy.class);
        //Mock
        when(mapper.map(any(PrivacyPolicyRequest.class))).thenReturn(PrivacyPolicy);
        when(repository.save(PrivacyPolicy)).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        service.edit(request, 1L);
        //Verify
        verify(repository).save(PrivacyPolicy);
        verify(mapper).map(request);
        verifyNoMoreInteractions(repository, mapper);
        //assert
        assertThat(PrivacyPolicy.getId()).isEqualTo(1L);
    }

    @DisplayName("Should delete privacy policy")
    @Test
    void thatCanDeletePrivacyPolicy() {
        //Test data
        final PrivacyPolicy PrivacyPolicy = easyRandom.nextObject(PrivacyPolicy.class);
        //Mock
        when(repository.findById(1L)).thenReturn(Optional.of(PrivacyPolicy));
        //Service call
        service.delete(1L);
        //Verify
        verify(repository).delete(PrivacyPolicy);
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @DisplayName("Should get All privacy policy")
    @Test
    void thatCanGetAllPrivacyPolicy() {
        //Test data
        final PrivacyPolicyResponse response = easyRandom.nextObject(PrivacyPolicyResponse.class);
        final PrivacyPolicy PrivacyPolicy = easyRandom.nextObject(PrivacyPolicy.class);
        final List<PrivacyPolicy> PrivacyPolicys = Collections.singletonList(PrivacyPolicy);
        //Mock
        when(repository.findAll()).thenReturn(PrivacyPolicys);
        when(mapper.map(PrivacyPolicys)).thenReturn(Collections.singletonList(response));
        //Service call
        final List<PrivacyPolicyResponse> responses = service.getAll();
        //Verify
        verify(mapper).map(PrivacyPolicys);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository, mapper);
        //assert
        assertThat(responses.get(0).getContent()).isEqualTo(response.getContent());
    }

    @DisplayName("Should throw exception while getting privacy policy")
    @Test
    void thatCanDeleteThrowEntityNotFoundException() {
        //Test data
        final PrivacyPolicy PrivacyPolicy = easyRandom.nextObject(PrivacyPolicy.class);
        //Mock
        when(repository.findById(1L)).thenReturn(Optional.empty());
        //Service call
        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
        //Verify
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}
