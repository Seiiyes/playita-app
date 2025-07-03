package com.playita.repository;

import com.playita.entity.Reabastecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReabastecimientoRepository extends JpaRepository<Reabastecimiento, Integer> {
}
