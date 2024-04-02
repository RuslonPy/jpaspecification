package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.Address;
import com.oil.production.userspecification.entities.Department;
import com.oil.production.userspecification.entities.Role;
import com.oil.production.userspecification.entities.User;
import jakarta.persistence.criteria.Join;
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
                .and(searchCriteria.getDepartment() == null ? null : departmentContains(searchCriteria.getDepartment()))
                .and(searchCriteria.getRole() == null ? null : roleContains(searchCriteria.getRole()))
                .and(searchCriteria.getAddress() == null ? null : addressEquals(searchCriteria.getAddress()));
    }

    public static Specification<User> userStateIsActive() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userState"), User.State.ACTIVE));
    }

    public static Specification<User> firstNameContains(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), contains(name)));
    }

    public static Specification<User> lastNameContains(String lastName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), contains(lastName)));
    }

    public static Specification<User> middleNameContains(String middleName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), contains(middleName)));
    }

    public static Specification<User> usernameContains(String username) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), contains(username)));
    }

    private static Specification<User> departmentContains(Department department) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Department> join = root.join("department");
            return criteriaBuilder.equal(join.get("id"), department.getId());
        });
    }

    private static Specification<User> roleContains(Role role) {
        return ((root, query, criteriaBuilder) -> {
            Join<User, Role> join = root.join("role");
            return criteriaBuilder.equal(join.get("id"), role.getName());
        });
    }

    private static Specification<User> addressEquals(Address address) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), contains(address.getAddress().toLowerCase())),
                        criteriaBuilder.equal(root.get("district"), contains(address.getDistrict().toString())),
                        criteriaBuilder.equal(root.get("region"), contains(address.getDistrict().getRegion().toString()))
                )
        );
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }

}
