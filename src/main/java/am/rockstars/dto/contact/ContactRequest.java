package am.rockstars.dto.contact;

import lombok.*;

@Getter
@Setter
@ToString
public class ContactRequest {

    private String address;

    private String call;

    private String info;

    private String mail;
}
