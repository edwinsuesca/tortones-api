package com.tortones.APItortones.repository;

import com.tortones.APItortones.model.Porcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorcionRepository extends JpaRepository<Porcion, Long> {
}
