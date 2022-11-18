package com.bookaholic.demo.repository;


import com.bookaholic.demo.entity.DiscussionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiscussionRepository extends JpaRepository<DiscussionEntity, UUID> {
    DiscussionEntity findByDiscussionId(UUID discussionId);
    List<DiscussionEntity> findByBookId(UUID bookId);
    DiscussionEntity save(DiscussionEntity discussionEntity);
}
