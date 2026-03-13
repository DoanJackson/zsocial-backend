package com.example.zsocial.backend.media.model;

import com.example.zsocial.backend.chat.model.Message;
import com.example.zsocial.backend.comment.model.Comment;
import com.example.zsocial.backend.media.model.enums.MediaType;
import com.example.zsocial.backend.posts.model.Posts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.List;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url", nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private MediaType type;

    @Column(name = "cloud_name", columnDefinition = "TEXT")
    private String cloudName;

//    @ManyToMany(mappedBy = "medias")
//    private List<Posts> posts;
//
//    @ManyToMany(mappedBy = "medias")
//    private List<Comment> comments;
//
//    @ManyToMany(mappedBy = "medias")
//    private List<Message> messages;
}
