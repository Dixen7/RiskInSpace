package adrar.jcvd.riskinspace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adrar.jcvd.riskinspace.Planet;
import adrar.jcvd.riskinspace.Player;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
	
	public List<Planet> findAllByPlanetOwner(Player p);

	

}
