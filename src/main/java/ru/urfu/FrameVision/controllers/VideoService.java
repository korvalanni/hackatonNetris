package ru.urfu.FrameVision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.FrameVision.models.Video;

import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public void markAsProcessed(String videoId) {
        Optional<Video> optionalVideo = videoRepository.findByVideoId(videoId);
        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            video.setProcessed(true);
            videoRepository.save(video);
        } else {
            // Handle case where videoId is not found in the database
        }
    }
}
