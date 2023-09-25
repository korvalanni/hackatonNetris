package ru.urfu.FrameVision.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

/**
 * This class is used to represent a user entity.
 * It contains the client id, the nickname, the password and the video list.
 * The client id is used to identify the client.
 * The nickname is used to identify the user.
 * The password is used to identify the user.
 * The video list is used to represent the user's video list.
 *
 * @author korvalanni
 */
@Entity
@Table(name = "user_entity")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID clientId;

    @Column(unique = true)
    private String nickname;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_list_id", referencedColumnName = "id")
    private VideoList videoList;

    public UserEntity(UUID clientId, String nickname, String password, VideoList videoList) {
        this.clientId = clientId;
        this.nickname = nickname;
        this.password = password;
        this.videoList = videoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity user)) return false;
        return getId().equals(user.getId()) && getClientId().equals(user.getClientId())
                && getNickname().equals(user.getNickname()) && getPassword().equals(user.getPassword())
                && getVideoList().equals(user.getVideoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClientId(), getNickname(), getPassword(), getVideoList());
    }
}
