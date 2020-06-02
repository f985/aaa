package am.rockstars.service;

import am.rockstars.dto.FileInfo;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FileService {

    @Value("${fileserver.folder}")
    private final String folder;

    @Value("${fileserver.url}")
    private final String url;

    public FileInfo uploadFile(MultipartFile file) throws IOException {
        var folderUUID = UUID.randomUUID().toString();
        String root = System.getenv("SystemDrive");

        var path = Path.of(root, File.separator, folder, File.separator, folderUUID);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        if (file.getOriginalFilename() == null) {
            throw new IllegalArgumentException("Failed to upload the file.");
        }

        Files.write(path.resolve(file.getOriginalFilename()), file.getBytes());

        var fileInfo = new FileInfo();
        fileInfo.setFileUri(url + "?path=" + folderUUID + "&name=" + file.getOriginalFilename());
        fileInfo.setFileName(file.getOriginalFilename());

        return fileInfo;
    }

    public File download(String path) throws IOException {
        String root = System.getenv("SystemDrive");
        return Files.walk(Paths.get(root, File.separator, folder, File.separator, path))
                .filter(Files::isRegularFile)
                .findFirst()
                .orElseThrow()
                .toFile();
    }
}