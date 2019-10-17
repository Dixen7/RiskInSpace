package adrar.jcvd.riskinspace;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adrar.jcvd.riskinspace.repositories.SpeciesRepository;


@Service
public class SpeciesService {
	
	@Autowired
	SpeciesRepository speciesRepo;
	
	public List<Species> findAll() {
		return speciesRepo.findAll();
	}

	public Species findOne(int id) {
		// TODO Auto-generated method stub
		return speciesRepo.getOne(id);
	}

	

}
