package br.com.mathidios.starwarsplanets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	private PlanetService planetService;
	
	@GetMapping("/{nmPlanet}")
	public void getAllPlanetsPublicAPI(@PathVariable(name="nmPlanet") String nmPlanet) throws Exception {
		
		int filmsNumber = this.getFilmsNumberFromStarWarsAPIByPlanetName(nmPlanet);
	}

	private int getFilmsNumberFromStarWarsAPIByPlanetName(String nmPlanet) throws Exception {
		StarWars api = StarWarsApi.getApi();
		
		Call<SWModel> planets = api.getPlanetByName(nmPlanet);
		
		Response<SWModel> response = planets.execute();
		
		if (response.isSuccessful()) {
            SWModel data = response.body();
            PlanetSWAPI planet = data.getResults().get(0);
            System.out.println("Planet: " + planet.getName() + " films: " + (planet.getFilms() != null && !planet.getFilms().isEmpty() ? planet.getFilms().size() : 0));
        	return planet.getFilms() != null && !planet.getFilms().isEmpty() ? planet.getFilms().size() : 0;
		} else {
			return 0;
		}
	}
}
