package studentmanagement.StudentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import studentmanagement.StudentManagement.data.Student;
import studentmanagement.StudentManagement.data.Students_Courses;

/*
受講生情報を取り扱うリポジトリ

全体検索や単一条件で検索　生徒情報の検索が行える
 */

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students WHERE isDeleted = false")
  @Results({
      @Result(property = "deleted",column = "isDeleted"),
      @Result(property = "kanaName",column = "kana_name")
  })
  List<Student> search();

//  @Select("SELECT * FROM students_courses ")
  @Select("SELECT sc. * FROM students_courses sc JOIN students s ON sc.student_id = s.id WHERE s.isDeleted = false")

  List<Students_Courses> searchStudentCourses();


  @Select("SELECT MAX(CAST(id AS UNSIGNED)) FROM students")
  Integer findMaxId();

  @Insert
      ("INSERT INTO students(id,name,kana_name,nickname,email,area,age,sex,remark,isDeleted)"
          + "VALUES(#{id},#{name},#{kanaName},#{nickname},#{email},#{area},#{age},#{sex},#{remark},#{deleted})")


 // @Options(useGeneratedKeys = true,keyProperty = "id")



  void insertStudent(Student student);

  @Insert()
  @Options(useGeneratedKeys = true,keyProperty = "id")
  void registerStudentsCourses(Students_Courses studentsCourses);


}

