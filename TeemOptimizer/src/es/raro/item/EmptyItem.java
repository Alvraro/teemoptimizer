package es.raro.item;

import java.util.HashSet;

import es.raro.skill.Skill;

public class EmptyItem extends Item {

	public EmptyItem(String name) {
		super(name);
	}

	@Override
	protected HashSet<Item> defineUniqueEffects() {
		return null;
	}

	@Override
	protected HashSet<Skill> defineUniqueSkills() {
		return null;
	}

	@Override
	protected int defineTotalCost() {
		return 0;
	}

	@Override
	protected float defineHealth() {
		return 0;
	}

	@Override
	protected float defineHealthPerLevel() {
		return 0;
	}

	@Override
	protected float defineHealthPercentage() {
		return 0;
	}

	@Override
	protected float defineHealthRegen() {
		return 0;
	}

	@Override
	protected float defineHealthRegenPerLevel() {
		return 0;
	}

	@Override
	protected float defineMana() {
		return 0;
	}

	@Override
	protected float defineManaPerLevel() {
		return 0;
	}

	@Override
	protected float defineManaRegen() {
		return 0;
	}

	@Override
	protected float defineManaRegenPerLevel() {
		return 0;
	}

	@Override
	protected float defineDamage() {
		return 0;
	}

	@Override
	protected float defineDamagePerLevel() {
		return 0;
	}

	@Override
	protected float defineAttackSpeed() {
		return 0;
	}

	@Override
	protected float defineArmor() {
		return 0;
	}

	@Override
	protected float defineArmorPerLevel() {
		return 0;
	}

	@Override
	protected float defineMagicResist() {
		return 0;
	}

	@Override
	protected float defineMagicResistPerLevel() {
		return 0;
	}

	@Override
	protected float defineMovementSpeed() {
		return 0;
	}

	@Override
	protected float definePercentageArmorPenetration() {
		return 0;
	}

	@Override
	protected float defineFlatArmorPenetration() {
		return 0;
	}

	@Override
	protected float defineCriticalChance() {
		return 0;
	}

	@Override
	protected float defineCriticalBonusDamage() {
		return 0;
	}

	@Override
	protected float defineAbilityPower() {
		return 0;
	}

	@Override
	protected float defineAbilityPowerPerLevel() {
		return 0;
	}

	@Override
	protected float defineMagicPenetration() {
		return 0;
	}

	@Override
	protected float defineLifeSteal() {
		return 0;
	}

	@Override
	protected float defineSpellVamp() {
		return 0;
	}

	@Override
	protected float defineEnergy() {
		return 0;
	}

	@Override
	protected float defineEnergyPerLevel() {
		return 0;
	}

	@Override
	protected float defineEnergyRegen() {
		return 0;
	}

	@Override
	protected float defineEnergyRegenPerLevel() {
		return 0;
	}

	@Override
	protected float defineCooldownReduction() {
		return 0;
	}

	@Override
	protected float defineCooldownReductionPerLevel() {
		return 0;
	}

}
