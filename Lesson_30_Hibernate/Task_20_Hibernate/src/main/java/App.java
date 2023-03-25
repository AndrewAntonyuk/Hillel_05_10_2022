import com.aveshnick.dao.StudentDao;
import com.aveshnick.entities.Student;
import com.aveshnick.configs.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

//entities
public class App {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student student;

        System.out.println(System.lineSeparator() + "------------Add new student--------------");
//        studentDao.save(new Student("Alli", "king@jbs.com"));

        System.out.println();
        studentDao.getAllStudents().forEach(System.out::println);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

        System.out.println(System.lineSeparator() + "------------Delete one student--------------");
        System.out.println("Students before delete: " + System.lineSeparator());
        studentDao.getAllStudents().forEach(System.out::println);

//        studentDao.deleteByID(8L);

        System.out.println("Students after delete: " + System.lineSeparator());
        studentDao.getAllStudents().forEach(System.out::println);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

        System.out.println(System.lineSeparator() + "------------Update one student--------------");
        student = studentDao.getByID(2L);
        System.out.println("Student with id = 2 before update: " + student);

        student.setName("NewName");
        student.setEmail("somemail@test.com");

        studentDao.update(student);

        System.out.println(System.lineSeparator() +
                "Student with id = 2 after update: " + studentDao.getByID(2L));
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

        System.out.println(System.lineSeparator() + "------------Get all exist students--------------");
        studentDao.getAllStudents().forEach(System.out::println);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());

        System.out.println(System.lineSeparator() + "------------Get one student--------------");
        System.out.println(System.lineSeparator() + "Student with ID 4: " + studentDao.getByID(4L));
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + System.lineSeparator());
    }
}
