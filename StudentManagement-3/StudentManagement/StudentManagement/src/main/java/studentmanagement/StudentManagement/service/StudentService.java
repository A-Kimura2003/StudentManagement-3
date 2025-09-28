package studentmanagement.StudentManagement.service;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studentmanagement.StudentManagement.StudentRepository;
import studentmanagement.StudentManagement.Students_CoursesRepository;
import studentmanagement.StudentManagement.data.Student;
import studentmanagement.StudentManagement.data.Students_Courses;
import studentmanagement.StudentManagement.domain.StudentDetail;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  private StudentRepository studentrepository;
  public StudentService(StudentRepository repository) {

    this.repository = repository;
  }


  public List<Student> searchStudentList(){
    //検索処理
  //  repository.search();

    //絞り込みをする。年齢が３０代の人のみを抽出する。
    //抽出したリストをコントローラーにかえす。


    return repository.search();
  }


  public  List<Students_Courses> searchStudentsCoursesList(){
    //絞り込み検索で「」のコースのみ抽出する。
    //抽出したリストをコントローラーにかえす。
    return repository.searchStudentCourses();

  }



 // public int getNextStudentId(){
  //  Integer maxId = repository.findMaxId();
  //  if(maxId == null){
  //    return 1;
  //  }
  //  return maxId + 1;
  //}

 // public void registerStudent(Student student){
 //   int nextId = getNextStudentId();
 //   student.setId(String.valueOf(nextId));
//    repository.insertStudent(student);
 // }

  public List<Students_Courses> getCoursesByStudentId(String studentId){
    return repository.findCoursesByStudentId(studentId);
  }

@Transactional
  public void registerStudent(StudentDetail studentDetail){
    repository.registerStudent(studentDetail.getStudent());
    studentDetail.getStudent().getId();
    //TODO:コース情報登録も行う。
    for(Students_Courses studentsCourses:studentDetail.getStudentsCourses()) {
      studentsCourses.setStudentId(studentDetail.getStudent().getId());
      studentsCourses.setCourseStartAt(LocalDateTime.now());
      studentsCourses.setCourseEndAt(LocalDateTime.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourses);
    }
  }
}
