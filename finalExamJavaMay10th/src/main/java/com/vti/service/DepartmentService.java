package com.vti.service;

import com.vti.entity.Department;

import java.util.List;

public interface DepartmentService {
    boolean isUsernameExist(String username);
    // read
    List<Department> getAllDepartments();
    Department getBySpecification();
    // create
}
