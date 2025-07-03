package com.playita.repository;

import com.playita.entity.DetalleRea;
import com.playita.entity.DetalleReaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleReaRepository extends JpaRepository<DetalleRea, DetalleReaId> {
    List<DetalleRea> findByReabastecimiento_Id(Integer idReabastecimiento);
}
