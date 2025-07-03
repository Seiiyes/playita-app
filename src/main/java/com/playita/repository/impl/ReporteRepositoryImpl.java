package com.playita.repository.impl;

import com.playita.dto.ReporteProductoDTO;
import com.playita.repository.ReporteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class ReporteRepositoryImpl implements ReporteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ReporteProductoDTO> consultarPorFiltros(Map<String, String> filtros) {
        String jpql = "SELECT new com.playita.dto.ReporteProductoDTO("
                + " p.nombre, "
                + " c.nombre, "
                + " SUM(dv.cantidad_ventaP), "
                + " SUM(dv.subtotal), "
                + " p.cantidadStock, "
                + " MAX(v.fecha_v)) "
                + "FROM DetalleVenta dv "
                + "JOIN dv.producto p "
                + "JOIN p.categoria c "
                + "JOIN dv.venta v "
                + "WHERE 1=1 ";

        if (filtros.get("categoria") != null && !filtros.get("categoria").isEmpty()) {
            jpql += " AND c.id = :categoria ";
        }
        if (filtros.get("startDate") != null && !filtros.get("startDate").isEmpty()) {
            jpql += " AND v.fecha_v >= :startDate ";
        }
        if (filtros.get("endDate") != null && !filtros.get("endDate").isEmpty()) {
            jpql += " AND v.fecha_v <= :endDate ";
        }

        jpql += " GROUP BY p.id, p.nombre, c.nombre, p.cantidadStock "
                + " ORDER BY SUM(dv.cantidad_ventaP) DESC";

        Query query = em.createQuery(jpql);

        if (filtros.get("categoria") != null && !filtros.get("categoria").isEmpty()) {
            query.setParameter("categoria", Integer.valueOf(filtros.get("categoria")));
        }
        if (filtros.get("startDate") != null && !filtros.get("startDate").isEmpty()) {
            query.setParameter("startDate", LocalDate.parse(filtros.get("startDate")));
        }
        if (filtros.get("endDate") != null && !filtros.get("endDate").isEmpty()) {
            query.setParameter("endDate", LocalDate.parse(filtros.get("endDate")));
        }

        return query.getResultList();
    }
    @Override
    public List<Object[]> obtenerIngresosPorFecha(LocalDate inicio, LocalDate fin) {
        String jpql = "SELECT v.fecha_v, SUM(v.total) "
                + "FROM Venta v "
                + "WHERE v.fecha_v BETWEEN :inicio AND :fin "
                + "GROUP BY v.fecha_v ORDER BY v.fecha_v";

        return em.createQuery(jpql, Object[].class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .getResultList();
    }
}
