package am.rockstars.dto.blog.response;

import am.rockstars.dto.TagInfo;
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

    private List<TagInfo> tags;

    @JsonProperty(value = "user_comments")
    private List<UserCommentResponse> userComments;

}
