package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.data.db.jpa.entities.AssignmentData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CriteriaAssignmentRepository {
    private final EntityManager em;
    private final CriteriaBuilder cb;

    public CriteriaAssignmentRepository(EntityManager em) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
    }

    public List<AssignmentData> findAllWithFilter(
            Identity id,
            String title,
            Identity teacherId,
            Identity subjectId) {
        CriteriaQuery<AssignmentData> criteriaQuery = cb.createQuery(AssignmentData.class);
        Root<AssignmentData> assignmentDataRoot = criteriaQuery.from(AssignmentData.class);

        Predicate predicate = getPredicate(
                id,
                title,
                teacherId,
                subjectId,
                assignmentDataRoot
        );

        criteriaQuery.where(predicate);
        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(
                assignmentDataRoot.get("updatedAt")
        ));
        criteriaQuery.orderBy(orders);
        TypedQuery<AssignmentData> assignmentQuery = em.createQuery(criteriaQuery);
        return assignmentQuery.getResultList();
    }

    private Predicate getPredicate(
            Identity id,
            String title,
            Identity teacherId,
            Identity subjectId,
            Root<AssignmentData> assignmentDataRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(id)) {
            predicates.add(cb.equal(assignmentDataRoot.get("id"), id.getId()));
        }

        if (Objects.nonNull(title)) {
            predicates.add(cb.like(assignmentDataRoot.get("title"), "%" + title + "%"));
        }

        if (Objects.nonNull(teacherId)) {
            predicates.add(cb.equal(assignmentDataRoot.get("teacherId"), teacherId.getId()));
        }

        if (Objects.nonNull(subjectId)) {
            predicates.add(cb.equal(assignmentDataRoot.get("subjectId"), subjectId.getId()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
