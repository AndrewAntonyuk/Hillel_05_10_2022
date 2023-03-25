package com.aveshnick.dao;

import com.aveshnick.configs.HibernateSession;
import com.aveshnick.entities.Student;
import com.aveshnick.exceptions.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentDao {
    private final Session session = HibernateSession.getSessionFactory().openSession();

    public void save(Student student) {
        session.save(student);
    }

    public void deleteByID(Long id) {
        Transaction transaction = session.beginTransaction();
        Student student = getCheckedStudent(id);

        session.delete(student);
        transaction.commit();
    }

    public void update(Student student) {
        Transaction transaction = session.beginTransaction();

        session.merge(student);
        transaction.commit();
    }

    public List<Student> getAllStudents() {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        CriteriaQuery<Student> allStudents = criteriaQuery.select(root);

        TypedQuery<Student> allQuery = session.createQuery(allStudents);

        return allQuery.getResultList();
    }

    public Student getByID(Long id) {
        return getCheckedStudent(id);
    }

    private Student getCheckedStudent(Long id) {
        Student student = session.get(Student.class, id);

        if (student == null) {
            throw new UserNotFoundException("User with ID " + id + " doesn't exist!");
        }

        return student;
    }
}
