package com.example.bootshelf.certification.repository;



import com.example.bootshelf.certification.Certification;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {

    Optional<Certification> findByUser_Idx(Integer userIdx);

}

