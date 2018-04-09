package es.uji.ei102717bmr.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102717bmr.sape.model.Tutor;

@Repository
public class TutorDAO {
	private JdbcTemplate jdbcTemplate;
		
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class TutorMapper implements RowMapper<Tutor> { 

	    public Tutor mapRow(ResultSet rs, int rowNum) throws SQLException { 
	    	Tutor tutor = new Tutor();
	    	tutor.setEmail(rs.getString("mail"));
	    	tutor.setName(rs.getString("name"));
	    	tutor.setTelephone(rs.getString("telephone"));
	    	tutor.setOffice(rs.getString("office"));
	    	
	    	
	        return tutor;
	    }
	}
	
	public List<Tutor> getTutors(){
		return this.jdbcTemplate.query("select * from Tutor;", new TutorMapper());
	}
	
	public Tutor getTutor(String mail){
		return this.jdbcTemplate.queryForObject("select * from Tutor where mail = ?;", 
				new Object[]{mail}, new TutorMapper());
	}
	
	public void addTutor(Tutor tutor){
		this.jdbcTemplate.update("insert into Tutor (mail, name, telephone,"
				+ "office) values (?,?,?,?);", tutor.getEmail(), tutor.getName(),
				tutor.getTelephone(), tutor.getOffice());
	}
	
	public void updateTutor(Tutor tutor){
		this.jdbcTemplate.update("update Tutor set name = ?, "
				+ "telephone = ?, office = ? where mail = ?;",  tutor.getName(),
				tutor.getTelephone(), tutor.getOffice(), tutor.getEmail());
	}
	
	public void deleteTutor(String mail){
		this.jdbcTemplate.update("delete from Tutor where mail=?", mail);
	}
}
