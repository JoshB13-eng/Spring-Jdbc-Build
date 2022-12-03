
package JoshA.Repository;

import org.springframework.stereotype.Repository;
import java.util.List;

import JoshA.DataBox.Lemon;

public interface LemonRepository{
    
	List<Lemon> getAllLemon();
	
	Lemon findByNumberOfLemon(Integer NumberOfLemon);
	
	List<Lemon> findByRange(Integer Offset,Integer Limit);
	
	int createLemon(Lemon lemon);
	
	int update(Lemon lemon);
	
	int delete(Integer NumberOfLemon);
	
	int deleteAll();
	
}
