package com.colpatria.bootcamp.repository;

import com.colpatria.bootcamp.entity.MessageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageJpaRepository extends JpaRepository<MessageJpaEntity, Long> {

    Optional<MessageJpaEntity> findByMessage(String message);
}
