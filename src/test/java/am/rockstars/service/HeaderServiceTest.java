package am.rockstars.service;

import am.rockstars.dto.header.edit.CreateHeaderChildElementRequest;
import am.rockstars.dto.header.edit.CreateHeaderChildRequest;
import am.rockstars.dto.header.edit.CreateHeaderRequest;
import am.rockstars.dto.header.edit.HeaderEdit;
import am.rockstars.entity.Header;
import am.rockstars.entity.HeaderChild;
import am.rockstars.entity.HeaderChildElement;
import am.rockstars.enums.HeaderType;
import am.rockstars.mapper.HeaderMapper;
import am.rockstars.repository.HeaderChildElementRepository;
import am.rockstars.repository.HeaderChildRepository;
import am.rockstars.repository.HeaderRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class HeaderServiceTest extends AbstractServiceUnitTest {

    final EasyRandom easyRandom = new EasyRandom();
    @InjectMocks
    private HeaderService headerService;
    @Mock
    private HeaderRepository headerRepository;
    @Mock
    private HeaderChildRepository childRepository;
    @Mock
    private HeaderChildElementRepository elementRepository;
    @Mock
    private HeaderMapper mapper;

    @DisplayName("Should create header and return edit response")
    @Test
    void thatCanCreateHeader() {
        //Test data
        final CreateHeaderRequest request = easyRandom.nextObject(CreateHeaderRequest.class);
        final Header header = easyRandom.nextObject(Header.class);
        HeaderEdit response = new HeaderEdit();
        response.setType(HeaderType.LINK);
        final List<Header> headers = Collections.singletonList(header);
        //Mock
        when(mapper.map(any(CreateHeaderRequest.class))).thenReturn(header);
        when(mapper.mapEditResponse(headers)).thenReturn(Collections.singletonList(response));
        when(headerRepository.save(any(Header.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        when(headerRepository.findAll(any(Sort.class))).thenReturn(headers);
        //Service call
        final List<HeaderEdit> responses = headerService.addHeader(request);
        //Verify
        verify(headerRepository).save(header);
        verify(headerRepository).findAll(any(Sort.class));
        verify(mapper).map(request);
        verify(mapper).mapEditResponse(headers);
        verifyNoMoreInteractions(headerRepository, mapper);
        //Asserts
        assertThat(responses).size().isEqualTo(1);
        assertThat(responses.get(0)).isEqualTo(response);
    }

    @DisplayName("Should create header child and return edit response")
    @Test
    void thatCanCreateHeaderChild() {
        //Test data
        final CreateHeaderChildRequest request = easyRandom.nextObject(CreateHeaderChildRequest.class);
        final Header header = easyRandom.nextObject(Header.class);
        final HeaderChild headerChild = easyRandom.nextObject(HeaderChild.class);
        final HeaderEdit response = new HeaderEdit();
        response.setType(HeaderType.LINK);
        final List<Header> headers = Collections.singletonList(header);
        //Mock
        when(headerRepository.findAll(any(Sort.class))).thenReturn(headers);
        when(mapper.mapEditResponse(headers)).thenReturn(Collections.singletonList(response));
        when(mapper.map(any(CreateHeaderChildRequest.class))).thenReturn(headerChild);
        when(headerRepository.findById(1L)).thenReturn(Optional.ofNullable(header));
        when(childRepository.save(any(HeaderChild.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        final List<HeaderEdit> responses = headerService.addHeaderChild(request, 1L);
        //Verify
        verify(headerRepository).findById(1L);
        verify(headerRepository).findAll(any(Sort.class));
        verify(headerRepository).save(header);
        verify(childRepository).save(headerChild);
        verify(mapper).map(request);
        verify(mapper).mapEditResponse(headers);
        verifyNoMoreInteractions(headerRepository, childRepository, mapper);
        //Asserts
        assertThat(responses).size().isEqualTo(1);
        assertThat(responses.get(0)).isEqualTo(response);
    }

    @DisplayName("Should create header child element and return edit response")
    @Test
    void thatCanCreateHeaderChildElement() {
        //Test data
        final CreateHeaderChildElementRequest request = easyRandom.nextObject(CreateHeaderChildElementRequest.class);
        final Header header = easyRandom.nextObject(Header.class);
        final HeaderChild headerChild = easyRandom.nextObject(HeaderChild.class);
        final HeaderChildElement headerChildElement = easyRandom.nextObject(HeaderChildElement.class);
        final HeaderEdit response = new HeaderEdit();
        response.setType(HeaderType.LINK);
        final List<Header> headers = Collections.singletonList(header);
        //Mock
        when(headerRepository.findAll(any(Sort.class))).thenReturn(headers);
        when(mapper.mapEditResponse(headers)).thenReturn(Collections.singletonList(response));
        when(mapper.map(any(CreateHeaderChildElementRequest.class))).thenReturn(headerChildElement);
        when(childRepository.findById(1L)).thenReturn(Optional.ofNullable(headerChild));
        when(elementRepository.save(any(HeaderChildElement.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        //Service call
        final List<HeaderEdit> responses = headerService.addHeaderChildElement(request, 1L);
        //Verify
        verify(childRepository).findById(1L);
        verify(headerRepository).findAll(any(Sort.class));
        verify(elementRepository).save(headerChildElement);
        verify(childRepository).save(headerChild);
        verify(mapper).map(request);
        verify(mapper).mapEditResponse(headers);
        verifyNoMoreInteractions(headerRepository, childRepository, mapper);
        //Asserts
        assertThat(responses).size().isEqualTo(1);
        assertThat(responses.get(0)).isEqualTo(response);
    }

    @DisplayName("Should throw exception create header child")
    @Test
    void thatHeaderChildThrowEntityNotFoundException() {
        //Test data
        final CreateHeaderChildRequest request = easyRandom.nextObject(CreateHeaderChildRequest.class);
        //Mock
        when(headerRepository.findById(1L)).thenReturn(Optional.empty());
        //Service call
        assertThrows(EntityNotFoundException.class, () -> headerService.addHeaderChild(request, 1L));
        //Verify
        verify(headerRepository).findById(1L);
        verifyNoMoreInteractions(headerRepository);
    }

    @DisplayName("Should throw exception create header child element")
    @Test
    void thatHeaderChildElementThrowEntityNotFoundException() {
        //Test data
        final CreateHeaderChildElementRequest request = easyRandom.nextObject(CreateHeaderChildElementRequest.class);
        //Mock
        when(childRepository.findById(1L)).thenReturn(Optional.empty());
        //Service call
        assertThrows(EntityNotFoundException.class, () -> headerService.addHeaderChildElement(request, 1L));
        //Verify
        verify(childRepository).findById(1L);
        verifyNoMoreInteractions(childRepository);
    }
}
