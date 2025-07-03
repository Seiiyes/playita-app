package com.playita.repository;

import com.playita.entity.DetalleVenta;
import com.playita.entity.DetalleVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaId> {
}
