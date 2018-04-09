package es.uji.ei102717bmr.sape.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import es.uji.ei102717bmr.sape.model.Preference;
import es.uji.ei102717bmr.sape.model.Student;
import es.uji.ei102717bmr.sape.model.ProjectOffer;

@Repository
public class PreferenceDAO {
	
	private JdbcTemplate jdbcTemplate;
    
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class PreferenceDAOMapper implements RowMapper<Preference> { 

	    public Preference mapRow(ResultSet rs, int rowNum) throws SQLException { 
	        Preference preference= new Preference();
	        preference.setOrder(rs.getInt("order"));
	        preference.setState(rs.getString("state"));
	        preference.setLastChangeDate(rs.getDate("lastChangeDate"));
	        //preference.setStudentNIF();
	        //preference.setProjectId();
	        
	        return preference;
	    }
	}
	
	public List<Preference> getPreferences(){
		return this.jdbcTemplate.query("select * from Preference;" , new PreferenceDAOMapper());
	}
	
	public Preference getPreference(long nifStudent, long idProjectOffer) {
		return this.jdbcTemplate.queryForObject("select * from Preference where nif_Student = ? AND id_ProjectOffer = ?;",
				new Object[]{nifStudent, idProjectOffer}, new PreferenceDAOMapper());
		
	}
	public void addPreference(Preference preference){
		this.jdbcTemplate.update(
				"insert into Preference (nif_Student, id_ProjectOffer, order, state, lastChangeDate) "
				+ " values (?,?,?,?,?);", 
				preference.getStudent().getNIF(), preference.getProectOffer().getId(),
				preference.getOrder(), preference.getState(), preference.getLastChangeDate());
				
	}
	public void updatePreference(Preference preference) {
		this.jdbcTemplate.update("updatePreference set (order = ?, state= ?, lastChangeDate = ?"
				+ " where nif_Student = ? AND id_ProjectOffer = ?);", 
				preference.getOrder(), preference.getState(), preference.getLastChangeDate(),
				preference.getStudent().getNIF(), preference.getProectOffer().getId());
	}			
	public void deletePreference(String nifStudent, long projectId) {
		this.jdbcTemplate.update("delete from Preference where nif_Student = ? AND id_Project = ?;", 
				nifStudent, projectId);
	}
	

}