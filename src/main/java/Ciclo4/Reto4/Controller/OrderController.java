
package Ciclo4.Reto4.Controller;

import Ciclo4.Reto4.Model.Order;
import Ciclo4.Reto4.Services.OrderServices;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OrderController {
    
    @Autowired
    private OrderServices OrdersServices;
    
    @GetMapping("/all")
    public List<Order> MostrarOrdenes(){
        return OrdersServices.MostrarOrdenes();
    }
    
    @GetMapping("/zona/{zone}")
    public List<Order> BuscarOrdenZona(@PathVariable("zone") String zone){
        return OrdersServices.BuscarOrdenZona(zone);
    }
    
    @GetMapping("/state/{status}")
    public List<Order> BuscarOrdenStatus(@PathVariable("status") String status){
        return OrdersServices.BuscarOrdenStatus(status);
    }
    
    @GetMapping("/salesman/{id}")
    public List<Order> BuscarOrdenesVendedor(@PathVariable("id") int id){
        return OrdersServices.BuscarOrdenesVendedor(id);
    }
    
    @GetMapping("/{id}")
    public Optional<Order> BuscarOrdenId(@PathVariable("id") int id){
        return OrdersServices.BuscarOrdenId(id);
    }
   
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order GuardarOrden(@RequestBody Order O){
        return OrdersServices.GuardarOrden(O);
    }
        
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order ActualizarStatusOrden(@RequestBody Order O){
        return OrdersServices.ActualizarStatusOrden(O);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean BorrarOrden(@PathVariable("id") int id){
        return OrdersServices.EliminarOrden(id);
    }
    
    @GetMapping("/state/{status}/{id}")
    public List<Order> BuscarOrdenesVendedorEstado(@PathVariable("status") String status, @PathVariable("id") int idsalesman){
        return OrdersServices.BuscarOrdenesVendedorEstado(status, idsalesman);
    }
   
    @GetMapping("/date/{registerDay}/{id}")
    public List<Order> BuscarOrdenesVendedorFecha(@PathVariable("registerDay") String fecha, @PathVariable("id") int idsalesman) throws ParseException{
        return OrdersServices.BuscarOrdenesVendedorFecha(fecha, idsalesman);
    }
}
