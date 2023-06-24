package ru.urfu.FrameVision.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "video_list")
@Getter
@Setter
@NoArgsConstructor
public class VideoList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientId;

    // TODO: ???
    @OneToOne(mappedBy = "videoList")
    private UserEntity user;

    @OneToMany(mappedBy = "videoList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos;

    public VideoList(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoList videoList)) return false;
        return getId().equals(videoList.getId()) && getClientId().equals(videoList.getClientId())
                && getVideos().equals(videoList.getVideos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClientId(), getVideos());
    }
}
