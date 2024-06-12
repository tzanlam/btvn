package com.vti.Specification;

import com.vti.entity.Account;
import com.vti.form.Account.AccountFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    private static final String SEARCH_USERNAME = "username";
    private static final String SEARCH_FULLNAME = "fullname";
    private static final String MIN_ID = "minId";
    private static final String MAX_ID = "maxId";

    public static Specification<Account> buildWhere(AccountFilterForm form) {
        if (form == null) {
            return null;
        }
        SpecificationImpl whereUsername = new SpecificationImpl(SEARCH_USERNAME, form.getUserName());
        SpecificationImpl whereFullname = new SpecificationImpl(SEARCH_FULLNAME, form.getFullName());
        SpecificationImpl whereMinId = new SpecificationImpl(MIN_ID, form.getMinId());
        SpecificationImpl whereMaxId = new SpecificationImpl(MAX_ID, form.getMaxId());
        return Specification.where(whereUsername).and(whereFullname).and(whereMinId).and(whereMaxId);
    }
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
    public static class SpecificationImpl implements Specification<Account> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null)
                return null;

            switch (key) {
                case SEARCH_USERNAME:
                    return criteriaBuilder.like(root.get(SEARCH_USERNAME), "%" + value.toString() + "%");
                case SEARCH_FULLNAME:
                    return criteriaBuilder.like(root.get(SEARCH_FULLNAME), "%" +  value.toString() + "%");
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
