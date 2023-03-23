import com.company.entitys.Student;
import com.company.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            List<Student> students;
            Student student;

            System.out.println(System.lineSeparator() + "------------Get all exist students--------------");
            students = session.createQuery("FROM Student", Student.class).getResultList();
            System.out.println();
            students.forEach(System.out::println);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Get one student--------------");
            student = session.get(Student.class, 4L);
            System.out.println(System.lineSeparator() + student);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Add new student--------------");
            // session.save(new Student("Alli", "king@jbs.com"));

            students = session.createQuery("FROM Student", Student.class).getResultList();
            System.out.println();
            students.forEach(System.out::println);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Update one student--------------");
            student = session.get(Student.class, 6L);
            System.out.println("Student with id = 6 before update: " + student);

            Transaction transactionUpdate = session.beginTransaction();
            student.setName("NewName");
            student.setEmail("somemail@test.com");

            session.update(student);
            transactionUpdate.commit();

            student = session.get(Student.class, 6L);
            System.out.println(System.lineSeparator() +
                    "Student with id = 6 after update: " +
                    student);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            System.out.println(System.lineSeparator() + "------------Delete one student--------------");
            student = session.get(Student.class, 3L);
            System.out.println("Student with id = 3 before delete: " + student);

            Transaction transactionDelete = session.beginTransaction();
            session.delete(student);
            transactionDelete.commit();

            System.out.println("Students after delete: ");
            students = session.createQuery("FROM Student", Student.class).getResultList();
            System.out.println();
            students.forEach(System.out::println);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

            session.close();
        }
    }
}
