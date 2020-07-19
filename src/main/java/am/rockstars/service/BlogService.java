package am.rockstars.service;

import am.rockstars.dto.blog.request.BlogAuthorRequest;
import am.rockstars.dto.blog.request.BlogRequest;
import am.rockstars.dto.blog.request.UserCommentRequest;
import am.rockstars.dto.blog.response.BlogResponse;
import am.rockstars.entity.*;
import am.rockstars.mapper.BlogMapper;
import am.rockstars.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static am.rockstars.entity.util.Utils.illegalArg;

@Service
@Slf4j
public class BlogService {


    private final BlogRepository blogRepository;
    private final BlogAuthorRepository blogAuthorRepository;
    private final UserSubCommentRepository userSubCommentRepository;
    private final UserCommentRepository userCommentRepository;
    private final TagRepository tagRepository;
    private final BlogMapper mapper;

    public BlogService(BlogRepository blogRepository,
                       BlogAuthorRepository blogAuthorRepository,
                       UserSubCommentRepository userSubCommentRepository,
                       UserCommentRepository userCommentRepository,
                       TagRepository tagRepository, BlogMapper mapper
    ) {
        this.blogRepository = blogRepository;
        this.blogAuthorRepository = blogAuthorRepository;
        this.userSubCommentRepository = userSubCommentRepository;
        this.userCommentRepository = userCommentRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    public List<BlogResponse> getBlogs() {
        log.debug("Trying to get all blogs");
        final List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(this::fetchBlogResponse).collect(Collectors.toList());
    }

    @Transactional
    public void add(BlogRequest blogRequest) {
        log.debug("Trying add new blog '{}'", blogRequest);
        final BlogAuthor blogAuthor = blogAuthorRepository.findByName(blogRequest.getAuthorName())
                .orElseThrow(illegalArg("Cannot find Blog Author by name", blogRequest.getAuthorName()));
        final Blog blog = mapper.map(blogRequest);
        final List<BlogTag> blogTags = fetchBlogTags(blogRequest);
        blog.setAuthor(blogAuthor);
        blog.setBlogTags(blogTags);
        blogAuthorRepository.save(blogAuthor);
        blogRepository.save(blog);
        log.debug("Successfully added new blog '{}'", blog);
    }

    @Transactional
    public void addBlogAuthor(final BlogAuthorRequest blogAuthorRequest) {
        log.debug("Trying add new blog author '{}'", blogAuthorRequest);
        final BlogAuthor blogAuthor = mapper.mapBlogAuthor(blogAuthorRequest);
        blogAuthorRepository.save(blogAuthor);
    }

    @Transactional
    public void addComment(final UserCommentRequest request, final Long blogId) {
        log.debug("Trying add by blog id '{}' new comment '{}'", blogId, request);
        final UserComment userComment = mapper.mapComment(request);
        userComment.setSubComments(new ArrayList<>());
        final Blog blog = blogRepository.findById(blogId)
                .orElseThrow(illegalArg("Cannot find Blog by id", blogId));
        blog.addComment(userComment);
        userCommentRepository.save(userComment);
        blogRepository.save(blog);
        log.debug("Successfully added new comment '{}'", userComment);
    }

    @Transactional
    public void addSubComment(final UserCommentRequest request, final Long commentId) {
        log.debug("Trying add by comment id '{}' new sub comment '{}'", commentId, request);
        final UserSubComment userSubComment = mapper.mapSubComment(request);
        final UserComment userComment = userCommentRepository.findById(commentId)
                .orElseThrow(illegalArg("Cannot find user comment by id", commentId));
        userComment.addSubComment(userSubComment);
        userSubCommentRepository.save(userSubComment);
        userCommentRepository.save(userComment);
        log.debug("Successfully added new sub comment '{}'", userSubComment);
    }

    @Transactional
    public void addTag(final String tagName) {
        log.debug("Trying add new blog tag by name '{}'", tagName);
        final BlogTag blogTag = new BlogTag();
        blogTag.setName(tagName);
        tagRepository.save(blogTag);
        log.debug("Successfully added new blog tag '{}'", blogTag);
    }

    private List<BlogTag> fetchBlogTags(BlogRequest blogRequest) {
        return blogRequest.getTags().stream()
                .map(tagRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList());
    }

    private BlogResponse fetchBlogResponse(Blog blog) {
        final BlogResponse blogResponse = mapper.map(blog);
        final List<String> tags = blog.getBlogTags().stream().map(BlogTag::getName).collect(Collectors.toList());
        blogResponse.setTags(tags);
        return blogResponse;
    }

}
