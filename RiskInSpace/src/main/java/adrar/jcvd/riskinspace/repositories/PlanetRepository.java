package adrar.jcvd.riskinspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adrar.jcvd.riskinspace.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
	
	

}
