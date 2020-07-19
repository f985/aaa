package am.rockstars.mapper;

import am.rockstars.dto.blog.request.BlogAuthorRequest;
import am.rockstars.dto.blog.request.BlogRequest;
import am.rockstars.dto.blog.request.UserCommentRequest;
import am.rockstars.dto.blog.response.BlogResponse;
import am.rockstars.entity.Blog;
import am.rockstars.entity.BlogAuthor;
import am.rockstars.entity.UserComment;
import am.rockstars.entity.UserSubComment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface BlogMapper {

    BlogResponse map(Blog blog);

    Blog map(BlogRequest blogRequest);

    BlogAuthor mapBlogAuthor(BlogAuthorRequest blogAuthorRequest);

    UserComment mapComment(UserCommentRequest userCommentRequest);

    UserSubComment mapSubComment(UserCommentRequest userCommentRequest);
}
