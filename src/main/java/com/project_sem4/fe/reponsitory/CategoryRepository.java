package com.project_sem4.fe.reponsitory;
import com.project_sem4.fe.entity.Category;
import com.project_sem4.fe.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    List<Category> findAllById(long id);
//    Page<Category> findAllByIdAndTitleAndCreatedAtLSGreaterThanEqual(long id, String title, long createdAt , Pageable pageable);
//    @Query("select a from Category as a where a.id = :id and a.title = :title and a.createdAt  >= :createdAt")
//    Page<Category> findByIdAndTitleAndCreatedAt(@Param("id") int status, @Param("title") String title, @Param("createdAt") long createdAt, Pageable pageable);
    List<Category> findByName(String name);
    Page<Category> findAllByCode(int code, Pageable pageable);
}
