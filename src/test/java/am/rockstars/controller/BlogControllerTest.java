package am.rockstars.controller;

import am.rockstars.dto.blog.request.BlogAuthorRequest;
import am.rockstars.dto.blog.request.BlogRequest;
import am.rockstars.dto.blog.request.UserCommentRequest;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BlogControllerTest extends AbstractControllerTest {

    private static final String BASE_PATH = "/api/blog";

    @ClassRule
    public static PostgreSQLContainer<PostgreSqlContainer> postgreSQLContainer = PostgreSqlContainer.getInstance();

    @DisplayName("Should retrieve Blogs")
    @Test
    void getAllBlogs() throws Exception {
        //Test data
        final Long id = 1L;
        //API calls
        mockMvc.perform(get(BASE_PATH ))
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("[0].id").value(id)));
    }

    @DisplayName("Should should")
    @Test
    void addComment() throws Exception {
        //Test data
        UserCommentRequest userCommentRequest = randomObject.nextObject(UserCommentRequest.class);
        userCommentRequest.setId(null);
        //API calls

        mockMvc.perform(post("/api/blog/1/comment")
                .content(mapper.writeValueAsString(userCommentRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(BASE_PATH))
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("[0].user_comments[3].name").value(userCommentRequest.getName())));
    }

    @DisplayName("Should update About information by payload")
    @Test
    void addSubComment() throws Exception {
        //Test data
        UserCommentRequest userCommentRequest = randomObject.nextObject(UserCommentRequest.class);
        userCommentRequest.setId(null);
        //API calls

        mockMvc.perform(post("/api/blog/1/comment/1/sub-comment")
                .content(mapper.writeValueAsString(userCommentRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(BASE_PATH))
                .andDo(print())
                .andExpect(matchAll(
                        status().isOk(),
                        jsonPath("[0].user_comments[0].sub_comments[2].name").value(userCommentRequest.getName())));
    }

    @DisplayName("Should create Blog")
    @Test
    void createBlog() throws Exception {
       final BlogAuthorRequest authorRequest = randomObject.nextObject(BlogAuthorRequest.class);
       final BlogRequest blogRequest = randomObject.nextObject(BlogRequest.class);
       final String tagName = "new tag";

       authorRequest.setId(null);
       blogRequest.setId(null);
       blogRequest.setTags(Collections.singletonList(tagName));
       blogRequest.setAuthorName(authorRequest.getName());

        mockMvc.perform(post("/api/blog/tag/".concat(tagName))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/blog/author")
                .content(mapper.writeValueAsString(authorRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/blog")
                .content(mapper.writeValueAsString(blogRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
