package am.rockstars.service;

import am.rockstars.dto.contact.ContactEditResponse;
import am.rockstars.dto.contact.ContactRequest;
import am.rockstars.dto.contact.ContactResponse;
import am.rockstars.entity.Contact;
import am.rockstars.mapper.ContactMapper;
import am.rockstars.repository.ContactRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class ContactServiceTest extends AbstractServiceUnitTest {

    @InjectMocks
    private ContactService service;

    @Mock
    private ContactRepository repository;

    @Mock
    private ContactMapper mapper;

    final EasyRandom easyRandom = new EasyRandom();

    @DisplayName("Should create contact and return edit response")
    @Test
    void thatCanCreateContact() {
        //Test data
        final ContactRequest request = easyRandom.nextObject(ContactRequest.class);
        final Contact contact = easyRandom.nextObject(Contact.class);
        final ContactEditResponse response = ContactEditResponse.builder().id(1L).build();
        final List<Contact> contacts = Collections.singletonList(contact);
        //Mock
        when(mapper.map(any(ContactRequest.class))).thenReturn(contact);
        when(mapper.mapToEditResponse(contacts)).thenReturn(Collections.singletonList(response));
        when(repository.save(any(Contact.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        when(repository.findAll()).thenReturn(contacts);
        //Service call
        final List<ContactEditResponse> responses = service.createContact(request);
        //Verify
        verify(repository).save(contact);
        verify(repository).findAll();
        verify(mapper).map(request);
        verify(mapper).mapToEditResponse(contacts);
        verifyNoMoreInteractions(repository, mapper);
        //Asserts
        assertThat(responses).size().isEqualTo(1);
        assertThat(responses.get(0)).isEqualTo(response);
    }

    @DisplayName("Should edit contact and return edit response")
    @Test
    void thatCanEditContact() {
        //Test data
        final ContactRequest request = easyRandom.nextObject(ContactRequest.class);
        final Contact contact = easyRandom.nextObject(Contact.class);
        final ContactEditResponse response = ContactEditResponse.builder().id(1L).build();
        final List<Contact> contacts = Collections.singletonList(contact);
        //Mock
        when(mapper.map(any(ContactRequest.class))).thenReturn(contact);
        when(mapper.mapToEditResponse(contacts)).thenReturn(Collections.singletonList(response));
        when(repository.save(any(Contact.class))).then(invocationOnMock -> invocationOnMock.getArgument(0));
        when(repository.findAll()).thenReturn(contacts);
        //Service call
        final List<ContactEditResponse> responses = service.editContact(request, 1L);
        //Verify
        verify(repository).save(contact);
        verify(repository).findAll();
        verify(mapper).map(request);
        verify(mapper).mapToEditResponse(contacts);
        verifyNoMoreInteractions(repository, mapper);
        //Asserts
        assertThat(responses).size().isEqualTo(1);
        assertThat(responses.get(0)).isEqualTo(response);
    }

    @DisplayName("Should create contact and return edit response")
    @Test
    void thatCanDeleteContact() {
        //Test data
        final Contact contact = easyRandom.nextObject(Contact.class);
        final ContactEditResponse response = ContactEditResponse.builder().id(1L).build();
        final List<Contact> contacts = Collections.singletonList(contact);
        //Mock
        when(mapper.mapToEditResponse(contacts)).thenReturn(Collections.singletonList(response));
        when(repository.findById(1L)).thenReturn(Optional.of(contact));
        when(repository.findAll()).thenReturn(contacts);
        //Service call
        final List<ContactEditResponse> responses = service.deleteContact(1L);
        //Verify
        verify(repository).delete(contact);
        verify(repository).findAll();
        verify(mapper).mapToEditResponse(contacts);
        verifyNoMoreInteractions(repository, mapper);
        //Asserts
        assertThat(responses).size().isEqualTo(1);
        assertThat(responses.get(0)).isEqualTo(response);
    }

    @DisplayName("Should get contact")
    @Test
    void thatCanGetContact() {
        //Test data
        final Contact contact = easyRandom.nextObject(Contact.class);
        final ContactResponse response = easyRandom.nextObject(ContactResponse.class);
        //Mock
        when(mapper.map(contact)).thenReturn(response);
        when(repository.findById(1L)).thenReturn(Optional.of(contact));
        //Service call
        final ContactResponse responses = service.get(1L);
        //Verify
        verify(repository).findById(1L);
        verify(mapper).map(contact);
        verifyNoMoreInteractions(repository, mapper);
        //Asserts
        assertThat(responses.getAddress()).isEqualTo(response.getAddress());
        assertThat(responses.getCall()).isEqualTo(response.getCall());
        assertThat(responses.getInfo()).isEqualTo(response.getInfo());
        assertThat(responses.getMail()).isEqualTo(response.getMail());
    }

    @DisplayName("Should throw exception while getting contact")
    @Test
    void thatContactThrowEntityNotFoundException() {
        //Mock
        when(repository.findById(1L)).thenReturn(Optional.empty());
        //Service call
        assertThrows(EntityNotFoundException.class, () -> service.get(1L));
        //Verify
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}
