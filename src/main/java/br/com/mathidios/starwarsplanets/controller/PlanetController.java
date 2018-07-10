package br.com.mathidios.starwarsplanets.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mathidios.starwarsplanets.model.Planet;
import br.com.mathidios.starwarsplanets.service.PlanetService;
import br.com.mathidios.starwarsplanets.swapi.PlanetSWAPI;
import br.com.mathidios.starwarsplanets.swapi.SWModel;
import br.com.mathidios.starwarsplanets.swapi.StarWars;
import br.com.mathidios.starwarsplanets.swapi.StarWarsApi;
import retrofit2.Call;
import retrofit2.Response;

@RestController
@RequestMapping("/planets")
public class PlanetController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlanetService planetService;
	
	@GetMapping("/films/{nmPlanet}")
	public ResponseEntity<Integer> getNumberOfFilmsFromPlanetNameOnSWAPI(@PathVariable(name="nmPlanet") String nmPlanet) throws Exception{
		return Optional
		        .ofNullable( this.getFilmsNumberFromStarWarsAPIByPlanetName(nmPlanet) )
		        .map( numberOfFilms -> ResponseEntity.ok().body(numberOfFilms) ) 
		        .orElseGet( () -> ResponseEntity.notFound().build() ); 
	}

	private int getFilmsNumberFromStarWarsAPIByPlanetName(String nmPlanet) throws Exception {
		StarWars api = StarWarsApi.getApi();
		
		Call<SWModel> planets = api.getPlanetByName(nmPlanet);
		
		Response<SWModel> response = planets.execute();
		
		if (response.isSuccessful()) {
            SWModel data = response.body();
            
            if(data.getResults() != null && !data.getResults().isEmpty()) {
            	
            	PlanetSWAPI planet = data.getResults().get(0);
            	log.debug("Planet: " + planet.getName() + " films: " + (planet.getFilms() != null && !planet.getFilms().isEmpty() ? planet.getFilms().size() : 0));
            	return planet.getFilms() != null && !planet.getFilms().isEmpty() ? planet.getFilms().size() : 0;
            } 
		} 
		return 0;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Planet> createNewPlanet(@RequestBody Planet planet) {
		try {
			
			int filmsNumber = this.getFilmsNumberFromStarWarsAPIByPlanetName(planet.getNmPlanet());
			planet.setNumberMovies(filmsNumber);
			return Optional
			        .ofNullable( this.planetService.create(planet) )
			        .map( newPlanet -> ResponseEntity.status(HttpStatus.CREATED).body(newPlanet) ) 
			        .orElseGet( () -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() );
			
		} catch (Exception e) {
			log.error("Error create new planet: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Planet>> getAllPlanets(){
		return Optional
				.ofNullable( this.planetService.findAll() )
				.map( planetList -> ResponseEntity.ok().body(planetList) )
				.orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	//TODO: find by name (usar UPPER)***
	@GetMapping("/name/{nmPlanet}")
	public ResponseEntity<List<Planet>> getPlanetByName(@PathVariable(name="nmPlanet") String nmPlanet) {
		return Optional
				.ofNullable( this.planetService.findByNmPlanet(nmPlanet) )
				.map( planetList -> ResponseEntity.ok().body(planetList) )
				.orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	@GetMapping("/{idPlanet}")
	public ResponseEntity<Planet> getPlanetById(@PathVariable(name="idPlanet") Long idPlanet) {
		return Optional
		        .ofNullable( this.planetService.findByIdPlanet(idPlanet) )
		        .map( planet -> ResponseEntity.ok().body(planet) ) 
		        .orElseGet( () -> ResponseEntity.notFound().build() ); 
	}
	
	@DeleteMapping("/delete/{idPlanet}")
	public ResponseEntity<?> deletePlanetById(@PathVariable(name="idPlanet") Long idPlanet) {
		try {
			this.planetService.deleteByIdPlanet(idPlanet);	
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			log.error("Error to delete planet: ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
