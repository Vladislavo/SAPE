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
	        assignment.setNif_student(rs.getString("nif_Student"));
	        assignment.setCreationDate(rs.getDate("creationDate"));
	        assignment.setId_projectoffer(rs.getLong("id_ProjectOffer"));
	        assignment.setMail_tutor(rs.getString("mail_tutor"));
	        assignment.setState(rs.getBoolean("state"));
	        assignment.setApprovalDate(rs.getDate("approvalDate"));
	        assignment.setRejectDate(rs.getDate("rejectDate"));

	        return assignment;
	    }
	}
	
	public List<Assignment> getAssignments(){
		return this.jdbcTemplate.query("select * from Assignment;" , new AssignmentMapper());
	}
	
	public Assignment getAssignment(String nif_student, Long id_projectoffer) {
		return this.jdbcTemplate.queryForObject("select * from Assignment where nif_Student = ? AND id_projectoffer = ?;",
				new Object[]{nif_student, id_projectoffer}, new AssignmentMapper());
		
	}
	public Assignment getAssignment(String nif_student) {
		try {
			return this.jdbcTemplate.queryForObject("select * from Assignment where nif_Student = ?;",
					new Object[]{nif_student}, new AssignmentMapper());
		} catch (Exception e) {
			return null;
		}
	}
	public List<Assignment> getAssignments(String nif_student){
		return this.jdbcTemplate.query("select * from Assignment where nif_Student = ?;", new Object[]{nif_student}, new AssignmentMapper());
	}
	public void addAssignment(Assignment assignment){
		this.jdbcTemplate.update(
				"insert into Assignment (nif_Student, creationDate, id_ProjectOffer, mail_Tutor, state, approvalDate, rejectDate) "
				+ " values (?,?,?,?,?,?,?);", 
				assignment.getNif_student(), new Date(), assignment.getId_projectoffer(),
				assignment.getMail_tutor(), assignment.getState(), assignment.getApprovalDate(), assignment.getRejectDate());
				
	}
	public void updateAssignment(Assignment assignment) {
		this.jdbcTemplate.update("update Assignment set id_projectoffer = ?, nif_student = ?, mail_tutor = ? where nif_student = ? AND id_projectoffer = ?;", 
				assignment.getId_projectoffer(), assignment.getNif_student(), assignment.getMail_tutor(), assignment.getNif_student(), 
				assignment.getId_projectoffer());
	}
	public void deleteAssignment(String nifStudent, Long id_projectoffer) {
		this.jdbcTemplate.update("delete from Assignment where nif_student = ? AND id_projectoffer = ?;", 
				nifStudent, id_projectoffer);
	}
	

}