package am.rockstars.dto.blog.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {

    private Long id;

    private BlogAuthorResponse author;

    @JsonProperty(value = "banner_img")
    private String bannerImg;

    private String content;

    private String image;

    @JsonProperty(value = "post_type")
    private String postType;

    @JsonProperty(value = "short_content")
    private String shortContent;

    private List<String> tags;

    @JsonProperty(value = "user_comments")
    private List<UserCommentResponse> userComments;

}
