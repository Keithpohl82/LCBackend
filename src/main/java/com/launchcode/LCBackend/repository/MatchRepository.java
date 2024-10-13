package com.launchcode.LCBackend.repository;

import com.launchcode.LCBackend.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findByEmail(String email);
}
