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
	        preference.setOrder(rs.getInt("preference_order"));
	        preference.setLastChangeDate(rs.getDate("lastChangeDate"));
	        preference.setStudent_nif(rs.getString("nif_Student"));
	        preference.setProjectOffer_id(rs.getLong("id_ProjectOffer"));
	        
	        return preference;
	    }
	}
	
	public List<Preference> getPreferences(){
		return this.jdbcTemplate.query("select * from Preference;" , new PreferenceDAOMapper());
	}
	
	public Preference getPreference(String nifStudent, long idProjectOffer) {
		return this.jdbcTemplate.queryForObject("select * from Preference where nif_Student = ? AND id_ProjectOffer = ?;",
				new Object[]{nifStudent, idProjectOffer}, new PreferenceDAOMapper());
		
	}
	public List<Preference> getPreference(String nifStudent) {
		return this.jdbcTemplate.query("select * from Preference where nif_Student = ? order by preference_order asc;",
				new Object[]{nifStudent}, new PreferenceDAOMapper());
		
	}
	public void addPreference(Preference preference){
		this.jdbcTemplate.update(
				"insert into Preference (nif_Student, id_ProjectOffer, order, lastChangeDate) "
				+ " values (?,?,?,?);", 
				preference.getStudent_nif(), preference.getProjectOffer_id(),
				preference.getOrder(), preference.getLastChangeDate());
				
	}
	public void updatePreference(Preference preference) {
		this.jdbcTemplate.update("updatePreference set (order = ?, lastChangeDate = ?"
				+ " where nif_Student = ? AND id_ProjectOffer = ?);", 
				preference.getOrder(), preference.getLastChangeDate(),
				preference.getStudent_nif(), preference.getProjectOffer_id());
	}			
	public void deletePreference(String nifStudent, long projectId) {
		this.jdbcTemplate.update("delete from Preference where nif_Student = ? AND id_Project = ?;", 
				nifStudent, projectId);
	}
	

}