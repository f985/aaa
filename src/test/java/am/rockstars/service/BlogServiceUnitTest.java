package am.rockstars.service;

import am.rockstars.dto.blog.response.BlogResponse;
import am.rockstars.entity.Blog;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        verifyNoMoreInteractions(blogRepository);
        //Asserts
        assertThat(blogResponseList).hasSize(1);
        assertThat(blogResponseList.get(0)).isEqualTo(blogResponse);
    }
}
