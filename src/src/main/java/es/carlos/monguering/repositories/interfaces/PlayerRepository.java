package es.carlos.monguering.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.carlos.monguering.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	public Player findByNameIgnoreCaseAndPassword(String name, String password);
	
	public Player findByToken(String token);
}