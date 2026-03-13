package com.example.zsocial.backend.media.repository;

import com.example.zsocial.backend.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query(value = """
              select m.* from media m
              inner join media_posts mp on m.id = mp.media_id
            where mp.post_id = :postId
            
              union 
            
              select m.* from media m
              inner join media_comment mc on m.id = mc.media_id
              inner join comment c on mc.comment_id = c.id
            where c.post_id = :postId
            """, nativeQuery = true)
//    @Query("select distinct mtLeft FROM Media mt" +
//            "Left JOIN mtLeft.posts p " +
//            "Left JOIN mtLeft.comments c " +
//            "WHERE p.id = :postId OR c.post.id = :postId")
    List<Media> findAllByPostId(@Param("postId") Long postId);

    @Query("SELECT p.id, m FROM Posts p JOIN p.medias m WHERE p.id IN :postIds")
    List<Object[]> findAllByPostIds(@Param("postIds") List<Long> postIds);

    @Query("SELECT msg.id, m FROM Message msg JOIN msg.medias m WHERE msg.id IN :messageIds")
    List<Object[]> findAllByMessageIds(@Param("messageIds") List<Long> messageIds);
}
