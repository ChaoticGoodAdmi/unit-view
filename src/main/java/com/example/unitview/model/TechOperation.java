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

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OperationVariation getVariation() {
        return variation;
    }

    public void setVariation(OperationVariation variation) {
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
        if (active != that.active) return false;
        if (!techProcess.equals(that.techProcess)) return false;
        if (!type.equals(that.type)) return false;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + techProcess.hashCode();
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + (variation != null ? variation.hashCode() : 0);
        result = 31 * result + (localDept != null ? localDept.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

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
