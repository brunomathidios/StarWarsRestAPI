package br.com.mathidios.starwarsplanets.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mathidios.starwarsplanets.dao.PlanetDAO;
import br.com.mathidios.starwarsplanets.model.Planet;
import br.com.mathidios.starwarsplanets.service.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlanetDAO planetDAO;

	@Override
	public Planet create(Planet planet) {
		try {
			return this.planetDAO.save(planet);
		} catch (Exception e) {
			log.error("Error create: ", e);
			return null;
		}
	}

	@Override
	public List<Planet> findAll() {
		try {
			List<Planet> planetList = this.planetDAO.findAll();
			return (planetList != null && !planetList.isEmpty()) ? planetList : null;
		} catch (Exception e) {
			log.error("Error findAll: ", e);
			return null;
		}
	}

	@Override
	public List<Planet> findByNmPlanet(String nmPlanet) {
		try {
			List<Planet> planetList = this.planetDAO.findByNmPlanetLike("%" + nmPlanet + "%");
			return (planetList != null && !planetList.isEmpty()) ? planetList : null;
		} catch (Exception e) {
			log.error("Error findByNmPlanet: ", e);
			return null;
		}
	}

	@Override
	public Planet findByIdPlanet(Long idPlanet) {
		try {
			Optional<Planet> planet = this.planetDAO.findById(idPlanet);
			return planet.orElse(null);
		} catch (Exception e) {
			log.error("Error findByIdPlanet: ", e);
			return null;
		}
	}

	@Override
	public void deleteByIdPlanet(Long idPlanet) {
		try {
			this.planetDAO.deleteById(idPlanet);
		} catch (Exception e) {
			log.error("Error deleteByIdPlanet: ", e);
			throw new RuntimeException(e);
		}
		
	}

}
