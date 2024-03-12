package com.example.bootshelf.certification.repository;



import com.example.bootshelf.certification.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {

}
