package com.project_sem4.fe.reponsitory;

import com.project_sem4.fe.entity.Account;
import com.project_sem4.fe.entity.Chapter;
import com.project_sem4.fe.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> getStoryByView(int view);

    Page<Story> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Story> findAllByOrderByRateDescActor(Pageable pageable);


    Page<Story> findAllByOrderByRateDesc(Pageable pageable);

    Optional<Story> findById(long storyId);

    Page<Story> findByTitleContaining(String term, Pageable pageable);

    Page<Story> findAll(Specification specification, Pageable pageable);
}
