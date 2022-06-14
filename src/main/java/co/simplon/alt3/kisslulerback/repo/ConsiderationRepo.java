package co.simplon.alt3.kisslulerback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import co.simplon.alt3.kisslulerback.entites.Consideration;

public interface ConsiderationRepo extends JpaRepository <Consideration, Integer> {
    
}
