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
@Table(name="CLIMATE")
public class Climate {

	@Id
	@Column(name = "ID_CLIMATE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idClimate;
	
	@Column(name = "NM_CLIMATE")
	private String nmClimate;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLANET")
	private Planet planet;
	
	public Climate() {
		
	}
	
	public Climate(String nmClimate) {
		this.nmClimate = nmClimate;
	}

	public Long getIdClimate() {
		return idClimate;
	}

	public void setIdClimate(Long idClimate) {
		this.idClimate = idClimate;
	}

	public String getNmClimate() {
		return nmClimate;
	}

	public void setNmClimate(String nmClimate) {
		this.nmClimate = nmClimate;
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
		result = prime * result + ((idClimate == null) ? 0 : idClimate.hashCode());
		result = prime * result + ((nmClimate == null) ? 0 : nmClimate.hashCode());
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
		Climate other = (Climate) obj;
		if (idClimate == null) {
			if (other.idClimate != null)
				return false;
		} else if (!idClimate.equals(other.idClimate))
			return false;
		if (nmClimate == null) {
			if (other.nmClimate != null)
				return false;
		} else if (!nmClimate.equals(other.nmClimate))
			return false;
		if (planet == null) {
			if (other.planet != null)
				return false;
		} else if (!planet.equals(other.planet))
			return false;
		return true;
	}
	
}
