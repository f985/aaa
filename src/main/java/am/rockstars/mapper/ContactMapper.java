package am.rockstars.mapper;

import am.rockstars.dto.contact.ContactEditResponse;
import am.rockstars.dto.contact.ContactRequest;
import am.rockstars.dto.contact.ContactResponse;
import am.rockstars.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface ContactMapper {

    Contact map(ContactRequest request);

    ContactResponse map(Contact contact);

    List<ContactEditResponse> mapToEditResponse(List<Contact> contacts);


}
