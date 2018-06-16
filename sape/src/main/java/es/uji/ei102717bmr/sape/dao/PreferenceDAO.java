package es.uji.ei102717bmr.sape.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

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
<<<<<<< HEAD
	        preference.setPreferenceOrder(rs.getInt("preference_order"));
	        preference.setStudent_nif(rs.getString("nif_student"));
	        preference.setProjectOffer_id(rs.getInt("id_projectOffer"));
	        preference.setLastChangeDate(rs.getDate("lastChangeDate"));

=======
	        preference.setOrder(rs.getInt("preference_order"));
	        preference.setLastChangeDate(rs.getDate("lastChangeDate"));
	        preference.setStudent_nif(rs.getString("nif_Student"));
	        preference.setProjectOffer_id(rs.getLong("id_ProjectOffer"));
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
	        
	        return preference;
	    }
	}
	
	public List<Preference> getPreferences(){
		return this.jdbcTemplate.query("select * from preference;" , new PreferenceDAOMapper());
	}
	
	public List<Preference> getPreference(String nif_student) {
		return this.jdbcTemplate.query("select * from Preference where nif_Student = ? order by preference_order;",
				new Object[]{nif_student}, new PreferenceDAOMapper());
	}
<<<<<<< HEAD
		

	public void addPreference(Preference preference){
		this.jdbcTemplate.update(
				"insert into Preference (nif_student, id_ProjectOffer, preference_order, lastChangeDate) "
				+ " values (?,?,?,?);", 
				preference.getStudent_nif(), preference.getProjectOffer_id(),
				preference.getPreferenceOrder(), preference.getLastChangeDate());
				
	}
	public void updatePreference(Preference preference) {
		this.jdbcTemplate.update("update Preference set (preference_order = ?, lastChangeDate = ?"
				+ " where nif_Student = ? AND id_ProjectOffer = ?);", 
				preference.getPreferenceOrder(), preference.getLastChangeDate(),
=======
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
>>>>>>> ccd335acc63bebcfcccc5c93e4572eec0750f8f9
				preference.getStudent_nif(), preference.getProjectOffer_id());
	}			
	public void deletePreference(String nif_student, int preference_order) {
		this.jdbcTemplate.update("delete from Preference where nif_Student = ? AND preference_order = ?;", 
				nif_student, preference_order);
	}
	public void deletePreferenceProject(String nif_student, long id_projectOffer) {
		this.jdbcTemplate.update("delete from Preference where nif_Student = ? AND id_ProjectOffer = ?;", 
				nif_student, id_projectOffer);
	}
	
}