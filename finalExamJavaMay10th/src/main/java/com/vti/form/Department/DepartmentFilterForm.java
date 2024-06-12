package com.vti.form.Department;

import lombok.Data;

@Data
public class DepartmentFilterForm {
    private String name;
    private Integer maxId;
    private Integer minId;
}
