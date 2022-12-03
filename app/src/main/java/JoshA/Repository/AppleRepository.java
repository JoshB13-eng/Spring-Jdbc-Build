
package JoshA.Repository;

import org.springframework.stereotype.Repository;
import java.util.List;

import JoshA.DataBox.Apple;

public interface AppleRepository{
    
	List<Apple> getAllApple();
	
	Apple findByNumberOfApple(Integer NumberOfApple);
	
	List<Apple> findByRange(Integer Offset,Integer Limit);
	
	int createApple(Apple apple);
	
	int update(Apple apple);
	
	int delete(Integer NumberOfApple);
	
	int deleteAll();
	
}