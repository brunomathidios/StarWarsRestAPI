package br.com.mathidios.starwarsplanets.swapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StarWars {

    @GET("planets/")
    Call<SWModel> getPlanetByName(@Query("search") String planetName);
}
