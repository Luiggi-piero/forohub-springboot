package com.kronos.forohub.repository;

import com.kronos.forohub.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("""
            SELECT t 
            FROM Topic t
            WHERE t.course ILIKE %:nameCourse% 
            """)
    Page<Topic> findByCourseContainsIgnoreCase(Pageable pagination, String nameCourse);


    @Query("""
            SELECT t
            FROM Topic t
            WHERE YEAR(t.creationDate) = :year
            """)
    Page<Topic> findByYear(Pageable pagination, Integer year);

    @Query("""
            SELECT t 
            FROM Topic t
            WHERE t.course ILIKE %:nameCourse% AND YEAR(t.creationDate) = :year
            """)
    Page<Topic> findByCourseAndYear(Pageable pagination, String nameCourse, Integer year);
}
