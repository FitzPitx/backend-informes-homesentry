package com.microservice.references.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Nationalized;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ReferenciaId implements java.io.Serializable {
    private static final long serialVersionUID = 7040292327203410130L;
    @NotNull
    @Column(name = "CATEGORIA_MRI", nullable = false)
    private Long categoriaMri;

    @NotNull
    @Column(name = "SUBCATEGORIA_MRI", nullable = false)
    private Long subcategoriaMri;

    @NotNull
    @Column(name = "LINEA_MRI", nullable = false)
    private Long lineaMri;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "COD_MRI", nullable = false, length = 50)
    private String codMri;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReferenciaId entity = (ReferenciaId) o;
        return Objects.equals(this.subcategoriaMri, entity.subcategoriaMri) &&
                Objects.equals(this.codMri, entity.codMri) &&
                Objects.equals(this.lineaMri, entity.lineaMri) &&
                Objects.equals(this.categoriaMri, entity.categoriaMri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subcategoriaMri, codMri, lineaMri, categoriaMri);
    }

}