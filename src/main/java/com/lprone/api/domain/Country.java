package com.lprone.api.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "country")
public class Country {

    @Id
    private String name;
    private int population;
}
