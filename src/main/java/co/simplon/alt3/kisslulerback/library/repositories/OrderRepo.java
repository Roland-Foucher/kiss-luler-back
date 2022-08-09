package co.simplon.alt3.kisslulerback.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.alt3.kisslulerback.library.entites.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
