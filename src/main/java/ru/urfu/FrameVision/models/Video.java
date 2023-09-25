package ru.urfu.FrameVision.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


/**
 * This class is used to represent a video entity.
 * It contains the client id, the video id and the list of chunks.
 * The client id is used to identify the client.
 * The id is used to identify the video chunk list.
 * The chunks are used to represent the video chunks.
 *
 * @author korvalanni
 */
@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientId;
    private String videoId;
    @Column
    private boolean processed = false;

    @ManyToOne
    @JoinColumn(name = "video_list_id", referencedColumnName = "id")
    private VideoList videoList;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoChunk> chunks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video video)) return false;
        return getId().equals(video.getId()) && getClientId().equals(video.getClientId())
                && getVideoList().equals(video.getVideoList()) && getChunks().equals(video.getChunks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClientId(), getVideoList(), getChunks());
    }

    public Video(String clientId) {
        this.clientId = clientId;
    }
}
