package studentmanagement.StudentManagement;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Service;
import studentmanagement.StudentManagement.data.Student;
import studentmanagement.StudentManagement.service.StudentService;

@Service
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StudentManagementApplication.class);
	}

	private StudentRepository repository;

	@Autowired
	public void StudentService(StudentRepository repository){
		this.repository = repository;
	}

	public void registerStudent(Student student){
		repository.insertStudent(student);
	}
}

