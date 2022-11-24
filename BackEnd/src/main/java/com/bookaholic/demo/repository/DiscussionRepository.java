package com.bookaholic.demo.repository;


import com.bookaholic.demo.entity.DiscussionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface DiscussionRepository extends JpaRepository<DiscussionEntity, UUID> {
    DiscussionEntity findByDiscussionId(UUID discussionId);
    List<DiscussionEntity> findByBookId(UUID bookId);
    DiscussionEntity save(DiscussionEntity discussionEntity);
    @Query(value="SELECT CAST(discussion.discussion_id as varchar) discussionId, CAST(discussion.book_id as varchar) bookId, discussion.content, discussion.creating_time, users.user_name From discussion JOIN users ON discussion.creator = users.user_id WHERE book_id = CAST(:bookId as uuid) ORDER BY creating_time DESC", nativeQuery=true)
    List<Map<String, Object>> ListDiscussionsOfOneBook(UUID bookId);
}
