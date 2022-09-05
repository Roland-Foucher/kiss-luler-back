package co.simplon.alt3.kisslulerback.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.alt3.kisslulerback.library.entites.Order;
import co.simplon.alt3.kisslulerback.library.entites.User;

public interface OrderRepo extends JpaRepository<Order, Integer> {
  List<Order> findByUser(User user);
}
