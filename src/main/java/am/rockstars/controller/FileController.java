package am.rockstars.controller;

import am.rockstars.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<?> downloadFile(@RequestParam(value = "path") final String path) throws IOException {
        final var file = fileService.download(path);

        final var contentDisposition = ContentDisposition.builder("inline")
                .filename(file.getName(), StandardCharsets.UTF_8)
                .build();

        final var resource = new UrlResource(file.toURI());

        final var mediaType = MediaTypeFactory.getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") final MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(multipartFile));
    }
}