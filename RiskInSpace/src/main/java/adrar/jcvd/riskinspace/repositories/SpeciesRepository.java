package adrar.jcvd.riskinspace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adrar.jcvd.riskinspace.Species;

public interface SpeciesRepository extends JpaRepository<Species, Integer>{
	
	//List<Species> findAll();

}
