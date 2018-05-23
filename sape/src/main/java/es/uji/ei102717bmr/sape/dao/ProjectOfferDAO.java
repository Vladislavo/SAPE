package es.uji.ei102717bmr.sape.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
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
	        projectOffer.setId(rs.getInt("id"));
	        projectOffer.setTitle(rs.getString("title"));
	        projectOffer.setItinerary(rs.getString("itinerary"));
	        projectOffer.setTasks(rs.getString("tasks"));
	        projectOffer.setObjectives(rs.getString("objectives"));
	        projectOffer.setState(rs.getString("state"));
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
	public void addProjectOffer(ProjectOffer projectOffer){
		this.jdbcTemplate.update(
				"insert into ProjectOffer (id, title, itinerary, tasks, objectives, state, "
				+ "startDate, lastChangeDate) values (?,?,?,?,?,?,?);", 
				projectOffer.getId(), projectOffer.getTitle(), projectOffer.getItinerary(),
				projectOffer.getTasks(), projectOffer.getObjectives(),
				projectOffer.getState(), projectOffer.getStartDate(),
				projectOffer.getLastChangeDate());
	}
	public void updateProjectOffer(ProjectOffer projectOffer) {
		this.jdbcTemplate.update("update ProjectOffer set (title = ?, tasks = ?,itinerary = ?, objectives = ?,"
				+ " state = ?, startDate = ?, lastChangeDate = ? where id = ?);", 
				projectOffer.getTitle(), projectOffer.getItinerary(),
				projectOffer.getTasks(), projectOffer.getObjectives(),
				projectOffer.getState(), projectOffer.getStartDate(),
				projectOffer.getLastChangeDate(), projectOffer.getId());
	}			
	public void deleteProjectOffer(long id) {
		this.jdbcTemplate.update("delete from ProjectOffer where id = ?;", id);
	}
	

}