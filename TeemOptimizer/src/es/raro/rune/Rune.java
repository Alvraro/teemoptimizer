package es.raro.rune;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

// TODO Implement runes as items to make use of the Riot API 
public class Rune {
	private static final String RUNE_PROPERTIES_FILE = "runes.properties";

	public static Properties runeProperties = null;
	
	public int health;
	public int healthPerLevel;
	public int healthPercentage;
	public float healthRegen;
	public float healthRegenPerLevel;
	public int mana;
	public int manaPerLevel;
	public float manaRegen;
	public float manaRegenPerLevel;
	public float damage;
	public float damagePerLevel;
	public float attackSpeed;
	public float armor;
	public float armorPerLevel;
	public float magicResist;
	public float magicResistPerLevel;
	public float movementSpeed;
	public float flatArmorPenetration;
	public float criticalChance;
	public float criticalBonusDamage;
	public float abilityPower;
	public float abilityPowerPerLevel;
	public float magicPenetration;
	public float lifeSteal;
	public float spellVamp;
	public float revival;
	public float goldEarn;
	public float experienceEarn;
	public float energy;
	public float energyPerLevel;
	public float energyRegen;
	public float energyRegenPerLevel;
	public float cooldownReduction;
	public float cooldownReductionPerLevel;

	public Rune(RuneStatType runeStatType, RuneType runeType) throws FileNotFoundException, IOException, NoSuchFieldException, SecurityException, NumberFormatException, IllegalArgumentException, IllegalAccessException{
		if(runeProperties==null){
			runeProperties = new Properties();
			runeProperties.load(new FileReader(RUNE_PROPERTIES_FILE));
		}
		
		// Get the value from the properties
		String value = runeProperties.getProperty(runeStatType.name()+"."+runeType.name());
		
		// Assign to the right field
		Field field = this.getClass().getField(runeStatType.name());
		if(field.getType().getName().equals("int"))
			field.setInt(this, Integer.parseInt(value));
		else if(field.getType().getName().equals("float"))
			field.setFloat(this, Float.parseFloat(value));
		else
			throw new IllegalArgumentException("Unexpected field type name '"+field.getType().getName()+"'");
		
		// Transform [0,100] percentages into [0,1]
		attackSpeed/=100;
		cooldownReduction/=100;
		cooldownReductionPerLevel/=100;
		criticalChance/=100;
		criticalBonusDamage/=100;
		healthPercentage/=100;
		lifeSteal/=100;
		movementSpeed/=100;
		revival/=100;
		spellVamp/=100;
	}
	
	public Rune(){
		health = 0;
		healthPerLevel = 0;
		healthPercentage = 0;
		healthRegen = 0;
		healthRegenPerLevel = 0;
		mana = 0;
		manaPerLevel = 0;
		manaRegen = 0;
		manaRegenPerLevel = 0;
		damage = 0;
		damagePerLevel = 0;
		attackSpeed = 0;
		armor = 0;
		armorPerLevel = 0;
		magicResist = 0;
		magicResistPerLevel = 0;
		movementSpeed = 0;
		flatArmorPenetration = 0;
		criticalChance = 0;
		criticalBonusDamage = 0;
		abilityPower = 0;
		abilityPowerPerLevel = 0;
		magicPenetration = 0;
		lifeSteal = 0;
		spellVamp = 0;
		revival = 0;
		goldEarn = 0;
		experienceEarn = 0;
		energy = 0;
		energyPerLevel = 0;
		energyRegen = 0;
		energyRegenPerLevel = 0;
		cooldownReduction = 0;
		cooldownReductionPerLevel = 0;	
	}
}
