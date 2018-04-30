package es.uji.ei102717bmr.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102717bmr.sape.model.Internship;

@Repository
public class InternshipDAO {
	private JdbcTemplate jdbcTemplate;
		
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class InternshipMapper implements RowMapper<Internship> { 

	    public Internship mapRow(ResultSet rs, int rowNum) throws SQLException { 
	    	Internship internship = new Internship();
	    	internship.setMailContactPerson(rs.getString("mailContactPerson"));
	    	internship.setDescription(rs.getString("description"));
	    	internship.setRenumeration(rs.getInt("renumeration"));
	        //internship.setProjectOffer();
	    	//internship.setCompany();
	        return internship;
	    }
	}
	
	public List<Internship> getInternships(){
		return this.jdbcTemplate.query("select * from Internship;", new InternshipMapper());
	}
	
	public Internship getInternship(long id){
		return this.jdbcTemplate.queryForObject("select * from Internship where id=?;", 
				new Object[]{id}, new InternshipMapper());
	}
	
	public void addInternship(Internship internship){
		this.jdbcTemplate.update("insert into Internship (id, mailContactPerson, description,"
				+ "renumeration) values (?,?,?,?);", internship.getId(), internship.getMailContactPerson(),
				internship.getDescription(), internship.getRenumeration());
	}
	
	public void updateInternship(Internship internship){
		this.jdbcTemplate.update("update Internship set mailContactPerson=?, "
				+ "description=?, renumeration=? where id=?;", internship.getMailContactPerson(),
				internship.getDescription(), internship.getRenumeration(), internship.getId());
	}
	
	public void deleteInternship(long id){
		this.jdbcTemplate.update("delete from Internship where id=?", id);
	}
}
