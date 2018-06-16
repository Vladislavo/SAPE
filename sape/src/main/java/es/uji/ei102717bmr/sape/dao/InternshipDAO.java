package es.uji.ei102717bmr.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
	    	internship.setId(rs.getLong("id"));
	    	internship.setCif_Company(rs.getString("cif_Company"));
	    	internship.setRemuneration(rs.getString("remuneration"));
	    	internship.setMailContactPerson(rs.getString("mailContactPerson"));
	    	internship.setDescription(rs.getString("description"));

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
		this.jdbcTemplate.update("insert into Internship (cif_Company, mailContactPerson, remuneration,"
				+ "description) values (?,?,?,?);", internship.getCif_Company(), internship.getMailContactPerson(),
				internship.getRemuneration(), internship.getDescription());
	}
	
	public void updateInternship(Internship internship){
		this.jdbcTemplate.update("update Internship set mailContactPerson=?, "
				+ "description=?, remuneration=? where id=?;", internship.getMailContactPerson(),
				internship.getDescription(), internship.getRemuneration(), internship.getId());
	}
	
	public void deleteInternship(long id){
		this.jdbcTemplate.update("delete from Internship where id=?", id);
	}
}
