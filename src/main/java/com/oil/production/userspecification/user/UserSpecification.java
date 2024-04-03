package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.Address;
import com.oil.production.userspecification.entities.Department;
import com.oil.production.userspecification.entities.Role;
import com.oil.production.userspecification.entities.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;

public class UserSpecification {

    public Specification<User> filter(UserRequest searchCriteria) {
        return Specification
                .where(userStateIsActive())
                .and(searchCriteria.getFirstName() == null ? null : firstNameContains(searchCriteria.getFirstName()))
                .and(searchCriteria.getLastName() == null ? null : lastNameContains(searchCriteria.getLastName()))
                .and(searchCriteria.getMiddleName() == null ? null : middleNameContains(searchCriteria.getMiddleName()))
                .and(searchCriteria.getUsername() == null ? null : usernameContains(searchCriteria.getUsername()))
                .and(searchCriteria.getDepartmentId() == null ? null : departmentIdEquals(searchCriteria.getDepartmentId()))
                .and(searchCriteria.getRoleNameUz() == null ? null : roleNameUzContains(searchCriteria.getRoleNameUz()))
                .and(searchCriteria.getRoleNameEn() == null ? null : roleNameEnContains(searchCriteria.getRoleNameEn()))
                .and(searchCriteria.getRoleNameRu() == null ? null : roleNameRuContains(searchCriteria.getRoleNameRu()))
                .and(searchCriteria.getAddress() == null ? null : addressContains(searchCriteria.getAddress()))
                .and(searchCriteria.getDistrictId() == null ? null : districtIdEquals(searchCriteria.getDistrictId()))
                .and(searchCriteria.getRegionId() == null ? null : regionIdEquals(searchCriteria.getRegionId()));
    }

    private static Specification<User> userStateIsActive() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userState"), User.State.ACTIVE));
    }

    private static Specification<User> firstNameContains(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"), contains(name)));
    }

    private static Specification<User> lastNameContains(String lastName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("lastName"), contains(lastName)));
    }

    private static Specification<User> middleNameContains(String middleName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("middleName"), contains(middleName)));
    }

    private static Specification<User> usernameContains(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), contains(username)));
    }

    private static Specification<User> departmentIdEquals(Long departmentId) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Department> join = root.join("department");
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Department> departmentRoot = subquery.from(Department.class);
            subquery.select(departmentRoot.get("id"))
                    .where(criteriaBuilder.or(
                            criteriaBuilder.equal(departmentRoot.get("id"), departmentId),
                            criteriaBuilder.equal(departmentRoot.get("parentDepartment").get("id"), departmentId)
                    ));
            return criteriaBuilder.in(join.get("id")).value(subquery);
        });
    }

    private static Specification<User> roleNameUzContains(String roleNameUz) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Role> join = root.join("roles");
            return criteriaBuilder.equal(criteriaBuilder.lower(join.get("nameUz")), roleNameUz.toLowerCase());
        });
    }

    private static Specification<User> roleNameEnContains(String roleNameEn) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Role> join = root.join("roles");
            return criteriaBuilder.equal(criteriaBuilder.lower(join.get("nameEn")), roleNameEn.toLowerCase());
        });
    }

    private static Specification<User> roleNameRuContains(String roleNameRu) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Role> join = root.join("roles");
            return criteriaBuilder.equal(criteriaBuilder.lower(join.get("nameRu")), roleNameRu.toLowerCase());
        });
    }

    private static Specification<User> addressContains(String address) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Role> join = root.join("address");
            return criteriaBuilder.like(criteriaBuilder.lower(join.get("address")), contains(address.toLowerCase()));
        });
    }

    private static Specification<User> districtIdEquals(Long districtId) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Address> join = root.join("address");
            return criteriaBuilder.equal(join.get("district").get("id"), districtId);
        });
    }

    private static Specification<User> regionIdEquals(Long regionId) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Address> join = root.join("address");
            return criteriaBuilder.equal(join.get("district").get("region").get("id"), regionId);
        });
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

}
