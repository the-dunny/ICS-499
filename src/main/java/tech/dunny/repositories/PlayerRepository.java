package tech.dunny.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tech.dunny.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {


}
