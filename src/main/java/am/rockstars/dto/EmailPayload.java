package am.rockstars.dto;

import am.rockstars.validator.ValidEmailCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
public class EmailPayload {

    @ValidEmailCollection
    private Set<String> recipients;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
