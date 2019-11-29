package com.project_sem4.fe.specification;

import com.project_sem4.fe.entity.Category;
import com.project_sem4.fe.entity.Story;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CategorySpecification implements Specification<Category> {
    private SearchCriteria criteria;

    public CategorySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("!=")) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("join")) {
            Join<Category, Story> categoryStoryJoin = root.join("stories");

            Predicate predicate = builder.or(
                    builder.like(root.get("name"), "%" + criteria.getValue() + "%"),
                    builder.like(categoryStoryJoin.get("title"), "%" + criteria.getValue() + "%")
//                    builder.like(root.get("description"), "%" + criteria.getValue() + "%"),
//                    builder.like(categoryStoryJoin.get("title"), "%" + criteria.getValue() + "%"),
//                    builder.like(categoryStoryJoin.get("description"), "%" + criteria.getValue() + "%")
            );
            return predicate;
        }
        return null;
    }
}
