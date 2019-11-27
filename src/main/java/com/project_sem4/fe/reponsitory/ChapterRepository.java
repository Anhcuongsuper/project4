package com.project_sem4.fe.reponsitory;

import com.project_sem4.fe.entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
        Optional<Chapter> findById(long chapId);
        Optional<Chapter> findByIdAndStoryId(long chapId, long storyId);
       Page<Chapter> findAllByStoryId(long storyId, Pageable pageable);

}
