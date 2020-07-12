package am.rockstars.service;

import am.rockstars.dto.blog.request.BlogAuthorRequest;
import am.rockstars.dto.blog.request.BlogRequest;
import am.rockstars.dto.blog.request.UserCommentRequest;
import am.rockstars.dto.blog.response.BlogResponse;
import am.rockstars.entity.Blog;
import am.rockstars.entity.BlogAuthor;
import am.rockstars.entity.UserComment;
import am.rockstars.entity.UserSubComment;
import am.rockstars.mapper.BlogMapper;
import am.rockstars.repository.BlogAuthorRepository;
import am.rockstars.repository.BlogRepository;
import am.rockstars.repository.UserCommentRepository;
import am.rockstars.repository.UserSubCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
public class BlogService {


    private final BlogRepository blogRepository;
    private final BlogAuthorRepository blogAuthorRepository;
    private final UserSubCommentRepository userSubCommentRepository;
    private final UserCommentRepository userCommentRepository;
    private final BlogMapper mapper;

    public BlogService(BlogRepository blogRepository,
                       BlogAuthorRepository blogAuthorRepository,
                       UserSubCommentRepository userSubCommentRepository,
                       UserCommentRepository userCommentRepository,
                       BlogMapper mapper
    ) {
        this.blogRepository = blogRepository;
        this.blogAuthorRepository = blogAuthorRepository;
        this.userSubCommentRepository = userSubCommentRepository;
        this.userCommentRepository = userCommentRepository;
        this.mapper = mapper;
    }

    public List<BlogResponse> getBlogs() {
        return mapper.map(blogRepository.findAll());
    }

    @Transactional
    public void add(BlogRequest blogRequest) {
        final BlogAuthor blogAuthor = blogAuthorRepository.findByName(blogRequest.getAuthorName())
                .orElseThrow(illegalArg("Cannot find Blog Author by name", blogRequest.getAuthorName()));
        final Blog blog = mapper.map(blogRequest);
        blog.setAuthor(blogAuthor);
        blogAuthorRepository.save(blogAuthor);
        blogRepository.save(blog);
    }

    @Transactional
    public void addBlogAuthor(final BlogAuthorRequest blogAuthorRequest) {
        final BlogAuthor blogAuthor = mapper.mapBlogAuthor(blogAuthorRequest);
        blogAuthorRepository.save(blogAuthor);
    }

    @Transactional
    public void addComment(final UserCommentRequest request, final Long blogId) {
        final UserComment userComment = mapper.mapComment(request);
        userComment.setSubComments(new ArrayList<>());
        final Blog blog = blogRepository.findById(blogId)
                .orElseThrow(illegalArg("Cannot find Blog by id", blogId));
        blog.addComment(userComment);
        userCommentRepository.save(userComment);
        blogRepository.save(blog);
    }

    @Transactional
    public void addSubComment(final UserCommentRequest request, final Long commentId) {
        final UserSubComment userSubComment = mapper.mapSubComment(request);
        final UserComment userComment = userCommentRepository.findById(commentId)
                .orElseThrow(illegalArg("Cannot find user comment by id", commentId));
        userComment.addSubComment(userSubComment);
        userSubCommentRepository.save(userSubComment);
        userCommentRepository.save(userComment);
    }

}
