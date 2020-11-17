package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tech_proc_oper")
@Data
@NoArgsConstructor
public class TechOperation {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "tp_id")
    private TechProcess techProcess;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "oper_id")
    private OperationType type;

    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "nvar")
    private OperationVariation variation;

    @Column(name = "local_dept")
    private String localDept;

    @Column(name = "active")
    private boolean active;

    @Override
    public String toString() {
        return "TechOperation{" +
                "id=" + id +
                ", techProcess=" + techProcess +
                ", department=" + department +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", variation=" + variation +
                ", localDept='" + localDept + '\'' +
                ", active=" + active +
                '}';
    }
}
