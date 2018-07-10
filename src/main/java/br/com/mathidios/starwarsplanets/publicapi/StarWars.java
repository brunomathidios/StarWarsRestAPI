package br.com.mathidios.starwarsplanets.publicapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWars {

    @GET("planets/")
    Call<SWModelList<PlanetPublicAPI>> getAllPlanets(@Query("page") Integer page);

    @GET("planets/{id}/")
    Call<PlanetPublicAPI> getPlanetById(@Path("id") int planetId);
    
    @GET("planets/{name}/")
    Call<SWModelList<PlanetPublicAPI>> getPlanetByName(@Query("name") String planetName);
}
