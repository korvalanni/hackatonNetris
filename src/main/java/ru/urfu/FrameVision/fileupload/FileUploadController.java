package ru.urfu.FrameVision.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.FrameVision.models.Video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    private final String UPLOAD_DIR = "/chunks/";
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload-video")
    public ResponseEntity<String> uploadFile(
            @RequestParam("chunk_id") Long chunkId,
            @RequestParam("chunk") MultipartFile video,
            @RequestParam("client_id") String clientId,
            @RequestParam("video_id") String videoId,
            @RequestParam("complete") Boolean complete
    ) {
        // The chunk file name will be its ID
        String fileName = String.format("%05d", chunkId); // leading zeros

        // File upload directory for each client and video
        String uploadDir = UPLOAD_DIR + clientId + "/" + videoId + "/";

        // Create directory if it doesn't exist
        File file = new File(uploadDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            // Save the chunk
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, video.getBytes());
//            Video video = new Video();
//            videoService.markAsStarted();
            if(complete) {
                videoService.markAsProcessed(videoId);
            }


            return ResponseEntity.ok("Chunk received successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to store chunk");
        }
    }
}
