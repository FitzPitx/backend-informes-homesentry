package com.microservice.subcategories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SubcategoriaId implements java.io.Serializable {
    private static final long serialVersionUID = -1375674728295312528L;
    @NotNull
    @Column(name = "id_categoria", nullable = false)
    private Integer idCategoria;

    @NotNull
    @Column(name = "id_subcategoria", nullable = false)
    private Integer idSubcategoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubcategoriaId entity = (SubcategoriaId) o;
        return Objects.equals(this.idSubcategoria, entity.idSubcategoria) &&
                Objects.equals(this.idCategoria, entity.idCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubcategoria, idCategoria);
    }

}