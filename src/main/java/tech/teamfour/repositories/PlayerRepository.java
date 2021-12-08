package tech.teamfour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.teamfour.model.Player;

/**
 * The Interface PlayerRepository.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    /**
     * Find by user name.
     *
     * @param userName the user name
     * @return the player
     */
    Player findByUserName(String userName);
}
