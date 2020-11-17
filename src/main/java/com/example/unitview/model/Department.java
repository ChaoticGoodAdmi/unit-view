package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
public class Department {

    @Id
    private int id;

    @Column(name = "date_org")
    private LocalDate creationDate;

    @Column(name = "date_liq")
    private LocalDate liquidationDate;

    @Column(name = "code")
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", liquidationDate=" + liquidationDate +
                ", code='" + code + '\'' +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
