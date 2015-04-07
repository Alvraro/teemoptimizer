package es.raro.item;

public abstract class Item {
	protected String name;
	
	public int cost;
	
	// Stats bonus
	public float health;
	public float healthPerLevel;
	public float healthPercentage;
	public float healthRegen;
	public float healthRegenPerLevel;
	public float mana;
	public float manaPerLevel;
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
	public float energy;
	public float energyPerLevel;
	public float energyRegen;
	public float energyRegenPerLevel;
	public float cooldownReduction;
	public float cooldownReductionPerLevel;

	public Item(String name){
		this.name = name;
		
		cost = defineCost();
		health = defineHealth();
		healthPerLevel = defineHealthPerLevel();
		healthPercentage = defineHealthPercentage();
		healthRegen = defineHealthRegen();
		healthRegenPerLevel = defineHealthRegenPerLevel();
		mana = defineMana();
		manaPerLevel = defineManaPerLevel();
		manaRegen = defineManaRegen();
		manaRegenPerLevel = defineManaRegenPerLevel();
		damage = defineDamage();
		damagePerLevel = defineDamagePerLevel();
		attackSpeed = defineAttackSpeed();
		armor = defineArmor();
		armorPerLevel = defineArmorPerLevel();
		magicResist = defineMagicResist();
		magicResistPerLevel = defineMagicResistPerLevel();
		movementSpeed = defineMovementSpeed();
		percentageArmorPenetration = definePercentageArmorPenetration();
		flatArmorPenetration = defineFlatArmorPenetration();
		criticalChance = defineCriticalChance();
		criticalBonusDamage = defineCriticalBonusDamage();
		abilityPower = defineAbilityPower();
		abilityPowerPerLevel = defineAbilityPowerPerLevel();
		magicPenetration = defineMagicPenetration();
		lifeSteal = defineLifeSteal();
		spellVamp = defineSpellVamp();
		energy = defineEnergy();
		energyPerLevel = defineEnergyPerLevel();
		energyRegen = defineEnergyRegen();
		energyRegenPerLevel = defineEnergyRegenPerLevel();
		cooldownReduction = defineCooldownReduction();
		cooldownReductionPerLevel = defineCooldownReductionPerLevel();
	}

	protected abstract int defineCost();

	protected abstract float defineHealth();
	
	protected abstract float defineHealthPerLevel();
	
	protected abstract float defineHealthPercentage();
	
	protected abstract float defineHealthRegen();
	
	protected abstract float defineHealthRegenPerLevel();
	
	protected abstract float defineMana();
	
	protected abstract float defineManaPerLevel();
	
	protected abstract float defineManaRegen();
	
	protected abstract float defineManaRegenPerLevel();
	
	protected abstract float defineDamage();
	
	protected abstract float defineDamagePerLevel();
	
	protected abstract float defineAttackSpeed();
	
	protected abstract float defineArmor();
	
	protected abstract float defineArmorPerLevel();
	
	protected abstract float defineMagicResist();
	
	protected abstract float defineMagicResistPerLevel();
	
	protected abstract float defineMovementSpeed();
	
	protected abstract float definePercentageArmorPenetration();
	
	protected abstract float defineFlatArmorPenetration();
	
	protected abstract float defineCriticalChance();
	
	protected abstract float defineCriticalBonusDamage();
	
	protected abstract float defineAbilityPower();
	
	protected abstract float defineAbilityPowerPerLevel();
	
	protected abstract float defineMagicPenetration();
	
	protected abstract float defineLifeSteal();
	
	protected abstract float defineSpellVamp();
	
	protected abstract float defineEnergy();
	
	protected abstract float defineEnergyPerLevel();
	
	protected abstract float defineEnergyRegen();
	
	protected abstract float defineEnergyRegenPerLevel();
	
	protected abstract float defineCooldownReduction();
	
	protected abstract float defineCooldownReductionPerLevel();
}
