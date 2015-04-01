package es.raro.rune;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

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
	public float percentageArmorPenetration;
	public float flatArmorPenetration;
	public float criticalChance;
	public float criticalBonusDamage;
	public float abilityPower;
	public float abilityPowerPerLevel;
	public float magicPenetration;
	public float lifeSteal;
	public float spellVamp;
	public float revival;
	public float goldGain;
	public float experienceGain;
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
		percentageArmorPenetration = 0;
		flatArmorPenetration = 0;
		criticalChance = 0;
		criticalBonusDamage = 0;
		abilityPower = 0;
		abilityPowerPerLevel = 0;
		magicPenetration = 0;
		lifeSteal = 0;
		spellVamp = 0;
		revival = 0;
		goldGain = 0;
		experienceGain = 0;
		energy = 0;
		energyPerLevel = 0;
		energyRegen = 0;
		energyRegenPerLevel = 0;
		cooldownReduction = 0;
		cooldownReductionPerLevel = 0;	
	}

	public Rune(int health, int healthPerLevel, int healthPercentage,
			float healthRegen, float healthRegenPerLevel, int mana,
			int manaPerLevel, float manaRegen, float manaRegenPerLevel,
			float damage, float damagePerLevel, float attackSpeed, float armor,
			float armorPerLevel, float magicResist, float magicResistPerLevel,
			float movementSpeed, float percentageArmorPenetration,
			float flatArmorPenetration, float criticalChance,
			float criticalBonusDamage, float abilityPower,
			float abilityPowerPerLevel, float magicPenetration,
			float lifeSteal, float spellVamp, float revival, float gold,
			float experience, float energy, float energyPerLevel,
			float energyRegen, float energyRegenPerLevel,
			float cooldownReduction, float cooldownReductionPerLevel) {
		super();
		this.health = health;
		this.healthPerLevel = healthPerLevel;
		this.healthPercentage = healthPercentage;
		this.healthRegen = healthRegen;
		this.healthRegenPerLevel = healthRegenPerLevel;
		this.mana = mana;
		this.manaPerLevel = manaPerLevel;
		this.manaRegen = manaRegen;
		this.manaRegenPerLevel = manaRegenPerLevel;
		this.damage = damage;
		this.damagePerLevel = damagePerLevel;
		this.attackSpeed = attackSpeed;
		this.armor = armor;
		this.armorPerLevel = armorPerLevel;
		this.magicResist = magicResist;
		this.magicResistPerLevel = magicResistPerLevel;
		this.movementSpeed = movementSpeed;
		this.percentageArmorPenetration = percentageArmorPenetration;
		this.flatArmorPenetration = flatArmorPenetration;
		this.criticalChance = criticalChance;
		this.criticalBonusDamage = criticalBonusDamage;
		this.abilityPower = abilityPower;
		this.abilityPowerPerLevel = abilityPowerPerLevel;
		this.magicPenetration = magicPenetration;
		this.lifeSteal = lifeSteal;
		this.spellVamp = spellVamp;
		this.revival = revival;
		this.goldGain = gold;
		this.experienceGain = experience;
		this.energy = energy;
		this.energyPerLevel = energyPerLevel;
		this.energyRegen = energyRegen;
		this.energyRegenPerLevel = energyRegenPerLevel;
		this.cooldownReduction = cooldownReduction;
		this.cooldownReductionPerLevel = cooldownReductionPerLevel;
	}
}
