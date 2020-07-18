package am.rockstars.service;

import am.rockstars.dto.missionvision.MissionVisionRequest;
import am.rockstars.dto.missionvision.MissionVisionResponse;
import am.rockstars.entity.MissionVision;
import am.rockstars.mapper.MissionVisionMapper;
import am.rockstars.repository.MissionVisionRepository;
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
public class MissionVisionServiceTest extends AbstractServiceUnitTest {

    @InjectMocks
    private MissionVisionService service;

    @Mock
    private MissionVisionRepository repository;

    @Mock
    private MissionVisionMapper mapper;

    final EasyRandom easyRandom = new EasyRandom();

    @DisplayName("Should create mission vision")
    @Test
    void thatCanCreateMissionVision() {
        //Test data
        final MissionVisionRequest request = easyRandom.nextObject(MissionVisionRequest.class);
        final MissionVision missionVision = easyRandom.nextObject(MissionVision.class);
        //Mock
        when(mapper.map(any(MissionVisionRequest.class))).thenReturn(missionVision);
        when(repository.save(missionVision)).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        service.createMissionVision(request);
        //Verify
        verify(repository).save(missionVision);
        verify(mapper).map(request);
        verifyNoMoreInteractions(repository, mapper);
    }

    @DisplayName("Should edit mission vision")
    @Test
    void thatCanEditMissionVision() {
        //Test data
        final MissionVisionRequest request = easyRandom.nextObject(MissionVisionRequest.class);
        final MissionVision missionVision = easyRandom.nextObject(MissionVision.class);
        //Mock
        when(mapper.map(any(MissionVisionRequest.class))).thenReturn(missionVision);
        when(repository.save(missionVision)).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        service.edit(request, 1L);
        //Verify
        verify(repository).save(missionVision);
        verify(mapper).map(request);
        verifyNoMoreInteractions(repository, mapper);
        //assert
        assertThat(missionVision.getId()).isEqualTo(1L);
    }

    @DisplayName("Should delete mission vision")
    @Test
    void thatCanDeleteMissionVision() {
        //Test data
        final MissionVision missionVision = easyRandom.nextObject(MissionVision.class);
        //Mock
        when(repository.findById(1L)).thenReturn(Optional.of(missionVision));
        //Service call
        service.delete(1L);
        //Verify
        verify(repository).delete(missionVision);
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @DisplayName("Should get All mission visions")
    @Test
    void thatCanGetAllMissionVision() {
        //Test data
        final MissionVisionResponse response = easyRandom.nextObject(MissionVisionResponse.class);
        final MissionVision missionVision = easyRandom.nextObject(MissionVision.class);
        final List<MissionVision> missionVisions = Collections.singletonList(missionVision);
        //Mock
        when(repository.findAll()).thenReturn(missionVisions);
        when(mapper.map(missionVisions)).thenReturn(Collections.singletonList(response));
        //Service call
        final List<MissionVisionResponse> responses = service.getAll();
        //Verify
        verify(mapper).map(missionVisions);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository, mapper);
        //assert
        assertThat(responses.get(0).getContent()).isEqualTo(response.getContent());
    }

    @DisplayName("Should throw exception while getting vision")
    @Test
    void thatCanDeleteThrowEntityNotFoundException() {
        //Test data
        final MissionVision missionVision = easyRandom.nextObject(MissionVision.class);
        //Mock
        when(repository.findById(1L)).thenReturn(Optional.empty());
        //Service call
        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
        //Verify
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}
