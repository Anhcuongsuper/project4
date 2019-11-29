package com.project_sem4.fe.reponsitory;


import com.project_sem4.fe.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(@Param("email") String email);

    Optional<Account> findByFullname(@Param("fullname") String fullname);
}
