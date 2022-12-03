
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
import JoshA.DataBox.Fruit;


@Repository("FruitRepository")
public class FruitRepImpl implements FruitRepository{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<Fruit> rowMapper=new RowMapper<Fruit>() {
        
		@Override public Fruit mapRow(ResultSet rs, int rowNum) throws SQLException {
		    
			Fruit fruit=new Fruit();
			
			fruit.setNumberOfFruit(rs.getInt("FRUIT.numberOfFruit"));
			
		    fruit.setByte(rs.getBytes("FRUIT.fileByt"));
		    
			return fruit;
		}	
	};
    
    @Override public List<Fruit> getAllFruit(){
        
        List<Fruit> fruit = jdbcTemplate.query("SELECT * FROM FRUIT", rowMapper);
        
        return fruit;
    }
    
    @Override public Fruit findByNumberOfFruit(Integer numberOfFruit){
        
        Fruit fruit;
        
        try {
            //
            fruit = jdbcTemplate.queryForObject("SELECT * FROM FRUIT WHERE numberOfFruit=?",new Object[]{numberOfFruit},rowMapper);
            
        } 
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        
        return fruit;
    }
    
    @Override public List<Fruit> findByRange(Integer Offset,Integer Limit){
        
        List<Fruit> fruit = new ArrayList<>();
        
        try {
            
            fruit = jdbcTemplate.query("SELECT * FROM FRUIT WHERE numberOfFruit BETWEEN ? and ?",new Object[]{Offset, Limit},rowMapper);
        }
        
        catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return fruit;
    }
    
    @Override public int createFruit(Fruit fruit){
        
        return jdbcTemplate.update("INSERT INTO FRUIT (numberOfFruit,fileByt) VALUES(?,?)", new Object[] {fruit.getNumberOfFruit(),fruit.getByte()});
    }
    
    @Override public int update(Fruit fruit){
        
        return jdbcTemplate.update("UPDATE FRUIT SET fileByt=? WHERE numberOfFruit=?", new Object[] {fruit.getByte(),fruit.getNumberOfFruit()});
        //
    }
    
    @Override public int delete(Integer NumberOfFruit){
        
        return jdbcTemplate.update("DELETE FROM FRUIT WHERE numberOfFruit=?", NumberOfFruit);
    }
    
    @Override public int deleteAll(){
        
        return jdbcTemplate.update("DELETE FROM FRUIT");
    }
    
}
