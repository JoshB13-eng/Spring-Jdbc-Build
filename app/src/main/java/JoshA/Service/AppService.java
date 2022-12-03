package JoshA.Service;

import java.util.List;

import JoshA.DataBox.*;

public interface AppService{

	List<Fruit> getAllFruit();
	
	Fruit findByNumberOfFruit(Integer NumberOfFruit);
	
	List<Fruit> findByRange(Integer Offset,Integer Limit);
	
	void createFruit(Fruit fruit);
	
	void update(Fruit fruit);
	
	void delete(Integer NumberOfFruit);
	
	void deleteAll();
}
