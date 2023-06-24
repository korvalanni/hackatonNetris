package ru.urfu.FrameVision.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * This class is used to represent a video chunk entity.
 * It contains the id, the video entity, the chunk number and the chunk itself.
 * The video is used to identify the video entity.
 * The chunk number is used to identify the chunk number.
 * The chunk is used to represent the video chunk.
 * The complete is used to identify the chunk completeness.
 * The timestamp is used to identify the chunk creation time.
 *
 * @author korvalanni
 */
@Entity
@Table(name = "video_chunk")
@Getter
@Setter
@NoArgsConstructor
public class VideoChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Video video;
    private Long chunkNumber;
    private byte[] chunk;
    private Boolean complete;
    @CreatedDate
    private Date timestamp;

    public VideoChunk(Long chunkNumber, byte[] chunk, Boolean complete, Date timestamp) {
        this.chunkNumber = chunkNumber;
        this.chunk = chunk;
        this.complete = complete;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoChunk that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getVideo(),
                that.getVideo()) && Objects.equals(getChunkNumber(),
                that.getChunkNumber()) && Arrays.equals(getChunk(),
                that.getChunk()) && Objects.equals(getComplete(),
                that.getComplete()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getVideo(), getChunkNumber(), getComplete(), getTimestamp());
        result = 31 * result + Arrays.hashCode(getChunk());
        return result;
    }

    public VideoChunk(Long chunkNumber, byte[] chunk, Boolean complete) {
        this.chunkNumber = chunkNumber;
        this.chunk = chunk;
        this.complete = complete;
    }

}
