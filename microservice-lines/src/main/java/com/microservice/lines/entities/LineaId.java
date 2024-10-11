package com.microservice.lines.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LineaId implements java.io.Serializable {
    private static final long serialVersionUID = 3678283916012596243L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "CODLIN_LEA", nullable = false)
    private Short codlinLea;

    @jakarta.validation.constraints.NotNull
    @Column(name = "CODSUB_LEA", nullable = false)
    private Short codsubLea;

    @jakarta.validation.constraints.NotNull
    @Column(name = "CODCAT_LEA", nullable = false)
    private Short codcatLea;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LineaId entity = (LineaId) o;
        return Objects.equals(this.codlinLea, entity.codlinLea) &&
                Objects.equals(this.codsubLea, entity.codsubLea) &&
                Objects.equals(this.codcatLea, entity.codcatLea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codlinLea, codsubLea, codcatLea);
    }

}