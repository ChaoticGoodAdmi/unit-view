package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "material")
/*@SecondaryTable(name = "measurement",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"*//*, referencedColumnName = "measurement_id"*//*))*/
@Data
@NoArgsConstructor
public class Material {

    @Id
    @Column(name = "id")
    @NonNull
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    @JoinColumn(name = "measurement_id")
    private MeasurementUnit measurementUnit;

}
