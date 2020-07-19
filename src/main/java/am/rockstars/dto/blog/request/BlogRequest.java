package am.rockstars.dto.blog.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class BlogRequest {

    private Long id;

    @JsonProperty(value = "author_name")
    private String authorName;

    @JsonProperty(value = "banner_img")
    private String bannerImg;

    private String content;

    private String image;

    private String name;

    @JsonProperty(value = "post_type")
    private String postType;

    @JsonProperty(value = "short_content")
    private String shortContent;

    private List<String> tags;
}
