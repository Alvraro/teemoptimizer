package es.raro.champion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import sim.engine.SimState;
import sim.engine.Steppable;
import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.model.Teemodel;
import es.raro.rune.Rune;
import es.raro.skill.Skill;

public abstract class Champion implements Steppable {
	private static final float MAX_ATTACK_SPEED = 2.5f;
	private static final float MAX_COOLDOWN_REDUCTION = 0.4f;

	protected ArrayList<Skill> skills;
	protected ArrayList<Mastery> masteries;
	protected ArrayList<Rune> runes;
	protected ArrayList<Item> items;

	protected boolean makeAutoAttacks = true;

	/** Last champion targeted */
	protected Champion lastTarget;

	/** Whether the champion is enabled or not */
	protected boolean isEnabled;

	/** Whether the champion can attack */
	protected boolean attackReady;

	/** Whether the champion can use skills */
	protected boolean skillsReady;

	protected int level;

	protected String name;

	// Attributes
	protected float health;
	protected float healthRegen;
	protected float mana;
	protected float manaRegen;
	protected float damage;
	/** Attacks per second */
	protected float attackSpeed;
	protected float bonusAttackSpeed;
	protected float armor;
	protected float armorGain;
	protected float magicResist;
	protected float movementSpeed;
	protected float range;
	/** 0-1 rate of armor ignored */
	protected float armorReduction;
	protected float percentageArmorPenetration;
	protected float flatArmorPenetration;
	protected float magicResistReduction;
	/** 0-1 rate of magic resistance ignored */
	protected float percentageMagicPenetration;
	protected float flatMagicPenetration;
	/** 0-1 probability of getting a critical hit */
	protected float criticalChance;
	/** 0-1 probability of increased damage on critical hit (over the basic 1) */
	protected float criticalBonusDamage;
	protected float abilityPower;
	protected float magicPenetration;
	protected float lifeSteal;
	protected float spellVamp;
	protected float energy;
	protected float energyRegen;
	/** 0-1 rate by which skill cooldown is reduced */
	protected float cooldownReduction;

	// Base attributes/gains
	protected float baseHealth;
	protected float healthGain;
	protected float baseHealthRegen;
	protected float healthRegenGain;
	protected float baseMana;
	protected float manaGain;
	protected float baseManaRegen;
	protected float manaRegenGain;
	protected float baseDamage;
	protected float damageGain;
	protected float baseAttackSpeed;
	protected float attackSpeedGain;
	protected float baseArmor;
	protected float baseMagicResist;
	protected float magicResistGain;
	protected float baseMovementSpeed;
	protected float baseRange;
	protected float baseArmorReduction;
	protected float basePercentageArmorPenetration;
	protected float baseFlatArmorPenetration;
	protected float baseCriticalChance;
	protected float baseCriticalBonusDamage;

	protected float remainingHealth;
	protected float remainingMana;

	protected HashSet<Skill> uniqueSkills;
	protected HashSet<Item> uniqueEffects;
	
	public Champion(String name, int level) {
		this.name = name;
		this.level = level;
		this.skills = new ArrayList<Skill>();
		this.masteries = new ArrayList<Mastery>();
		this.runes = new ArrayList<Rune>();
		this.items = new ArrayList<Item>();
		this.uniqueSkills = new HashSet<Skill>();
		this.uniqueEffects = new HashSet<Item>();

		this.baseHealth = defineBaseHealth();
		this.healthGain = defineHealthGain();
		this.baseHealthRegen = defineBaseHealthRegen();
		this.healthRegenGain = defineHealthRegenGain();
		this.baseMana = defineBaseMana();
		this.manaGain = defineManaGain();
		this.baseManaRegen = defineBaseManaRegen();
		this.manaRegenGain = defineManaRegenGain();
		this.baseDamage = defineBaseDamage();
		this.damageGain = defineDamageGain();
		this.baseAttackSpeed = defineBaseAttackSpeed();
		this.attackSpeedGain = defineAttackSpeedGain();
		this.bonusAttackSpeed = 0;
		this.baseArmor = defineBaseArmor();
		this.armorGain = defineArmorGain();
		this.baseMagicResist = defineBaseMagicResist();
		this.magicResistGain = defineMagicResistGain();
		this.baseMovementSpeed = defineBaseMovementSpeed();
		this.baseRange = defineBaseRange();
		this.baseArmorReduction = 0;
		this.basePercentageArmorPenetration = 0;
		this.baseFlatArmorPenetration = 0;
		this.baseCriticalChance = 0;
		this.baseCriticalBonusDamage = 0;

		restart();
	}

	protected abstract float defineBaseHealth();
	protected abstract float defineHealthGain();
	protected abstract float defineBaseHealthRegen();
	protected abstract float defineHealthRegenGain();
	protected abstract float defineBaseMana();
	protected abstract float defineManaGain();
	protected abstract float defineBaseManaRegen();
	protected abstract float defineManaRegenGain();
	protected abstract float defineBaseDamage();
	protected abstract float defineDamageGain();
	protected abstract float defineBaseAttackSpeed();
	protected abstract float defineAttackSpeedGain();
	protected abstract float defineBaseArmor();
	protected abstract float defineArmorGain();
	protected abstract float defineBaseMagicResist();
	protected abstract float defineMagicResistGain();
	protected abstract float defineBaseMovementSpeed();
	protected abstract float defineBaseRange();

	public void restart(){
		lastTarget = null;

		isEnabled = true;
		attackReady = true;
		skillsReady = true;

		health = baseHealth + increaseStat(healthGain, level);
		healthRegen = baseHealthRegen + increaseStat(healthRegenGain, level);
		mana = baseMana + increaseStat(manaGain, level);
		manaRegen = baseManaRegen + increaseStat(manaRegenGain, level);
		damage = baseDamage + increaseStat(damageGain, level);
		bonusAttackSpeed = increaseStat(attackSpeedGain, level);
		armor = baseArmor + increaseStat(armorGain, level);
		magicResist = baseMagicResist + increaseStat(magicResistGain, level);
		movementSpeed = baseMovementSpeed;
		range = baseRange;
		armorReduction = baseArmorReduction;
		percentageArmorPenetration = basePercentageArmorPenetration;
		flatArmorPenetration = baseFlatArmorPenetration;
		criticalChance = baseCriticalChance;
		criticalBonusDamage = baseCriticalBonusDamage;

		applySkillsBonuses(skills);
		applyMasteriesBonuses(masteries);
		applyRunesBonuses(runes);
		applyItemsBonuses(items);
		
		applySkillsBonuses(uniqueSkills);
		applyItemsBonuses(uniqueEffects);

		// Get attackSpeed final value
		attackSpeed = baseAttackSpeed * (100f + bonusAttackSpeed) / 100f; 

		if(attackSpeed > MAX_ATTACK_SPEED)
			attackSpeed = MAX_ATTACK_SPEED;

		if(cooldownReduction > MAX_COOLDOWN_REDUCTION)
			cooldownReduction = MAX_COOLDOWN_REDUCTION;

		remainingHealth = health;
		remainingMana = mana;
	}

	private float increaseStat(float gain, int level){
		if(gain>0 && level>0)
			return gain * (7f * level * level / 400f + 267f *level / 400 - 137f / 200f);
		else
			return 0;
	}
	
	private void applySkillsBonuses(Collection<Skill> skills){
		for(Skill skill : skills){
			applySkillBonuses(skill);
		}
	}
		
	private void applySkillBonuses(Skill skill) {
		health+=skill.health;
		health+=level*skill.healthPerLevel;
		//healthPercentage+=item.healthPercentage; TODO health percentage
		healthRegen+=skill.healthRegen;
		healthRegen+=level*skill.healthRegenPerLevel;
		mana+=skill.mana;
		mana+=level*skill.manaPerLevel;
		manaRegen+=skill.manaRegen;
		manaRegen+=level*skill.manaRegenPerLevel;
		damage+=skill.damage;
		damage+=level*skill.damagePerLevel;
		bonusAttackSpeed+=skill.attackSpeed;
		armor+=skill.armor;
		armor+=level*skill.armorPerLevel;
		magicResist+=skill.magicResist;
		magicResist+=level*skill.magicResistPerLevel;
		movementSpeed+=skill.movementSpeed; // TODO flat vs raw
		percentageArmorPenetration+=skill.percentageArmorPenetration;
		flatArmorPenetration+=skill.flatArmorPenetration;
		criticalChance+=skill.criticalChance;
		criticalBonusDamage+=skill.criticalBonusDamage;
		abilityPower+=skill.abilityPower;
		abilityPower+=level*skill.abilityPowerPerLevel;
		magicPenetration+=skill.magicPenetration;
		lifeSteal+=skill.lifeSteal;
		spellVamp+=skill.spellVamp;
		energy+=skill.energy;
		energy+=level*skill.energyPerLevel;
		energyRegen+=skill.energyRegen;
		energyRegen+=level*skill.energyRegenPerLevel;
		cooldownReduction+=skill.cooldownReduction;
		cooldownReduction+=level*skill.cooldownReductionPerLevel;
	}

	private void applyMasteriesBonuses(Collection<Mastery> masteries) {
		for(Mastery mastery : masteries){
			applyMasteryBonuses(mastery);	
		}
	}	

	private void applyMasteryBonuses(Mastery mastery) {
		health+=mastery.getHealth();
		healthRegen+=mastery.getHealthRegen();
		mana+=mastery.getMana();
		manaRegen+=mastery.getManaRegen();
		damage+=mastery.getDamage();
		bonusAttackSpeed+=mastery.getAttackSpeed();
		armor+=mastery.getArmor();
		magicResist+=mastery.getMagicResist();
		movementSpeed+=mastery.getMovementSpeed(); // TODO flat vs raw
		range+=mastery.getRange();
		armorReduction+=mastery.getArmorReduction();
		percentageArmorPenetration+=mastery.getPercentageArmorPenetration();
		flatArmorPenetration+=mastery.getFlatArmorPenetration();
		criticalChance+=mastery.getCriticalChance();
		criticalBonusDamage+=mastery.getCriticalBonusDamage();
	}

	private void applyRunesBonuses(ArrayList<Rune> runes) {
		for(Rune rune : runes){
			applyRuneBonuses(rune);
		}
	}

	private void applyRuneBonuses(Rune rune) {
		health+=rune.health;
		health+=level*rune.healthPerLevel;
		//healthPercentage+=rune.healthPercentage; TODO health percentage
		healthRegen+=rune.healthRegen;
		healthRegen+=level*rune.healthRegenPerLevel;
		mana+=rune.mana;
		mana+=level*rune.manaPerLevel;
		manaRegen+=rune.manaRegen;
		manaRegen+=level*rune.manaRegenPerLevel;
		damage+=rune.damage;
		damage+=level*rune.damagePerLevel;
		bonusAttackSpeed+=rune.attackSpeed;
		armor+=rune.armor;
		armor+=level*rune.armorPerLevel;
		magicResist+=rune.magicResist;
		magicResist+=level*rune.magicResistPerLevel;
		movementSpeed+=rune.movementSpeed; // TODO flat vs raw
		flatArmorPenetration+=rune.flatArmorPenetration;
		criticalChance+=rune.criticalChance;
		criticalBonusDamage+=rune.criticalBonusDamage;
		abilityPower+=rune.abilityPower;
		abilityPower+=level*rune.abilityPowerPerLevel;
		magicPenetration+=rune.magicPenetration;
		lifeSteal+=rune.lifeSteal;
		spellVamp+=rune.spellVamp;
		energy+=rune.energy;
		energy+=level*rune.energyPerLevel;
		energyRegen+=rune.energyRegen;
		energyRegen+=level*rune.energyRegenPerLevel;
		cooldownReduction+=rune.cooldownReduction;
		cooldownReduction+=level*rune.cooldownReductionPerLevel;
	}

	private void applyItemsBonuses(Collection<Item> items) {
		for(Item item : items){
			applyItemBonuses(item);
		}
	}

	private void applyItemBonuses(Item item) {
		health+=item.health;
		health+=level*item.healthPerLevel;
		//healthPercentage+=item.healthPercentage; TODO health percentage
		healthRegen+=item.healthRegen;
		healthRegen+=level*item.healthRegenPerLevel;
		mana+=item.mana;
		mana+=level*item.manaPerLevel;
		manaRegen+=item.manaRegen;
		manaRegen+=level*item.manaRegenPerLevel;
		damage+=item.damage;
		damage+=level*item.damagePerLevel;
		bonusAttackSpeed+=item.attackSpeed;
		armor+=item.armor;
		armor+=level*item.armorPerLevel;
		magicResist+=item.magicResist;
		magicResist+=level*item.magicResistPerLevel;
		movementSpeed+=item.movementSpeed; // TODO flat vs raw
		percentageArmorPenetration+=item.percentageArmorPenetration;
		flatArmorPenetration+=item.flatArmorPenetration;
		criticalChance+=item.criticalChance;
		criticalBonusDamage+=item.criticalBonusDamage;
		abilityPower+=item.abilityPower;
		abilityPower+=level*item.abilityPowerPerLevel;
		magicPenetration+=item.magicPenetration;
		lifeSteal+=item.lifeSteal;
		spellVamp+=item.spellVamp;
		energy+=item.energy;
		energy+=level*item.energyPerLevel;
		energyRegen+=item.energyRegen;
		energyRegen+=level*item.energyRegenPerLevel;
		cooldownReduction+=item.cooldownReduction;
		cooldownReduction+=level*item.cooldownReductionPerLevel;
	}	

	@Override
	public void step(SimState state) {
		Teemodel model = (Teemodel)state;

		Champion target = chooseTarget(model);

		// Check if we can do anything
		if(!isEnabled()){
			return;
		}

		// Use one random ready skill
		if(canUseSkills()){
			Collections.shuffle(skills);
			for(Skill skill : skills){
				if(skill.isReady()){
					useSkill(model, skill, target);
					return;
				}
			}
		}

		// Else, attack if possible and we want to
		if(canAttack()){
			attack(model, target);
			return;
		}
	}

	protected boolean canAttack() {
		return makeAutoAttacks && attackReady && isEnabled();
	}

	protected boolean canUseSkills() {
		return skillsReady && isEnabled();
	}

	protected boolean isEnabled() {
		return isEnabled;
	}

	protected Champion chooseTarget(Teemodel model) {
		return (model.getDefender());
	}

	protected void useSkill(Teemodel model, Skill s, Champion target) {
		s.use(model, target);

		// Skills are not ready after using it
		skillsReady = false;

		// After attack there is a global cooldown for all skills
		startGlobalCooldown(model);
	}

	protected void attack(Teemodel model, Champion target) {
		// Do damage
		float damageDone = Teemodel.calculatePhysicalDamage(this, target, null);
		target.receiveDamage(damageDone);

		// Attack is not ready after using it
		attackReady = false;

		// Schedule event to reactivate skill after the cooldown
		model.schedule.scheduleOnce(model.schedule.getTime() + 1.0 / getAttackSpeed(), new Steppable() {
			@Override
			public void step(SimState state) {
				attackReady = true;
			}
		});

		// After attack there is a global cooldown
		startGlobalCooldown(model);
	}

	public void receiveDamage(float damageDone) {
		System.out.println(this+" received damage="+damageDone);
		remainingHealth -= damageDone;
	}

	private void startGlobalCooldown(Teemodel model) {
		model.schedule.scheduleOnce(model.schedule.getTime() + Teemodel.GLOBAL_COOLDOWN, new Steppable() {
			@Override
			public void step(SimState state) {
				skillsReady = true;
			}
		});
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public ArrayList<Mastery> getMasteries() {
		return masteries;
	}

	public ArrayList<Rune> getRunes() {
		return runes;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public boolean isAttackReady() {
		return attackReady;
	}

	public float getHealth() {
		return health;
	}

	public float getHealthRegen() {
		return healthRegen;
	}

	public float getMana() {
		return mana;
	}

	public float getManaRegen() {
		return manaRegen;
	}

	public float getDamage() {
		return damage;
	}

	public float getAttackSpeed() {
		return attackSpeed;
	}

	public float getArmor() {
		return armor;
	}

	public float getMagicResist() {
		return magicResist;
	}

	public float getMovementSpeed() {
		return movementSpeed;
	}

	public float getRange() {
		return range;
	}

	public float getArmorReduction() {
		return armorReduction;
	}

	public float getPercentageArmorPenetration() {
		return percentageArmorPenetration;
	}

	public float getFlatArmorPenetration() {
		return flatArmorPenetration;
	}

	public float getMagicResistReduction() {
		return magicResistReduction;
	}

	public float getPercentageMagicPenetration() {
		return percentageMagicPenetration;
	}

	public float getFlatMagicPenetration() {
		return flatMagicPenetration;
	}

	public float getCriticalChance() {
		return criticalChance;
	}

	public float getCriticalBonusDamage() {
		return criticalBonusDamage;
	}

	public float getAbilityPower() {
		return abilityPower;
	}

	public float getMagicPenetration() {
		return magicPenetration;
	}

	public float getLifeSteal() {
		return lifeSteal;
	}

	public float getSpellVamp() {
		return spellVamp;
	}

	public float getEnergy() {
		return energy;
	}

	public float getEnergyRegen() {
		return energyRegen;
	}

	public float getCooldownReduction() {
		return cooldownReduction;
	}

	public float getRemainingHealth() {
		return remainingHealth;
	}

	public float getRemainingMana() {
		return remainingMana;
	}

	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Float getStat(ChampionStatType statType) {
		switch (statType) {
		case abilityPower: 
			return getAbilityPower();
		case damage:
			return getDamage();
		case armor:
			return getArmor();
		case magicResist:
			return getMagicResist();
		case health:
			return getHealth();
		case mana:
			return getMana();
		default:
			throw new IllegalArgumentException("Not implemented 'getStat()' function value for statType='"+statType+"'");
		}
	}

	public void makeAutoAttacks(boolean makeAutoAttacks) {
		this.makeAutoAttacks = makeAutoAttacks; 
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
		applySkillBonuses(skill);
	}

	public void addMastery(Mastery mastery) {
		masteries.add(mastery);
		applyMasteryBonuses(mastery);
	}
	
	public void addRune(Rune rune) {
		runes.add(rune);
		applyRuneBonuses(rune);
	}

	public void addItem(Item item) {
		items.add(item);
		applyItemBonuses(item);
		
		for(Item effect : item.uniqueEffects){
			if(!uniqueEffects.contains(effect)){
				uniqueEffects.add(effect);
				applyItemBonuses(effect);
			}
		}
		
		for(Skill skill : item.uniqueSkills){
			if(!uniqueSkills.contains(skill)){
				uniqueSkills.add(skill);
				applySkillBonuses(skill);
			}
		}
	}
}
