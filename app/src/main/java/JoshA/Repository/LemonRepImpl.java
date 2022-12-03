
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
import JoshA.DataBox.Lemon;


@Repository("LemonRepository")
public class LemonRepImpl implements LemonRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<Lemon> rowMapper=new RowMapper<Lemon>() {

		@Override public Lemon mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lemon lemon=new Lemon();
			
			lemon.setNumberOfLemon(rs.getInt("LEMON.numberOfLemon"));
			//
		    lemon.setAmount(rs.getInt("LEMON.amount"));
			
			return lemon;
		}	
	};
    
    @Override public List<Lemon> getAllLemon(){
        
        List<Lemon> lemon = jdbcTemplate.query("SELECT * FROM LEMON", rowMapper);
        
        return lemon;
    }
    
    @Override public Lemon findByNumberOfLemon(Integer numberOfLemon){
    
        Lemon lemon;
        
        try {
            //
            lemon = jdbcTemplate.queryForObject("SELECT * FROM LEMON WHERE numberOfLemon=?",new Object[]{numberOfLemon},rowMapper);
            
        }
        
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        
        return lemon;
    }
    
    @Override public List<Lemon> findByRange(Integer Offset,Integer Limit){
        
        List<Lemon> lemon = new ArrayList<>();
        
        try {
            
            lemon = jdbcTemplate.query("SELECT * FROM LEMON WHERE numberOfLemon BETWEEN ? and ?",new Object[]{Offset, Limit},rowMapper);
            
        } 
        
        catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return lemon;
    }
    
    @Override public int createLemon(Lemon lemon){
        
        return jdbcTemplate.update("INSERT INTO LEMON (numberOfLemon,amount) VALUES(?,?)", new Object[] {lemon.getNumberOfLemon(),lemon.getAmount()});
    }
    
    @Override public int update(Lemon lemon){
        //;
        return jdbcTemplate.update("UPDATE LEMON SET amount=? WHERE numberOfLemon=?", new Object[] {lemon.getAmount(),lemon.getNumberOfLemon()});
    }
    
    @Override public int delete(Integer NumberOfLemon){
        
        return jdbcTemplate.update("DELETE FROM LEMON WHERE numberOfLemon=?", NumberOfLemon);
    }
    
    @Override public int deleteAll(){
        return jdbcTemplate.update("DELETE FROM LEMON");
    }
    
}

