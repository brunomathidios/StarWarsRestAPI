package br.com.mathidios.starwarsplanets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mathidios.starwarsplanets.publicapi.PlanetPublicAPI;
import br.com.mathidios.starwarsplanets.publicapi.SWModelList;
import br.com.mathidios.starwarsplanets.publicapi.StarWars;
import br.com.mathidios.starwarsplanets.publicapi.StarWarsApi;
import br.com.mathidios.starwarsplanets.service.PlanetService;
import retrofit2.Call;
import retrofit2.Response;

@RestController
@RequestMapping("/planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;
	
	@GetMapping
	public void getAllPlanetsPublicAPI() throws Exception{
		StarWars api = StarWarsApi.getApi();
		
		Call<SWModelList<PlanetPublicAPI>> planets = api.getAllPlanets(null);
		
		Response<SWModelList<PlanetPublicAPI>> response = planets.execute();
		
		if (response.isSuccessful()) {
            SWModelList<PlanetPublicAPI> data = response.body();
            for (PlanetPublicAPI planet : data.results) {
            	System.out.println("Planet: " + planet.name + " " + planet.climate + " " + planet.population);
            }
		
		}
		
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "teste da aplicação star wars";
	}
}
