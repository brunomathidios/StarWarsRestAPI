package br.com.mathidios.starwarsplanets.service;

import java.util.List;

import br.com.mathidios.starwarsplanets.model.Planet;

public interface PlanetService {

	Planet create(Planet planet);
	List<Planet> findAll();
	List<Planet> findByNmPlanet(String nmPlanet);
	Planet findByIdPlanet(Long idPlanet);
	void deleteByIdPlanet(Long idPlanet);
}
