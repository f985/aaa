package am.rockstars.controller;

import am.rockstars.dto.blog.request.BlogAuthorRequest;
import am.rockstars.dto.blog.request.BlogRequest;
import am.rockstars.dto.blog.request.UserCommentRequest;
import am.rockstars.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(blogService.getBlogs());
    }

    @PostMapping("/author")
    public ResponseEntity<?> createBlogAuthor(@RequestBody BlogAuthorRequest blogAuthor) {
        blogService.addBlogAuthor(blogAuthor);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody BlogRequest blogRequest) {
        blogService.add(blogRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{blogId}/comment")
    public ResponseEntity<?> createBlogComment(@PathVariable Long blogId, @RequestBody UserCommentRequest request) {
        blogService.addComment(request, blogId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{blogId}/comment/{commentId}/sub-comment")
    public ResponseEntity<?> createBlogSubComment(@PathVariable Long blogId, @PathVariable Long commentId, @RequestBody UserCommentRequest request) {
        blogService.addSubComment(request, commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tag/{tagName}")
    public ResponseEntity<?> createTag(@PathVariable String tagName) {
        blogService.addTag(tagName);
        return ResponseEntity.ok().build();
    }

}
