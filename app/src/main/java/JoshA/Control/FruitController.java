
//My Controller class

package JoshA.Control;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.FileInputStream; 
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import JoshA.DataBox.Fruit;
import JoshA.DataBox.Apple;
import JoshA.Service.AppService;
import JoshA.Repository.AppleRepository;

@RestController
@RequestMapping(path = "/JoshFruit")
public class FruitController{
    //
    public static String Client;
    public  byte[] fileByt;
    
    //public Map<Integer,Fruit> fruit;
    
    @Autowired
    private AppService appServe;
    
    @Autowired
    private AppleRepository appleServe;
    
    @PostMapping(value="/UploadToDB", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException{
        //Upload file from request
        fileByt = file.getBytes();
        
        return "File is upload successfully"; 
    } 
    
    @PostMapping(path = "/Fruit/Create", consumes = "application/json", produces = "application/json")
    public String createFruit(@RequestBody Fruit fruitp){
        //Create a fruit bean record in DB table
        fruitp.setByte(fileByt);
        
        appServe.createFruit(fruitp);
        
        return "\nFruit is created successfully\n";
        
    }
    
    @PutMapping(path = "/Fruit/Update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(@RequestBody Fruit fruitp){
        //Update a fruit bean record in DB table
        
        fruitp.setByte(fileByt);
        
        appServe.update(fruitp);
        
        String str = "Fruit Database has been sucessfully updated\n";
        
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
    
    @GetMapping(path = "/Fruit", produces = "application/json")
    public List<Fruit> getFruit(){
        //Get all fruit bean records from DB table and send it to client
        
        return appServe.getAllFruit();
    }
    
    @GetMapping(path = "/FruitXY/{offset}/{limit}", produces = "application/json")
    public List<Fruit> getFruit(@PathVariable("offset") Integer offset,@PathVariable("limit") Integer limit){
        //Get range of fruit bean records from DB table and send it to client
        
        return appServe.findByRange(offset,limit);
    }
    
    @GetMapping(path = "/FruitX/{NumberOfFruit}", produces = "application/json")
    public Fruit getFruitX(@PathVariable("NumberOfFruit") Integer NumberOfFruit){
        //Get a particular fruit bean record from DB table and send it to client
        
        return appServe.findByNumberOfFruit(NumberOfFruit);
    }
    
    @DeleteMapping(path = "/Delete/{NumberOfFruit}")
    public String deleteFruit(@PathVariable("NumberOfFruit") Integer NumberOfFruit){
        //Delete a fruit bean record from DB table
        
        appServe.delete(NumberOfFruit);
        
        return "\nIndex"+NumberOfFruit+" NumberOfFruit data/FruitRecord has been sucessfully deleted";
    }
    
    @DeleteMapping(path = "/DeleteAll")
    public String deleteAll(){
        //Delete all fruit bean records from DB table
        
        appServe.deleteAll();
        
        return "\nAll DataBase records has been sucessfully deleted";
    }
    
    @RequestMapping(path = "/Exit")
    public ResponseEntity<Object> Exit(){
        //Exit app
        
        return new ResponseEntity<>("\nShutting down server...\n", HttpStatus.OK);
    }
    
}



//To upload File
//curl  -X POST  -i  -F "file=@/path/to/file" -F "additional_parm=param2" http://localhost:9090/JoshFruit/UploadToDB

//To create a fruit record to database
//curl -H "Content-Type: application/json" -X POST -d '{"numberOfFruit":25,"orange":{"numberOfOrange":25,"amount":2500},"apple":{"numberOfApple":25,"amount":12500},"lemon":{"numberOfLemon":25,"amount":7500}}' http://localhost:9090/JoshFruit/Fruit/Create

//To update fruit record in database
//curl -X PUT -d '{"numberOfFruit":1,"orange":{"numberOfOrange":1,"amount":1500},"apple":{"numberOfApple":1,"amount":7500},"lemon":{"numberOfLemon":1,"amount":4500}}' -H "Content-Type: application/json" http://localhost:9090/JoshFruit/Fruit/Update

//To update fruit record in database
//curl -X PUT -d '{"numberOfFruit":2,"orange":{"numberOfOrange":2,"amount":2500},"apple":{"numberOfApple":2,"amount":12500},"lemon":{"numberOfLemon":2,"amount":7500}}' -H "Content-Type: application/json" http://localhost:9090/JoshFruit/Fruit/Update

//To get All Records/FruitDatas from database
//curl http://localhost:9090/JoshFruit/Fruit

//To get a specified Record of FRUIT table from database
//curl http://localhost:9090/JoshFruit/FruitX/1

//To get another specified Record of FRUIT table from database
//curl http://localhost:9090/JoshFruit/FruitX/2

//To get a specified Range of Record of FRUIT table from database.
//curl http://localhost:9090/JoshFruit/FruitXY/1/2

//To delete a specified Record of FRUIT table from database
//curl -X "DELETE" http://localhost:9090/JoshFruit/Delete/0

//To delete ALL Records of FRUIT table from database
//curl -X "DELETE" http://localhost:9090/JoshFruit/DeleteAll

//To view database from browser console 
//http://localhost:9090/h2-console

//To exit program
//curl http://localhost:9090/JoshFruit/Exit

