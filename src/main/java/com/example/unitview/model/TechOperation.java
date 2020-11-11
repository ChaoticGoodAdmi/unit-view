package com.example.unitview.model;

import javax.persistence.*;

@Entity
@Table(name = "tech_proc_oper")
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

    @Column(name = "oper_id")
    private int operId;

    @Column(name = "nvar")
    private int variation;

    @Column(name = "local_dept")
    private String localDept;

    @Column(name = "active")
    private boolean active;

    public TechOperation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TechProcess getTechProcess() {
        return techProcess;
    }

    public void setTechProcess(TechProcess techProcess) {
        this.techProcess = techProcess;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getOperId() {
        return operId;
    }

    public void setOperId(int operId) {
        this.operId = operId;
    }

    public int getVariation() {
        return variation;
    }

    public void setVariation(int variation) {
        this.variation = variation;
    }

    public String getLocalDept() {
        return localDept;
    }

    public void setLocalDept(String localDept) {
        this.localDept = localDept;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TechOperation that = (TechOperation) o;

        if (id != that.id) return false;
        if (operId != that.operId) return false;
        if (variation != that.variation) return false;
        if (active != that.active) return false;
        return localDept.equals(that.localDept);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + operId;
        result = 31 * result + variation;
        result = 31 * result + localDept.hashCode();
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechOperation{" +
                "id=" + id +
                ", operId=" + operId +
                ", variation=" + variation +
                ", localDept='" + localDept + '\'' +
                ", active=" + active +
                '}';
    }
}
