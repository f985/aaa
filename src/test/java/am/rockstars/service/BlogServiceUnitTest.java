package am.rockstars.service;

import am.rockstars.dto.blog.request.BlogAuthorRequest;
import am.rockstars.dto.blog.request.BlogRequest;
import am.rockstars.dto.blog.request.UserCommentRequest;
import am.rockstars.dto.blog.response.BlogResponse;
import am.rockstars.entity.*;
import am.rockstars.mapper.BlogMapper;
import am.rockstars.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class BlogServiceUnitTest extends AbstractServiceUnitTest {

    @InjectMocks
    private BlogService blogService;

    @Mock
    private BlogRepository blogRepository;
    @Mock
    private BlogAuthorRepository blogAuthorRepository;
    @Mock
    private UserSubCommentRepository userSubCommentRepository;
    @Mock
    private UserCommentRepository userCommentRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private BlogMapper mapper;

    @DisplayName("Should return Blog Response list")
    @Test
    void thatCanGetAllBlogs() {
        //Test data
        final Blog blog = easyRandom.nextObject(Blog.class);
        final List<Blog> blogs = Collections.singletonList(blog);
        final BlogResponse blogResponse = easyRandom.nextObject(BlogResponse.class);
        //Mock
        when(blogRepository.findAll()).thenReturn(blogs);
        when(mapper.map(blog)).thenReturn(blogResponse);
        //Service call
        final List<BlogResponse> blogResponseList = blogService.getBlogs();
        //Verify
        verify(blogRepository).findAll();
        verify(mapper).map(blog);
        verifyNoMoreInteractions(blogRepository, mapper);
        //Asserts
        assertThat(blogResponseList).hasSize(1);
        assertThat(blogResponseList.get(0)).isEqualTo(blogResponse);
    }

    @DisplayName("Should add Blog tag")
    @Test
    void thatCanAddBlogTag() {
        //Test data
        final String tagName = "new Tag";
        //Service call
        blogService.addTag(tagName);
        //Verify
        verify(tagRepository).save(any(BlogTag.class));
        verifyNoMoreInteractions(tagRepository);
    }

    @DisplayName("Should add Blog Author")
    @Test
    void thatCanAddBlogAuthor() {
        //Test data
        final BlogAuthorRequest authorRequest = easyRandom.nextObject(BlogAuthorRequest.class);
        final BlogAuthor author = easyRandom.nextObject(BlogAuthor.class);
        //mock
        when(mapper.mapBlogAuthor(authorRequest)).thenReturn(author);
        //Service call
        blogService.addBlogAuthor(authorRequest);
        //Verify
        verify(blogAuthorRepository).save(any(BlogAuthor.class));
        verify(mapper).mapBlogAuthor(authorRequest);
        verifyNoMoreInteractions(blogAuthorRepository, mapper);
    }

    @DisplayName("Should add Blog")
    @Test
    void thatCanAddBlog() {
        //Test data
        final BlogRequest blogRequest = easyRandom.nextObject(BlogRequest.class);
        final BlogAuthor author = easyRandom.nextObject(BlogAuthor.class);
        final Blog blog = easyRandom.nextObject(Blog.class);
        final BlogTag blogTag = easyRandom.nextObject(BlogTag.class);
        blogRequest.setTags(Collections.singletonList(blogTag.getName()));
        //mock
        when(blogAuthorRepository.findByName(blogRequest.getAuthorName())).thenReturn(Optional.of(author));
        when(mapper.map(blogRequest)).thenReturn(blog);
        when(tagRepository.findByName(blogTag.getName())).thenReturn(Optional.of(blogTag));
        //Service call
        blogService.add(blogRequest);
        //Verify
        verify(blogAuthorRepository).findByName(blogRequest.getAuthorName());
        verify(mapper).map(blogRequest);
        verify(tagRepository).findByName(blogTag.getName());
        verify(blogAuthorRepository).save(author);
        verify(blogRepository).save(blog);
        verifyNoMoreInteractions(blogAuthorRepository, mapper, tagRepository, blogAuthorRepository, blogRepository);
    }

    @DisplayName("Should add Blog comment")
    @Test
    void thatCanAddComment() {
        //Test data
        final Long blogId = 1L;
        final UserCommentRequest commentRequest = easyRandom.nextObject(UserCommentRequest.class);
        final UserComment comment = easyRandom.nextObject(UserComment.class);
        final Blog blog = easyRandom.nextObject(Blog.class);
        //mock
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(blog));
        when(mapper.mapComment(commentRequest)).thenReturn(comment);
        //Service call
        blogService.addComment(commentRequest, blogId);
        //Verify
        verify(mapper).mapComment(commentRequest);
        verify(blogRepository).findById(blogId);
        verify(userCommentRepository).save(comment);
        verify(blogRepository).save(blog);
        verifyNoMoreInteractions(blogRepository, mapper, userCommentRepository, blogRepository);
    }

    @DisplayName("Should add Blog sub comment")
    @Test
    void thatCanAddSubComment() {
        //Test data
        final Long commentId = 1L;
        final UserSubComment subComment = easyRandom.nextObject(UserSubComment.class);
        final UserCommentRequest userCommentRequest = easyRandom.nextObject(UserCommentRequest.class);
        final UserComment comment = easyRandom.nextObject(UserComment.class);
        //mock
        when(userCommentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(mapper.mapSubComment(userCommentRequest)).thenReturn(subComment);
        //Service call
        blogService.addSubComment(userCommentRequest, commentId);
        //Verify
        verify(mapper).mapSubComment(userCommentRequest);
        verify(userCommentRepository).findById(commentId);
        verify(userCommentRepository).save(comment);
        verify(userSubCommentRepository).save(subComment);
        verifyNoMoreInteractions(mapper, userCommentRepository, userSubCommentRepository);
    }

    @DisplayName("Should fail while adding Blog and not found author")
    @Test
    void thatFailAddBlogWhenAuthorNotFound() {
        //Test data
        final BlogRequest blogRequest = easyRandom.nextObject(BlogRequest.class);
        //Service call
        assertThrows(EntityNotFoundException.class, () -> blogService.add(blogRequest));
        //Verify
        verify(blogAuthorRepository).findByName(blogRequest.getAuthorName());
        verifyNoMoreInteractions(blogAuthorRepository);
    }

    @DisplayName("Should fail while adding comment and not found blog")
    @Test
    void thatFailAddCommentWhenBlogNotFound() {
        //Test data
        final Long blogId = 1L;
        final UserCommentRequest request = easyRandom.nextObject(UserCommentRequest.class);
        final UserComment comment = easyRandom.nextObject(UserComment.class);
        //mock
        when(mapper.mapComment(request)).thenReturn(comment);
        //Service call
        assertThrows(EntityNotFoundException.class, () -> blogService.addComment(request, blogId));
        //Verify
        verify(mapper).mapComment(request);
        verify(blogRepository).findById(blogId);
        verifyNoMoreInteractions(mapper, blogRepository);
    }

    @DisplayName("Should fail while adding sub comment and not found comment")
    @Test
    void thatFailAddSubCommentWhenCommentNotFound() {
        //Test data
        final Long commentId = 1L;
        final UserCommentRequest request = easyRandom.nextObject(UserCommentRequest.class);
        final UserSubComment comment = easyRandom.nextObject(UserSubComment.class);
        //mock
        when(mapper.mapSubComment(request)).thenReturn(comment);
        //Service call
        assertThrows(EntityNotFoundException.class, () -> blogService.addSubComment(request, commentId));
        //Verify
        verify(mapper).mapSubComment(request);
        verify(userCommentRepository).findById(commentId);
        verifyNoMoreInteractions(mapper, userCommentRepository);
    }
}
