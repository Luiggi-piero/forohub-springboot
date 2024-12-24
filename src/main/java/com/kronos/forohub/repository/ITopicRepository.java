package com.kronos.forohub.repository;

import com.kronos.forohub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

    @Query("""
            select count(t) > 0
            from Topic t
            where t.title = :title and t.message = :message
            """)
    boolean existsByTitleAndMessage(String title, String message);
}
