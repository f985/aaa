package am.rockstars.service;

import am.rockstars.dto.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    @Value("${fileserver.folder}")
    private String folder;

    @Value("${fileserver.url}")
    private String url;

    @Value("${fileserver.root}")
    private String root;


    public FileInfo uploadFile(final MultipartFile file) throws IOException {
        final var folderUUID = UUID.randomUUID().toString();
        final var path = Path.of(root, File.separator, folder, File.separator, folderUUID);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (file.getOriginalFilename() == null) {
            throw new IllegalArgumentException("Failed to upload the file.");
        }

        Files.write(path.resolve(file.getOriginalFilename()), file.getBytes());

        final var fileInfo = new FileInfo();
        fileInfo.setFileUri(url + "?path=" + folderUUID + "&name=" + file.getOriginalFilename());
        fileInfo.setFileName(file.getOriginalFilename());

        return fileInfo;
    }

    public File download(final String path) throws IOException {
        return Files.walk(Paths.get(root, File.separator, folder, File.separator, path))
                .filter(Files::isRegularFile)
                .findFirst()
                .orElseThrow()
                .toFile();
    }
}