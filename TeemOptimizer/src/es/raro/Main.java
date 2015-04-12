package es.raro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.regex.Pattern;

import es.raro.champion.Champion;
import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.model.Teemodel;
import es.raro.rune.Rune;
import es.raro.skill.Skill;

public class Main {
	private static final String MAIN_SEPARATOR = "+";
	private static final String SECONDARY_SEPARATOR = "*";

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Properties properties = new Properties();
		properties.load(new FileReader("config.properties"));
		
		Teemodel.initializeRiotAPI(properties.getProperty("key"), properties.getProperty("region"));
		
		int seed = Integer.parseInt(properties.getProperty("seed"));
		
		// Attacker config
		Champion attacker = configureChampion("attacker", properties);
		
		// Defender config
		Champion defender = configureChampion("defender", properties);
		
		int duration = Integer.parseInt(properties.getProperty("simulation.duration"));
		
		Teemodel model = new Teemodel(seed, attacker, defender, duration);
		model.start();
		
		while(model.schedule.step(model)){
			//System.out.println(model.schedule.getTime());
		}
	}

	private static Champion configureChampion(String prefix, Properties properties) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, FileNotFoundException, NoSuchFieldException, IOException {
		// Champion
		int attackerLevel = Integer.parseInt(properties.getProperty(prefix+".level")); 
		String attackerName = properties.getProperty(prefix+".champion");
		Champion champion = Teemodel.getChampionInstance(attackerName, attackerLevel);

		// Skills
		String skillsProperty = properties.getProperty(prefix+".skills");
		if(skillsProperty!=null){
			for(String skillDefinition : skillsProperty.split(Pattern.quote(MAIN_SEPARATOR))){
				String[] aux = skillDefinition.split(Pattern.quote(SECONDARY_SEPARATOR));
				int offset = 0;
				int skillLevel = Integer.parseInt(aux[offset++]);
				String skillName = aux[offset++];
				Skill skill = Teemodel.getSkillInstance(skillName, skillLevel, champion);
				champion.addSkill(skill);
			}
		}

		// Masteries
		String masteriesProperty = properties.getProperty(prefix+".masteries");
		if(masteriesProperty!=null){
			for(String masteryDefinition : masteriesProperty.split(Pattern.quote(MAIN_SEPARATOR))){
				String[] aux = masteryDefinition.split(Pattern.quote(SECONDARY_SEPARATOR));
				int offset = 0;
				int masteryLevel = Integer.parseInt(aux[offset++]);
				String masteryName = aux[offset++];
				Mastery mastery = Teemodel.getMasteryInstance(masteryName, masteryLevel);
				champion.addMastery(mastery);
			}
		}
		
		// Runes
		String runesProperty = properties.getProperty(prefix+".runes");
		if(runesProperty!=null){
			for(String runeDefinition : runesProperty.split(Pattern.quote(MAIN_SEPARATOR))){
				String[] aux = runeDefinition.split(Pattern.quote(SECONDARY_SEPARATOR));
				int offset = 0;
				int runeNumber = Integer.parseInt(aux[offset++]);
				String runeType = aux[offset++];
				String runeName = aux[offset++];
			
				for(int i=0; i<runeNumber; i++){
					Rune rune = Teemodel.getRuneInstance(runeName, runeType);				
					champion.addRune(rune);
				}
			}
		}
		
		// Items
		String itemsProperty = properties.getProperty(prefix+".items");
		if(itemsProperty!=null){
			for(String itemDefinition : itemsProperty.split(Pattern.quote(MAIN_SEPARATOR))){
				String[] aux = itemDefinition.split(Pattern.quote(SECONDARY_SEPARATOR));
				int offset = 0;
				int itemNumber = Integer.parseInt(aux[offset++]);
				String itemName = aux[offset++];
				
				for(int i=0; i<itemNumber; i++){
					Item item = Teemodel.getItemInstance(itemName);
					champion.addItem(item);
				}
			}
		}
		
		String autoAttackProperty = properties.getProperty(prefix+".autoattack");
		boolean autoAttack = true;
		if(autoAttackProperty!=null){
			autoAttack = Boolean.parseBoolean(autoAttackProperty);
		}
		champion.makeAutoAttacks(autoAttack);
		
		return champion;
	}
}
