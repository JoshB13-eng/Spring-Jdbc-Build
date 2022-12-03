
package JoshA.Repository;

import java.util.List;
import java.util.ArrayList;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;
import JoshA.DataBox.Orange;


@Repository("OrangeRepository")
public class OrangeRepImpl implements OrangeRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<Orange> rowMapper=new RowMapper<Orange>() {
    
		@Override public Orange mapRow(ResultSet rs, int rowNum) throws SQLException {
		    
			Orange orange=new Orange();
			
			orange.setNumberOfOrange(rs.getInt("ORANGE.numberOfOrange"));
			
		    orange.setAmount(rs.getInt("ORANGE.amount"));
			
			return orange;
		}	
	};
    
    @Override public List<Orange> getAllOrange(){
        
        List<Orange> orange = jdbcTemplate.query("SELECT * FROM ORANGE", rowMapper);        
        
        return orange;
    }
    
    @Override public Orange findByNumberOfOrange(Integer numberOfOrange){
        
        Orange orange;
        
        try {
            
            orange = jdbcTemplate.queryForObject("SELECT * FROM ORANGE WHERE numberOfOrange=?",new Object[]{numberOfOrange},rowMapper);
            
        } 
        
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        
        return orange;
    }
    
    @Override public List<Orange> findByRange(Integer Offset,Integer Limit){
        
        List<Orange> orange = new ArrayList<>();
        
        try {
            
            orange = jdbcTemplate.query("SELECT * FROM ORANGE WHERE numberOfOrange BETWEEN ? and ?",new Object[]{Offset, Limit},rowMapper);
            
        } 
        catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return orange;
    }
    
    @Override public int createOrange(Orange orange){
        
        return jdbcTemplate.update("INSERT INTO ORANGE (numberOfOrange,amount) VALUES(?,?)", new Object[] {orange.getNumberOfOrange(),orange.getAmount()});
        
    }
    
    @Override public int update(Orange orange){
        
        return jdbcTemplate.update("UPDATE ORANGE SET amount=? WHERE numberOfOrange=?", new Object[] {orange.getAmount(),orange.getNumberOfOrange()});
    }
    
    @Override public int delete(Integer NumberOfOrange){
        
        return jdbcTemplate.update("DELETE FROM ORANGE WHERE numberOfOrange=?", NumberOfOrange);
    }
    
    @Override public int deleteAll(){
        
        return jdbcTemplate.update("DELETE FROM ORANGE");
    }
    
}

