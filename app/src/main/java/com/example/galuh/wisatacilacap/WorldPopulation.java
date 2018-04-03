package com.example.galuh.wisatacilacap;

public class WorldPopulation {
	private String rank;
	private String country;
	private String population;

	public WorldPopulation(String rank, String country) {
		this.rank = rank;
		this.country = country;
		//this.population = population;
	}

	public String getRank() {
		return this.rank;
	}

	public String getCountry() {
		return this.country;
	}


}
