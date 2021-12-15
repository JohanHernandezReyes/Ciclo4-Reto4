
package Ciclo4.Reto4.Repository;

import Ciclo4.Reto4.Model.Order;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
 @Autowired
    private IfcOrder OrdersCRUD; 
    
    public List<Order> MostrarOrdenes(){
        return OrdersCRUD.findAll();
    }
    
    public Optional<Order> BuscarOrden(int id){
        return OrdersCRUD.findById(id);
    }
    
    public List<Order> BuscarOrdenZona(String zone){
        return OrdersCRUD.findByZone(zone);
    }
    
    public List<Order> BuscarOrdenStatus(String status){
        return OrdersCRUD.findByStatus(status);
    }
    
    public List<Order> BuscarOrdenesVendedor(int idsalesman){
        return OrdersCRUD.findBySalesMan(idsalesman);
    }
    
    public List<Order> BuscarOrdenesFecha(Date fecha){
        return OrdersCRUD.findByRegisterDay(fecha);
    }
    
    public Order GuardarOrden(Order O){
        return OrdersCRUD.save(O);
    } 
    
    public Optional<Order> LastId(){
        return OrdersCRUD.findTopByOrderByIdDesc();
    }
    
    public void EliminarOrden(int id){
        OrdersCRUD.deleteById(id);
    }
}
