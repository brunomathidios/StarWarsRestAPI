package br.com.mathidios.starwarsplanets.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mathidios.starwarsplanets.dao.PlanetDAO;
import br.com.mathidios.starwarsplanets.model.Planet;
import br.com.mathidios.starwarsplanets.service.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {
	
	@Autowired
	private PlanetDAO planetDAO;

	@Override
	public Planet create(Planet planet) {
		return this.planetDAO.save(planet);
	}

	@Override
	public List<Planet> findAll() {
		List<Planet> planetList = this.planetDAO.findAll();
		return (planetList != null && !planetList.isEmpty()) ? planetList : null;
	}

	@Override
	public List<Planet> findByNmPlanet(String nmPlanet) {
		List<Planet> planetList = this.planetDAO.findByNmPlanetLike("%" + nmPlanet + "%");
		return (planetList != null && !planetList.isEmpty()) ? planetList : null;
	}

	@Override
	public Planet findByIdPlanet(Long idPlanet) {
		Optional<Planet> planet = this.planetDAO.findById(idPlanet);
		return planet.orElse(null);
	}

	@Override
	public void deleteByIdPlanet(Long idPlanet) {
		this.planetDAO.deleteById(idPlanet);
		
	}

}
