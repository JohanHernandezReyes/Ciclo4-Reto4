
package Ciclo4.Reto4.Repository;

import Ciclo4.Reto4.Model.Order;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IfcOrder extends MongoRepository<Order, Integer>{
    
    @Query("{'salesMan.zone':?0}")
    public List <Order> findByZone(String zone);
    
    @Query("{status:?0}")
    public List <Order> findByStatus(String status);
    
    //Seleccionar orden con el maximo id
    public Optional <Order> findTopByOrderByIdDesc();
    
    @Query("{'salesMan.id':?0}")
    public List <Order> findBySalesMan(int idsalesMan);
    
    @Query("{registerDay:?0}")
    public List <Order> findByRegisterDay(Date fecha);
    
}
