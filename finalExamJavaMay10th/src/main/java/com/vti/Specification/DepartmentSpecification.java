package com.vti.Specification;

import com.vti.entity.Department;
import com.vti.form.Department.DepartmentFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecification {
    public static final String SEARCH_NAME = "name";
    public static final String MIN_ID = "minId";
    public static final String MAX_ID = "maxId";
    public static Specification<Department> buildWhere(DepartmentFilterForm form) {
        if (form == null) {
            return null;
        }
        SpecificationImpl whereDepartmentName = new SpecificationImpl(SEARCH_NAME, form.getName());
        SpecificationImpl whereMinId = new SpecificationImpl(MIN_ID, form.getMinId());
        SpecificationImpl whereMaxId = new SpecificationImpl(MAX_ID, form.getMaxId());
        return whereDepartmentName.and(whereMinId).and(whereMaxId);
    }


        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SpecificationImpl implements Specification<Department>{
            private String key;
            private Object value;


            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (value == null) {
                    return null;
                }
                switch (key) {
                    case SEARCH_NAME:
                        return criteriaBuilder.like(root.get(SEARCH_NAME), "%" + value.toString() + "%");
                    case MIN_ID:
                        return criteriaBuilder.greaterThanOrEqualTo(root.get(MIN_ID),(Integer) value);
                    case MAX_ID:
                        return criteriaBuilder.lessThanOrEqualTo(root.get(MAX_ID),(Integer) value);
                    default:
                        return null;
                }
            }
        }
    }

