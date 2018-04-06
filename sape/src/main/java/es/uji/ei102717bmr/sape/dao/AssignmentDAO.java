package es.uji.ei102717bmr.sape.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import es.uji.ei102717bmr.sape.model.Assignment;
import es.uji.ei102717bmr.sape.model.Student;
import es.uji.ei102717bmr.sape.model.ProjectOffer;

@Repository
public class AssignmentDAO {
	
	private JdbcTemplate jdbcTemplate;
    
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class AssignmentMapper implements RowMapper<Assignment> { 

	    public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException { 
	        Assignment assignment = new Assignment();
	        //assignment.setStudentNIF(rs.getString("nif_Student"));
	        assignment.setCreationDate(rs.getDate("creationDate"));
	        //assignment.setProjectOfferId(rs.getString("id_ProjectOffer"));
	        //assignment.setTutorMail(rs.getString("mail_tutor"));
	        assignment.setState(rs.getString("state"));
	        assignment.setApprovalDate(rs.getDate("approvalDate"));
	        assignment.setRejectDate(rs.getDate("rejectDate"));

	        return assignment;
	    }
	}
	
	public List<Assignment> getAssignments(){
		return this.jdbcTemplate.query("select * from Assignment;" , new AssignmentMapper());
	}
	
	public Assignment getAssignment(long nifStudent, Date creationDate) {
		return this.jdbcTemplate.queryForObject("select * from Assignment where nif_Student = ? AND creationDate = ?;",
				new Object[]{nifStudent, creationDate}, new AssignmentMapper());
		
	}
	public void addAssignment(Assignment assignment){
		this.jdbcTemplate.update(
				"insert into Assignment (nif_Student, creationDate, id_ProjectOffer, mail_Tutor, state, approvalDate, rejectDate) "
				+ " values (?,?,?,?,?,?,?);", 
				assignment.getStudent().getNIF(), assignment.getCreationDate(), assignment.getProjectOffer().getId(),
				assignment.getTutor().getEmail(), assignment.getState(), assignment.getApprovalDate(), assignment.getRejectDate());
				
	}
	public void updateAssignment(Assignment assignment) {
		this.jdbcTemplate.update("update Assignment set (id_ProjectOffer = ?, mail_Tutor= ?, state = ?, approvalDate = ?",
				+ ", rejectDate = ? where nif_Student = ? AND creationDate = ?);", 
				assignment.getProjectOffer().getId(), assignment.getTutor().getEmail(), assignment.getState(), 
				assignment.getApprovalDate(), assignment.getRejectDate(), assignment.getStudent().getNIF(), 
				assignment.getCreationDate());
	}			
	public void deleteAssignment(Assignment assignment) {
		this.jdbcTemplate.update("delete from Assignment where nif_Student = ? AND creationDate = ?;", 
				assigment.getStudent().getNIF(), assignment.getCreationDate());
	}
	

}