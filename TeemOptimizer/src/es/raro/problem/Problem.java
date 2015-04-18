package es.raro.problem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import es.raro.model.Teemodel;

public abstract class Problem {
	protected Properties properties;
	protected int seed;

	public Problem(Properties properties) throws FileNotFoundException, IOException {
		this.properties = properties;
		Teemodel.initializeRiotAPI(properties.getProperty("key"), properties.getProperty("region"));
		seed = Integer.parseInt(properties.getProperty("seed"));
	}
}
