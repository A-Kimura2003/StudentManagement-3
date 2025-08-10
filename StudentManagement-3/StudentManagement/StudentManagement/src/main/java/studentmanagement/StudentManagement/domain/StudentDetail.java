package studentmanagement.StudentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import studentmanagement.StudentManagement.data.Student;
import studentmanagement.StudentManagement.data.Students_Courses;

@Getter
@Setter

public class StudentDetail {

  private Student student;
  private List<Students_Courses> studentsCourses;


}
