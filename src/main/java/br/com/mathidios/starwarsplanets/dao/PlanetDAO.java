package br.com.mathidios.starwarsplanets.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mathidios.starwarsplanets.model.Planet;

@Repository
public interface PlanetDAO extends JpaRepository<Planet, Long> {

	List<Planet> findAll();
	List<Planet> findByNmPlanetLike(String nmPlanet);
}
