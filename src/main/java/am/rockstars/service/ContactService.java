package am.rockstars.service;

import am.rockstars.dto.contact.ContactEditResponse;
import am.rockstars.dto.contact.ContactRequest;
import am.rockstars.dto.contact.ContactResponse;
import am.rockstars.entity.Contact;
import am.rockstars.mapper.ContactMapper;
import am.rockstars.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
public class ContactService {

    private final ContactRepository repository;
    private final ContactMapper contactMapper;

    public ContactService(ContactRepository repository, ContactMapper contactMapper) {
        this.repository = repository;
        this.contactMapper = contactMapper;
    }


    public ContactResponse get(final Long id) {
        log.debug("Requested to get contact by Id '{}'", id);
        final Contact contact = findById(id);
        return contactMapper.map(contact);
    }

    public List<ContactEditResponse> getContactResponseForEdit() {
        log.debug("Requested to get all contacts for edit");
        return contactMapper.mapToEditResponse(repository.findAll());
    }

    @Transactional
    public List<ContactEditResponse> createContact(final ContactRequest request) {
        Assert.notNull(request, "Contact request should not be null");
        log.debug("Requested to add contact with request '{}'", request);
        final Contact contact = contactMapper.map(request);
        final Contact savedContact = repository.save(contact);
        log.debug("Successfully added contact with id '{}' and request '{}'", savedContact.getId(), request);
        return this.getContactResponseForEdit();
    }

    @Transactional
    public List<ContactEditResponse> editContact(final ContactRequest request, final Long id) {
        Assert.notNull(request, "Argument ContactRequest should not be null");
        Assert.notNull(id, "Argument id should not be null");
        log.debug("Requested to edit Contact with request '{}'", request);
        final Contact contact = contactMapper.map(request);
        contact.setId(id);
        final Contact savedContact = repository.save(contact);
        log.debug("Successfully added contact with id '{}' and request '{}'", savedContact.getId(), request);
        return this.getContactResponseForEdit();
    }

    @Transactional
    public List<ContactEditResponse> deleteContact(final Long id) {
        Assert.notNull(id, "Argument contact Id should not be null");
        log.debug("Requested to delete contact with id '{}'", id);
        final Contact contact = findById(id);
        repository.delete(contact);
        log.debug("Successfully deleted contact by id '{}'", id);
        return this.getContactResponseForEdit();
    }

    private Contact findById(final Long id) {
        return repository.findById(id).orElseThrow(illegalArg("Cannot find contact by id", id));
    }
}
