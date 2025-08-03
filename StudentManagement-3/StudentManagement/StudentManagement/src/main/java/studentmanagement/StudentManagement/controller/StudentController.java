package studentmanagement.StudentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studentmanagement.StudentManagement.controller.converter.StudentConverter;
import studentmanagement.StudentManagement.data.Student;
import studentmanagement.StudentManagement.data.Students_Courses;
import studentmanagement.StudentManagement.domain.StudentDetail;
import studentmanagement.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter){
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentlist(){
    List<Student> students = service.searchStudentList();
    List<Students_Courses> studentsCourses = service.searchStudentsCoursesList();

    return converter.convertStudentDetails(students, studentsCourses);

  }

  @GetMapping("/studentsCourseList")
  public List<Students_Courses> getStudents_CourseList(){
    return service.searchStudentsCoursesList();
  }




}
