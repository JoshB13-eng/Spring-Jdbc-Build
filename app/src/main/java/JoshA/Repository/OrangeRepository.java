
package JoshA.Repository;

import org.springframework.stereotype.Repository;
import java.util.List;

import JoshA.DataBox.Orange;

public interface OrangeRepository{
    
	List<Orange> getAllOrange();
	
	Orange findByNumberOfOrange(Integer NumberOfOrange);
	
	List<Orange> findByRange(Integer Offset,Integer Limit);
	
	int createOrange(Orange orange);
	
	int update(Orange orange);
	
	int delete(Integer NumberOfOrange);
	
	int deleteAll();
	
}
