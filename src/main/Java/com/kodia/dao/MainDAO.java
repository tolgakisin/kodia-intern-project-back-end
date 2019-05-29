package com.kodia.dao;

import com.kodia.model.Student;
import com.kodia.model.University;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class MainDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = LoggerFactory.getLogger(MainDAO.class);

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getCurrentSession().get(clazz, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        getCurrentSession().save(object);
        return true;
    }

    public boolean removeObject(Object object) {
        getCurrentSession().remove(object);
        return true;
    }

    // select query for university table
    public List<University> getUniversityList(){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<University> criteriaQuery = criteriaBuilder.createQuery(University.class);
        Root<University> root = criteriaQuery.from(University.class);

        criteriaQuery.select(root);

        Query<University> query = getCurrentSession().createQuery(criteriaQuery);
        List<University> universityList = null;
        try {
            universityList = query.getResultList();
        } catch (Exception e) {
            logger.error("University list is null");
        }
        return universityList;
    }

    // select query by university id for university table
    public University getUniversityById(int id){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<University> criteriaQuery = criteriaBuilder.createQuery(University.class);
        Root<University> root = criteriaQuery.from(University.class);

        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
        criteriaQuery.select(root).where(predicate);

        Query<University> query = getCurrentSession().createQuery(criteriaQuery);
        University university = null;
        try {
            university = query.getSingleResult();
        } catch (Exception e) {
            logger.error("University object is null");
        }
        return university;
    }

    // select query for student table
    public List<Student> getStudentList(){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        criteriaQuery.select(root);

        Query<Student> query = getCurrentSession().createQuery(criteriaQuery);
        List<Student> studentList = null;
        try {
            studentList = query.getResultList();
        } catch (Exception e) {
            logger.error("Student list is null");
        }
        return studentList;
    }

    // select query by student id for student table
    public Student getStudentById(int id){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
        criteriaQuery.select(root).where(predicate);

        Query<Student> query = getCurrentSession().createQuery(criteriaQuery);
        Student student = null;
        try {
            student = query.getSingleResult();
        } catch (Exception e) {
            logger.error("Student object is null");
        }
        return student;
    }
}
