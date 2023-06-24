package ru.urfu.FrameVision.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.FrameVision.models.Video;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    Optional<Video> findByVideoId(String videoId);
}