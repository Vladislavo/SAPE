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
import es.uji.ei102717bmr.sape.model.Review;

@Repository
public class ReviewDAO {
	
	private JdbcTemplate jdbcTemplate;
    
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class ReviewMapper implements RowMapper<Review> { 

	    public Review mapRow(ResultSet rs, int rowNum) throws SQLException { 
	        Review review = new Review();
	        //review.setProjectOfferId(rs.getString("id_ProjectOffer"));
	        review.setCreationDate(rs.getDate("creationDate"));
	        review.setText(rs.getString("description"));

	        return review;
	    }
	}
	
	public List<Review> getReviews(){
		return this.jdbcTemplate.query("select * from Review;" , new ReviewMapper());
	}
	
	public Review getReview(long idProjectOffer, Date creationDate) {
		return this.jdbcTemplate.queryForObject("select * from Review where id_ProjectOffer = ? AND creationDate = ?;",
				new Object[]{idProjectOffer, creationDate}, new ReviewMapper());
		
	}
	public void addReview(Review review){
		this.jdbcTemplate.update(
				"insert into Review (id_ProjectOffer, creationDate, description) "
				+ " values (?,?,?);", 
				review.getProjectOffer().getId(), review.getCreationDate(), review.getText());
				
	}
	public void updateReview(Review review) {
		this.jdbcTemplate.update("update Review set (text = ?"
				+ " where id_ProjectOffer = ? AND creationDate = ?);", 
				review.getText(), review.getProjectOffer().getId(), review.getCreationDate());
	}			
	public void deleteReview(long idProjectOffer, Date creationDate) {
		this.jdbcTemplate.update("delete from Review where id_ProjectOffer = ? AND creationDate = ?;", 
				idProjectOffer, creationDate);
	}
	

}