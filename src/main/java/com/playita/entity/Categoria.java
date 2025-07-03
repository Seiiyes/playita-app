package com.playita.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_categoria")
    private Integer id;

    @Column(name = "categoria", nullable = false, length = 25)
    private String nombre;

    // Relación inversa (opcional, si quieres acceder a productos desde la categoría)
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // toString para mostrar nombre en select si se usa directamente
    @Override
    public String toString() {
        return nombre;
    }
}
