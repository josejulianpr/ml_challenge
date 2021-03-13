package com.ml.challenge.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "top_secret")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
public class TopSecret implements Serializable {

    private static final long serialVersionUID = -1726436571535364126L;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Type(type = "string-array")
    @Column(name = "message", columnDefinition = "TEXT[]")
    private String[] message;
}
