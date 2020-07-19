package am.rockstars.service;

import am.rockstars.dto.AboutInformationPayload;
import am.rockstars.entity.AboutInformation;
import am.rockstars.mapper.AboutInformationMapper;
import am.rockstars.repository.AboutInformationRepository;
import am.rockstars.response.AboutInformationResponse;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class AboutInformationServiceTest extends AbstractServiceUnitTest {

    @InjectMocks
    private AboutInformationService aboutInformationService;

    @Mock
    private AboutInformationRepository aboutInformationRepository;

    @Mock
    private AboutInformationMapper mapper;

    final EasyRandom easyRandom = new EasyRandom();

    @DisplayName("Should return about info response")
    @Test
    void thatCanGetAbout() {
        //Test data
        final Long id = 1L;
        final AboutInformationResponse request = easyRandom.nextObject(AboutInformationResponse.class);
        final AboutInformation aboutInformation = easyRandom.nextObject(AboutInformation.class);
        //Mock
        when(mapper.mapToResponse(aboutInformation)).thenReturn(request);
        when(aboutInformationRepository.findById(id)).thenReturn(Optional.ofNullable(aboutInformation));
        //Service call
        final AboutInformationResponse aboutInformationResponse = aboutInformationService.get(id);
        //Verify
        verify(aboutInformationRepository).findById(id);
        verify(mapper).mapToResponse(aboutInformation);
        verifyNoMoreInteractions(aboutInformationRepository, mapper);
        //Asserts
        assertThat(aboutInformationResponse.getContent()).isEqualTo(request.getContent());
        assertThat(aboutInformationResponse.getHeading()).isEqualTo(request.getHeading());
        assertThat(aboutInformationResponse.getImage()).isEqualTo(request.getImage());
        assertThat(aboutInformationResponse.getSubHeading()).isEqualTo(request.getSubHeading());
        assertThat(aboutInformationResponse.getId()).isEqualTo(request.getId());
    }

    @DisplayName("Should update About info")
    @Test
    void thatCanUpdateAbout() {
        //Test data
        final Long id = 1L;
        final AboutInformationPayload aboutInformationPayload = easyRandom.nextObject(AboutInformationPayload.class);
        final AboutInformation aboutInformation = easyRandom.nextObject(AboutInformation.class);
        //Mock
        when(mapper.map(aboutInformationPayload)).thenReturn(aboutInformation);
        //Service call
        aboutInformationService.update(aboutInformationPayload);
        //Verify
        verify(aboutInformationRepository).save(aboutInformation);
        verify(mapper).map(aboutInformationPayload);
        verifyNoMoreInteractions(aboutInformationRepository, mapper);
    }

    @DisplayName("Should throw exception")
    @Test
    void thatHeaderChildThrowEntityNotFoundException() {
        final Long id = 1L;
        //Mock
        when(aboutInformationRepository.findById(id)).thenReturn(Optional.empty());
        //Service call
        assertThrows(EntityNotFoundException.class, () -> aboutInformationService.get(id));
        //Verify
        verify(aboutInformationRepository).findById(id);
        verifyNoMoreInteractions(aboutInformationRepository);
    }
}
