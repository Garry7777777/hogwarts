package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;
import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAge(int age);
    Collection<Student> findByAgeBetween(int min, int max);



    @Query(value ="SELECT COUNT(s) FROM student AS s" , nativeQuery = true)
    Long countAll();

    @Query(value ="SELECT AVG(s.age) FROM student AS s " , nativeQuery = true)
    Long countMidlife();

    @Query(value ="SELECT *  FROM student as s ORDER BY age ASC LIMIT 5" , nativeQuery = true)
    Collection<Student> findYoungest();
}
