package br.com.mathidios.starwarsplanets.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="PLANET")
public class Planet {
	
	@Id
	@Column(name = "ID_PLANET")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPlanet;
	
	@Column(name = "NM_PLANET")
	private String nmPlanet;
	
	@Column(name = "NUMBER_MOVIES")
	private Integer numberMovies;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Climate> climateList;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Terrain> terrainList;

	public Long getIdPlanet() {
		return idPlanet;
	}

	public void setIdPlanet(Long idPlanet) {
		this.idPlanet = idPlanet;
	}

	public String getNmPlanet() {
		return nmPlanet;
	}

	public void setNmPlanet(String nmPlanet) {
		this.nmPlanet = nmPlanet;
	}

	public Integer getNumberMovies() {
		return numberMovies;
	}

	public void setNumberMovies(Integer numberMovies) {
		this.numberMovies = numberMovies;
	}

	public List<Climate> getClimateList() {
		return climateList;
	}

	public void setClimateList(List<Climate> climateList) {
		this.climateList = climateList;
	}

	public List<Terrain> getTerrainList() {
		return terrainList;
	}

	public void setTerrainList(List<Terrain> terrainList) {
		this.terrainList = terrainList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climateList == null) ? 0 : climateList.hashCode());
		result = prime * result + ((idPlanet == null) ? 0 : idPlanet.hashCode());
		result = prime * result + ((nmPlanet == null) ? 0 : nmPlanet.hashCode());
		result = prime * result + ((numberMovies == null) ? 0 : numberMovies.hashCode());
		result = prime * result + ((terrainList == null) ? 0 : terrainList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (climateList == null) {
			if (other.climateList != null)
				return false;
		} else if (!climateList.equals(other.climateList))
			return false;
		if (idPlanet == null) {
			if (other.idPlanet != null)
				return false;
		} else if (!idPlanet.equals(other.idPlanet))
			return false;
		if (nmPlanet == null) {
			if (other.nmPlanet != null)
				return false;
		} else if (!nmPlanet.equals(other.nmPlanet))
			return false;
		if (numberMovies == null) {
			if (other.numberMovies != null)
				return false;
		} else if (!numberMovies.equals(other.numberMovies))
			return false;
		if (terrainList == null) {
			if (other.terrainList != null)
				return false;
		} else if (!terrainList.equals(other.terrainList))
			return false;
		return true;
	}

}
