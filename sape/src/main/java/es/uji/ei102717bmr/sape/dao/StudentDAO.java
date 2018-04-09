package es.uji.ei102717bmr.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102717bmr.sape.model.Student;

@Repository
public class StudentDAO {
	private JdbcTemplate jdbcTemplate;
		
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class StudentMapper implements RowMapper<Student> { 

	    public Student mapRow(ResultSet rs, int rowNum) throws SQLException { 
	    	Student student = new Student();
	    	student.setNIF(rs.getString("nif"));
	    	student.setEmail(rs.getString("mail"));
	    	student.setName(rs.getString("name"));
	    	student.setTelephone(rs.getString("telephone"));
	    	student.setGradeCertificate(rs.getString("gradeCertificate"));
	    	student.setItinerary(rs.getString("itinerary"));
	        
	        return student;
	    }
	}
	
	public List<Student> getStudents(){
		return this.jdbcTemplate.query("select * from Student;", new StudentMapper());
	}
	
	public Student getStudent(String nif){
		return this.jdbcTemplate.queryForObject("select * from Student where nif = ?;", 
				new Object[]{nif}, new StudentMapper());
	}
	
	public void addStudent(Student student){
		this.jdbcTemplate.update("insert into Student (nif, mail, name, telephone, gradeCertificate, itinerary) "
				+ "values (?,?,?,?,?,?);", student.getNIF(), student.getEmail(),
				student.getName(), student.getTelephone(), student.getGradeCertificate(), student.getGradeCertificate());
	}
	
	public void updateStudent(Student student){
		this.jdbcTemplate.update("update Student set mail = ?, name = ?"
				+ "telephone = ?, gradeCertificate = ?, itinerary = ? where nif = ?;", student.getEmail(), student.getName(),
				student.getTelephone(), student.getGradeCertificate(), student.getItinerary(), student.getNIF());
	}
	
	public void deleteStudent(String nif){
		this.jdbcTemplate.update("delete from Student where nif = ?", nif);
	}
}
