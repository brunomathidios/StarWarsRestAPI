package br.com.mathidios.starwarsplanets;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.mathidios.starwarsplanets.model.Climate;
import br.com.mathidios.starwarsplanets.model.Planet;
import br.com.mathidios.starwarsplanets.model.Terrain;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarWarsPlanetsApplication.class)
@AutoConfigureMockMvc
public class PlanetControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getNumberOfFilmsFromValidPlanetOnSWAPI() throws Exception {
		String nmPlanet = "Yavin IV";
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets/films/" + nmPlanet))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(this.contentType))
			.andExpect(MockMvcResultMatchers.jsonPath("$", Is.is(1)));
	}
	
	@Test
	public void getNumberOfFilmsFromInValidPlanetOnSWAPI() throws Exception {
		String nmPlanet = "Mathidios Planet";
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets/films/" + nmPlanet))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(this.contentType))
			.andExpect(MockMvcResultMatchers.jsonPath("$", Is.is(0)));
	}
	
	@Test
	public void createPlanetNotFoundInSWAPI() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/planets/create")
			.content(new Gson().toJson(this.getPlanetToCreateNotFoundInSWAPI()))
			.contentType(this.contentType)
			.accept(this.contentType))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.numberMovies", Is.is(0)));
	}
	
	private Planet getPlanetToCreateNotFoundInSWAPI() {
		Planet planet = new Planet();
		planet.setNmPlanet("Planet ZeroX");
		
		List<Climate> climateList = new ArrayList<Climate>();
		
		Climate climate1 = new Climate("Climate one");
		climateList.add(climate1);
		
		Climate climate2 = new Climate("Climate zero");
		climateList.add(climate2);
		
		planet.setClimateList(climateList);
		
		List<Terrain> terrainList = new ArrayList<Terrain>();
		
		Terrain terrain1 = new Terrain("Terrain one");
		terrainList.add(terrain1);
		
		Terrain terrain2 = new Terrain("Terrain zero");
		terrainList.add(terrain2);
		
		planet.setTerrainList(terrainList);
		
		return planet;
	}
	
	@Test
	public void createPlanetFoundInSWAPI() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/planets/create")
			.content(new Gson().toJson(this.getPlanetToCreateFoundInSWAPI()))
			.contentType(this.contentType)
			.accept(this.contentType))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.numberMovies", Is.is(1)));
	}
	
	private Planet getPlanetToCreateFoundInSWAPI() {
		Planet planet = new Planet();
		planet.setNmPlanet("Yavin IV");
		
		List<Climate> climateList = new ArrayList<Climate>();
		
		Climate climate1 = new Climate("Climate moon");
		climateList.add(climate1);
		
		Climate climate2 = new Climate("Climate mars");
		climateList.add(climate2);
		
		planet.setClimateList(climateList);
		
		List<Terrain> terrainList = new ArrayList<Terrain>();
		
		Terrain terrain1 = new Terrain("Terrain earth");
		terrainList.add(terrain1);
		
		Terrain terrain2 = new Terrain("Terrain beach");
		terrainList.add(terrain2);
		
		planet.setTerrainList(terrainList);
		
		return planet;
	}
	
	@Test
	public void findAllPlanets() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(this.contentType))
			.andExpect(MockMvcResultMatchers.jsonPath("$", IsNull.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(2)));
	}
	
	@Test
	public void findPlanetByName() throws Exception {
		String nmPlanet = "Zero";
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets/name/" + nmPlanet))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(this.contentType))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].numberMovies", Is.is(0)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].terrainList", IsNull.notNullValue()));
	}
	
	@Test
	public void findPlanetByInvalidName() throws Exception {
		String nmPlanet = "Zero_XXX";
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets/name/" + nmPlanet))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void findPlanetById() throws Exception {
		int idPlanet = 1;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets/" + idPlanet))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(this.contentType))
			.andExpect(MockMvcResultMatchers.jsonPath("$.nmPlanet", Is.is("Yavin IV")));
	}
	
	@Test
	public void findPlanetByInvalidId() throws Exception {
		int idPlanet = 999;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets/" + idPlanet))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void deletePlanetById() throws Exception {
		int idPlanet = 1;
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/planets/delete/" + idPlanet))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void findAllPlanetsAfterDeleting() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/planets"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(this.contentType))
			.andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(1)));
	}
	
	@Test
	public void deletePlanetByInvalidId() throws Exception {
		int idPlanet = 199;
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/planets/delete/" + idPlanet))
			.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}

}
