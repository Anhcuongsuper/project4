package com.project_sem4.fe.reponsitory;

import com.project_sem4.fe.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}
