package com.microservice.subcategories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SubcategoriaId implements java.io.Serializable {
    private static final long serialVersionUID = 7920309635685405490L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "CODCAT_SCE", nullable = false)
    private Short codcatSce;

    @jakarta.validation.constraints.NotNull
    @Column(name = "CODSUB_SCE", nullable = false)
    private Short codsubSce;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubcategoriaId entity = (SubcategoriaId) o;
        return Objects.equals(this.codcatSce, entity.codcatSce) &&
                Objects.equals(this.codsubSce, entity.codsubSce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codcatSce, codsubSce);
    }

}