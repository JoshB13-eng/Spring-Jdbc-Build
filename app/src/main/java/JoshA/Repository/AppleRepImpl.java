
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
import JoshA.DataBox.Apple;


@Repository("AppleRepository")
public class AppleRepImpl implements AppleRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<Apple> rowMapper=new RowMapper<Apple>() {

		@Override public Apple mapRow(ResultSet rs, int rowNum) throws SQLException {
			Apple apple=new Apple();
			
			apple.setNumberOfApple(rs.getInt("APPLE.numberOfApple"));
			//
		    apple.setAmount(rs.getInt("APPLE.amount"));
			
			return apple;
		}	
	};
    
    @Override public List<Apple> getAllApple(){
        
        List<Apple> apple = jdbcTemplate.query("SELECT * FROM APPLE", rowMapper);
        
        return apple;
    }
    
    @Override public Apple findByNumberOfApple(Integer numberOfApple){
        
        Apple apple;
        
        try {
            
            apple = jdbcTemplate.queryForObject("SELECT * FROM APPLE WHERE numberOfApple=?",new Object[]{numberOfApple},rowMapper);
            
        } 
        
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        
        return apple;
    }
    
    @Override public List<Apple> findByRange(Integer Offset,Integer Limit){
        
        List<Apple> apple = new ArrayList<>();
        
        try {
            
            apple = jdbcTemplate.query("SELECT * FROM APPLE WHERE numberOfApple BETWEEN ? and ?",new Object[]{Offset, Limit},rowMapper);
            
        }
        
        catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return apple;
    }
    
    @Override public int createApple(Apple apple){
        
        return jdbcTemplate.update("INSERT INTO APPLE (numberOfApple,amount) VALUES(?,?)", new Object[] {apple.getNumberOfApple(),apple.getAmount()});
    }
    
    @Override public int update(Apple apple){
    
        return jdbcTemplate.update("UPDATE APPLE SET amount=? WHERE numberOfApple=?", new Object[] {apple.getAmount(),apple.getNumberOfApple()});
    }
    
    @Override public int delete(Integer NumberOfApple){
        
        return jdbcTemplate.update("DELETE FROM APPLE WHERE numberOfApple=?", NumberOfApple);
    }
    
    @Override public int deleteAll(){
        return jdbcTemplate.update("DELETE FROM APPLE");
    }
    
}

