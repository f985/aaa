package am.rockstars.dto.blog.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentResponse {

    private Long id;

    private String comment;

    private String date;

    @JsonProperty(value = "img")
    private String image;

    private String name;

    @JsonProperty(value = "sub_comments")
    private List<UserSubCommentResponse> subComments;
}
