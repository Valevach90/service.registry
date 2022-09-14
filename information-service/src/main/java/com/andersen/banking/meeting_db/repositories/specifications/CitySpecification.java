package com.andersen.banking.meeting_db.repositories.specifications;

import com.andersen.banking.meeting_db.entities.City;
import org.springframework.data.jpa.domain.Specification;

public class CitySpecification {

    private CitySpecification() {}

    public static Specification<City> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<City> hasCountry(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), id);
    }
}
