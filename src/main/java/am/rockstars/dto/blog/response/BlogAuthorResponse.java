package am.rockstars.dto.blog.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
public class BlogAuthorResponse {

    private Long id;

    @JsonProperty(value = "author_bio")
    private String bio;

    @JsonProperty(value = "post_date")
    private String postDate;

    @JsonProperty(value = "author_img")
    private String image;

    @JsonProperty(value = "author_name")
    private String name;
}
