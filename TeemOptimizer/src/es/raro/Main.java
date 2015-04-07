package es.raro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;

import es.raro.champion.Champion;
import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;
import es.raro.skill.Skill;

public class Main {
	private static final String MAIN_SEPARATOR = "+";
	private static final String SECONDARY_SEPARATOR = "*";

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Properties properties = new Properties();
		properties.load(new FileReader("config.properties"));
		
		initializeRiotAPI(properties.getProperty("key"), properties.getProperty("region"));
		
		int seed = Integer.parseInt(properties.getProperty("seed"));
		
		// Attacker config
		int attackerLevel = Integer.parseInt(properties.getProperty("attacker.level")); 

		ArrayList<Mastery> masteries = new ArrayList<Mastery>();
		ArrayList<Rune> runes = new ArrayList<Rune>();
		ArrayList<Item> items = new ArrayList<Item>();
		
		String attackerName = properties.getProperty("attacker.champion");
		Champion attacker = attackerClass.getConstructor(championConstructorTypes).newInstance(new Object[]{attackerLevel, masteries, runes, items});

		Class[] skillConstructorTypes = new Class[]{Champion.class, Integer.class};
		ArrayList<Skill> skills = new ArrayList<Skill>();
		for(String skillDefinition : properties.getProperty("attacker.skills").split(Pattern.quote(MAIN_SEPARATOR))){
			String[] aux = skillDefinition.split(Pattern.quote(SECONDARY_SEPARATOR));
			int skillLevel = Integer.parseInt(aux[0]);
			String skillName = aux[1];
			Class<Skill> skillClass = (Class<Skill>) Class.forName(Skill.class.getPackage().getName()+"."+skillName);
			Skill skill = skillClass.getConstructor(skillConstructorTypes).newInstance(attacker, skillLevel);
			skills.add(skill);
		}
		attacker.setSkills(skills);
		
		// Defender config
		String defenderClassName = properties.getProperty("defender.champion");
		Class<Champion> defenderClass = (Class<Champion>) Class.forName(Champion.class.getPackage().getName()+"."+defenderClassName);
		Champion defender = defenderClass.newInstance();
		
		// TODO
		throw new IllegalAccessError();
		//Teemodel model = new Teemodel(seed, attacker, defender);
		//model.start();
	}

	private static void initializeRiotAPI(String key, String region) {
        RiotAPI.setMirror(Region.valueOf(region));
        RiotAPI.setRegion(Region.valueOf(region));
        RiotAPI.setAPIKey(key);
	}
}
