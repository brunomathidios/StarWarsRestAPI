package br.com.mathidios.starwarsplanets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="TERRAIN")
public class Terrain {

	@Id
	@Column(name = "ID_TERRAIN")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTerrain;
	
	@Column(name = "NM_TERRAIN")
	private String nmTerrain;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLANET")
	private Planet planet;

	public Long getIdTerrain() {
		return idTerrain;
	}

	public void setIdTerrain(Long idTerrain) {
		this.idTerrain = idTerrain;
	}

	public String getNmTerrain() {
		return nmTerrain;
	}

	public void setNmTerrain(String nmTerrain) {
		this.nmTerrain = nmTerrain;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTerrain == null) ? 0 : idTerrain.hashCode());
		result = prime * result + ((nmTerrain == null) ? 0 : nmTerrain.hashCode());
		result = prime * result + ((planet == null) ? 0 : planet.hashCode());
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
		Terrain other = (Terrain) obj;
		if (idTerrain == null) {
			if (other.idTerrain != null)
				return false;
		} else if (!idTerrain.equals(other.idTerrain))
			return false;
		if (nmTerrain == null) {
			if (other.nmTerrain != null)
				return false;
		} else if (!nmTerrain.equals(other.nmTerrain))
			return false;
		if (planet == null) {
			if (other.planet != null)
				return false;
		} else if (!planet.equals(other.planet))
			return false;
		return true;
	}
	
}
