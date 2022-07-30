package co.simplon.alt3.kisslulerback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import co.simplon.alt3.kisslulerback.entites.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
