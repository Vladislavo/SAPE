package es.uji.ei102717bmr.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102717bmr.sape.model.Company;

@Repository
public class CompanyDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class CompanyMapper implements RowMapper<Company> { 

	    public Company mapRow(ResultSet rs, int rowNum) throws SQLException { 
	        Company company = new Company();
	        company.setCif(rs.getString("cif"));
	        company.setMail(rs.getString("mail"));
	        company.setName(rs.getString("name"));
	        company.setAddress(rs.getString("address"));
	        company.setTelephone(rs.getString("telephone"));
	        company.setVat(rs.getInt("VAT"));
	        //company.setOffers();
	        return company;
	    }
	}
	
	public List<Company> getCompanies(){
		return this.jdbcTemplate.query("select * from Company;", new CompanyMapper());
	}
	
	public Company getCompany(String cif){
		return this.jdbcTemplate.queryForObject("select * from Company where cif=?;", 
				new Object[] {cif}, new CompanyMapper());
	}
	
	public void addCompany(Company company){
		this.jdbcTemplate.update("insert into Company (cif, mail, name, address, telephone, VAT)"
				+ " values (?,?,?,?,?,?);", company.getCif(), company.getMail(), company.getName(),
				company.getAddress(), company.getTelephone(), company.getVat());
	}
	
	public void updateCompany(Company company){
		this.jdbcTemplate.update("update Company set mail=?, name=?, address=?, telephone=?, "
				+ "VAT=? where cif=?;", company.getMail(), company.getName(), company.getAddress(),
				company.getTelephone(), company.getVat(), company.getCif());
	}
	
	public void deleteCompany(String cif){
		this.jdbcTemplate.update("delete from Company where cif=?;", cif);
	}
}
