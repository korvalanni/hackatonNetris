package ru.urfu.FrameVision.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.FrameVision.models.Video;

import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    /**
     * в базе данных делает отметку, что загрузка видео завершена
     * @param videoId
     */
    public void markAsProcessed(String videoId) {
        Optional<Video> optionalVideo = videoRepository.findByVideoId(videoId);
        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            video.setProcessed(true);
            videoRepository.save(video);
        } else {
            System.out.println("Видосика нету");
        }
    }

    /**
     * в базе данных делает отметку про то, что загрузка началась
     * @param video
     */
    public void markAsStarted(Video video){
        Optional<Video> optionalVideo = videoRepository.findByVideoId(video.getVideoId());
        if (!optionalVideo.isPresent()) {
            videoRepository.save(video);
        }
    }
}
