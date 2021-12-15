
package Ciclo4.Reto4.Services;

import Ciclo4.Reto4.Model.Order;
import Ciclo4.Reto4.Repository.OrderRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServices {
   
    @Autowired
    private OrderRepository OrdersRepository;
    
 
    public List <Order> MostrarOrdenes(){
        return (List<Order>) OrdersRepository.MostrarOrdenes();
    }    
    
    public Optional<Order> BuscarOrdenId(int id){
        return OrdersRepository.BuscarOrden(id);
    }
       
    public List<Order> BuscarOrdenZona(String zone){
        return OrdersRepository.BuscarOrdenZona(zone);
    }
    
    public List<Order> BuscarOrdenesVendedor(int idsalesman){
        return OrdersRepository.BuscarOrdenesVendedor(idsalesman);
    }
    
    public List<Order> BuscarOrdenStatus(String status){
         return OrdersRepository.BuscarOrdenStatus(status);
    }
    
    public List<Order> BuscarOrdenesFecha(Date fecha){
         return OrdersRepository.BuscarOrdenesFecha(fecha);
    }
    
    public Order GuardarOrden(Order O){
        
        Optional <Order> NextOrder = OrdersRepository.LastId(); 
        if (O.getId()==null){
            if(!NextOrder.isPresent()){
                O.setId(1);
            }
            else{
                O.setId(NextOrder.get().getId() + 1);
            }      
        }   
  
        Optional<Order> OrderX = OrdersRepository.BuscarOrden(O.getId());
        if (!OrderX.isPresent()) {
            return OrdersRepository.GuardarOrden(O);
        } else {
            return O;
        }  
        
    }
 
    public Order ActualizarOrden(Order O) {
        if (O.getId() != null) {
            Optional<Order> OrderX = OrdersRepository.BuscarOrden(O.getId());
            if (OrderX.isPresent()) {
                if (O.getStatus() != null && O.getRegisterDay() != null && O.getSalesMan() != null && O.getProducts() != null && O.getQuantities() != null ) {
                    OrderX.get().setId(O.getId());
                    OrderX.get().setRegisterDay(O.getRegisterDay());
                    OrderX.get().setStatus(O.getStatus());
                    OrderX.get().setSalesMan(O.getSalesMan());
                    OrderX.get().setProducts(O.getProducts());
                    OrderX.get().setQuantities(O.getQuantities());
                }
                return OrdersRepository.GuardarOrden(OrderX.get());
            }
        }
        return O;
    }
    
    public Order ActualizarStatusOrden(Order O) {
        if (O.getId() != null) {
            Optional<Order> OrderX = OrdersRepository.BuscarOrden(O.getId());
            if (OrderX.isPresent()) {
                if (O.getStatus() != null) {
                    OrderX.get().setId(O.getId());
                    OrderX.get().setStatus(O.getStatus());
                }
                return OrdersRepository.GuardarOrden(OrderX.get());
            }
        }
        return O;
    }
    
    public boolean EliminarOrden(int id) {
        Optional<Order> OrderX = OrdersRepository.BuscarOrden(id);
        if (OrderX.isPresent()) {
            OrdersRepository.EliminarOrden(id);
            return true;
        }
        return false;
    }   
    
    public List<Order> BuscarOrdenesVendedorEstado(String status, int idsalesman) {
        List<Order> OrdersX = OrdersRepository.BuscarOrdenStatus(status);
        List<Order> OrdersY = OrdersRepository.BuscarOrdenesVendedor(idsalesman);
        List<Order> OrdersZ = new ArrayList<>();
        for (Order x:OrdersX){
           if(OrdersY.contains(x)){
               OrdersZ.add(x);
           }
        }
            
        return OrdersZ;    
    }

    public List<Order> BuscarOrdenesVendedorFecha(String fecha, int idsalesman) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd");
        Date FechaPedido = dt.parse(fecha);
        List<Order> OrdersX = OrdersRepository.BuscarOrdenesFecha(FechaPedido);
        List<Order> OrdersY = OrdersRepository.BuscarOrdenesVendedor(idsalesman);
        List<Order> OrdersZ = new ArrayList<>();
        for (Order x:OrdersX){
           if(OrdersY.contains(x)){
               OrdersZ.add(x);
           }
        }
        System.out.println(OrdersX);
        System.out.println(OrdersY);
        System.out.println(FechaPedido);    
        return OrdersZ;    
    }

}
