
package JoshA.Repository;

import org.springframework.stereotype.Repository;
import java.util.List;

import JoshA.DataBox.Fruit;

public interface FruitRepository{
    
	List<Fruit> getAllFruit();
	
	Fruit findByNumberOfFruit(Integer NumberOfFruit);
	
	List<Fruit> findByRange(Integer Offset,Integer Limit);
	
	int createFruit(Fruit fruit);
	
	int update(Fruit fruit);
	
	int delete(Integer NumberOfFruit);
	
	int deleteAll();
	
}
