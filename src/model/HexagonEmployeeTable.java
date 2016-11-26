package model;

/**
 * Created by hexagon13 on 9/7/2016.
 */


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HexagonEmployeeTable")
public class HexagonEmployeeTable implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "EMPLOYEE_NUMBER")
    private long empno;

    @Column(name = "EMPLOYEE_NAME")
    private String ename;

    @Column(name = "EMPLOYEE_SALARY")
    private int sal;

    @Column(name = "EMPLOYEE_JOB")
    private String job;

    @Column(name = "EMPLOYEE_DEPARTMENT_NUMBER")
    private int deptno;

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(long empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

}
