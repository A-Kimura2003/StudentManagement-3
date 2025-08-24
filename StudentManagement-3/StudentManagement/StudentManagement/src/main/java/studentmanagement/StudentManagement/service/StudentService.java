package studentmanagement.StudentManagement.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentmanagement.StudentManagement.StudentRepository;
import studentmanagement.StudentManagement.Students_CoursesRepository;
import studentmanagement.StudentManagement.data.Student;
import studentmanagement.StudentManagement.data.Students_Courses;

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
    repository.search();

    //絞り込みをする。年齢が３０代の人のみを抽出する。
    //抽出したリストをコントローラーにかえす。


    return repository.search();
  }


  public  List<Students_Courses> searchStudentsCoursesList(){
    //絞り込み検索で「」のコースのみ抽出する。
    //抽出したリストをコントローラーにかえす。
    return repository.searchStudentCourses();

  }



  public int getNextStudentId(){
    Integer maxId = repository.findMaxId();
    if(maxId == null){
      return 1;
    }
    return maxId + 1;
  }

  public void registerStudent(Student student){
    int nextId = getNextStudentId();
    student.setId(String.valueOf(nextId));
    repository.insertStudent(student);
  }
}
