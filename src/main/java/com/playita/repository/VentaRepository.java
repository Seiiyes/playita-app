package com.playita.repository;

import com.playita.entity.Venta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT v.fecha_v, SUM(v.total) "
            + "FROM Venta v "
            + "WHERE v.fecha_v BETWEEN :inicio AND :fin "
            + "GROUP BY v.fecha_v ORDER BY v.fecha_v")
    List<Object[]> obtenerIngresosPorFechaRaw(@Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

}
