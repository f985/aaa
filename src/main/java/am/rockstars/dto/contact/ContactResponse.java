package am.rockstars.dto.contact;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
    private String address;

    private String call;

    private String info;

    private String mail;
}
