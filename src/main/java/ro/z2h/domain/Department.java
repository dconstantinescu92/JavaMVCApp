package ro.z2h.domain;

/**
 * Department.java
 */
public class Department {

    private Long id;
    private String departmentName;

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
