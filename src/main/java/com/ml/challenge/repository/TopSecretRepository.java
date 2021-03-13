package com.ml.challenge.repository;

import com.ml.challenge.model.TopSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopSecretRepository extends JpaRepository<TopSecret, String> {

    Optional<TopSecret> findByName(String name);
}
