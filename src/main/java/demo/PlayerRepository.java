package demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marion on 04/06/15.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
