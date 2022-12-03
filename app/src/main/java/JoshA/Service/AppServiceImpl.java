package JoshA.Service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import JoshA.DataBox.*;
import JoshA.Repository.*;


@Service
public class AppServiceImpl implements AppService{
    
    @Autowired
    private FruitRepository fruitR;
    
    @Autowired
    private AppleRepository appleR;
    
    @Autowired
    private OrangeRepository orangeR;
    
    @Autowired
    private LemonRepository lemonR;
    
    
	@Override public List<Fruit> getAllFruit(){
	    
	    List<Fruit> fruitLt = new ArrayList<>();
	    
	    for(int t=0; t<fruitR.getAllFruit().size(); t++){
	        
	        Fruit fruit = fruitR.getAllFruit().get(t);
	        
	        fruit.setApple(appleR.getAllApple().get(t));
	        
	        fruit.setLemon(lemonR.getAllLemon().get(t));
	        
	        fruit.setOrange(orangeR.getAllOrange().get(t));
	        
	        fruitLt.add(fruit);
	    }
	    
	    return fruitLt;
	}
	
	@Override public Fruit findByNumberOfFruit(Integer NumberOfFruit){
	    
	    Fruit fruit = fruitR.findByNumberOfFruit(NumberOfFruit);
	    
	    fruit.setApple(appleR.findByNumberOfApple(NumberOfFruit));
	    
	    fruit.setLemon(lemonR.findByNumberOfLemon(NumberOfFruit));
	    
	    fruit.setOrange(orangeR.findByNumberOfOrange(NumberOfFruit));
	    
	    return fruit;
	    
	}
	
	@Override public List<Fruit> findByRange(Integer offset, Integer limit){

	    
	    List<Fruit> fruitLt = new ArrayList<>();
	    
	    for(int t=0; t<fruitR.findByRange(offset,limit).size(); t++){
	        
	        Fruit fruit = fruitR.findByRange(offset,limit).get(t);
	        
	        fruit.setApple(appleR.findByRange(offset,limit).get(t));
	        
	        fruit.setLemon(lemonR.findByRange(offset,limit).get(t));
	        
	        fruit.setOrange(orangeR.findByRange(offset,limit).get(t));
	        
	        fruitLt.add(fruit);
	    }
	    
	    return fruitLt;
	    
	}
	
	@Override public void createFruit(Fruit fruit){
	    
	    fruitR.createFruit(fruit);
	    
	    appleR.createApple(fruit.getApple());
	    
	    lemonR.createLemon(fruit.getLemon());
	    
	    orangeR.createOrange(fruit.getOrange());
	    
	}
	
	@Override public void update(Fruit fruit){
	    //
	    fruitR.update(fruit);
	    
	    appleR.update(fruit.getApple());
	    lemonR.update(fruit.getLemon());
	    orangeR.update(fruit.getOrange());
	    
	}
	
	@Override public void delete(Integer NumberOfFruit){
	    //
	    appleR.delete(NumberOfFruit);

	    lemonR.delete(NumberOfFruit);

	    orangeR.delete(NumberOfFruit);
	    
	    fruitR.delete(NumberOfFruit);
	    
	}
	
	@Override public void deleteAll(){
	    //
	    appleR.deleteAll();

	    lemonR.deleteAll();

	    orangeR.deleteAll();
	    
	    fruitR.deleteAll();
	}
}
