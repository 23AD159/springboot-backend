package com.example.springintern.repository;

import com.example.springintern.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {
    RegisterDetails findByEmail(String email);

   Optional<RegisterDetails> findByUserName(String userName);

    @Query("SELECT u FROM RegisterDetails u JOIN u.roles r WHERE r.roleName = :roleName")
    List<RegisterDetails> findByRoleName(@Param("roleName") String roleName);

}
