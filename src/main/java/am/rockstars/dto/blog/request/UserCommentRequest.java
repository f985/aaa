package am.rockstars.dto.blog.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentRequest {

    private Long id;

    private String comment;

    private String date;

    private String image;

    private String name;
}
