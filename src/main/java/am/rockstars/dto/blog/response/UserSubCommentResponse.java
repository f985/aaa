package am.rockstars.dto.blog.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSubCommentResponse {

    private Long id;

    private String comment;

    private String date;

    @JsonProperty(value = "img")
    private String image;

    private String name;
}
