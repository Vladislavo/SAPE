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


import es.uji.ei102717bmr.sape.model.ProjectOffer;
@Repository
public class ProjectOfferDAO {
	
	private JdbcTemplate jdbcTemplate;
    
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class ProjectOfferMapper implements RowMapper<ProjectOffer> { 

	    public ProjectOffer mapRow(ResultSet rs, int rowNum) throws SQLException { 
	        ProjectOffer projectOffer= new ProjectOffer();
	        projectOffer.setId(rs.getLong("id"));
	        projectOffer.setTitle(rs.getString("title"));
	        projectOffer.setItinerary(rs.getString("itinerary"));
	        projectOffer.setTasks(rs.getString("tasks"));
	        projectOffer.setObjectives(rs.getString("objectives"));
	        projectOffer.setState(rs.getLong("state"));
	        projectOffer.setStartDate(rs.getDate("startDate"));
	        projectOffer.setLastChangeDate(rs.getDate("lastChangeDate"));
	        projectOffer.setId_internship(rs.getLong("id_internship"));
	        
	        return projectOffer;
	    }
	}
	
	public List<ProjectOffer> getProjectOffers(){
		return this.jdbcTemplate.query("select * from ProjectOffer;" , new ProjectOfferMapper());
	}
	
	public ProjectOffer getProjectOffer(long id) {
		return this.jdbcTemplate.queryForObject("select * from ProjectOffer where id = ?;",new Object[]{id}, new ProjectOfferMapper());
	}
	
	public ProjectOffer getProjectOfferInternship(long id) {
		return this.jdbcTemplate.queryForObject("select * from ProjectOffer where id_internship = ?;",new Object[]{id}, new ProjectOfferMapper());
	}
	
	public void addProjectOffer(ProjectOffer projectOffer){
		long lastValue = jdbcTemplate.queryForObject("SELECT last_value FROM internship_id_seq;", Long.class);
		this.jdbcTemplate.update("insert into projectOffer (title, id_Internship, startDate, lastChangeDate)"
						+ " values (?,?,?,?);", projectOffer.getTitle(), lastValue, new Date(), new Date());
	}
	public void updateProjectOffer(ProjectOffer projectOffer) {
<<<<<<< HEAD
		this.jdbcTemplate.update("update ProjectOffer set id_internship = ?, title = ?, tasks = ?,itinerary = ?, objectives = ?,"
=======
		this.jdbcTemplate.update("update ProjectOffer set id_Internship = ?, title = ?, tasks = ?,itinerary = ?, objectives = ?,"
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
				+ " state = ?, startDate = ?, lastChangeDate = ? where id = ?;",
				projectOffer.getId_internship(),
				projectOffer.getTitle(), projectOffer.getTasks(),
				projectOffer.getItinerary(), projectOffer.getObjectives(),
				projectOffer.getState(), projectOffer.getStartDate(),
				new Date(), projectOffer.getId());
	}			
	
	public void updateProjectOfferInternship(ProjectOffer projectOffer) {
		this.jdbcTemplate.update("update ProjectOffer set title = ?, tasks = ?, itinerary = ?, objectives = ?,"
				+ " state = ?, lastChangeDate = ? where id_internship = ?;",
				projectOffer.getTitle(), projectOffer.getTasks(), 
				projectOffer.getItinerary(), projectOffer.getObjectives(),
				projectOffer.getState(), new Date(), projectOffer.getId_internship());
	}
	
	public void deleteProjectOffer(long id) {
		this.jdbcTemplate.update("delete from ProjectOffer where id = ?;", id);
	}
	

}