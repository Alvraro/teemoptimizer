package es.raro.item;

import java.util.HashSet;

import es.raro.riot.api.RaroRiotAPI;
import es.raro.skill.Skill;

public class RiotAPIItem extends Item {
	protected com.robrua.orianna.type.core.staticdata.Item item;

	public RiotAPIItem(String name){
		super(name);
	}

	@Override
	protected float defineHealth() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);
		
		return (float) item.getStats().getFlatHPPoolMod();
	}

	@Override
	protected float defineHealthPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatHPModPerLevel();
	}

	@Override
	protected float defineHealthPercentage() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getPercentHPPoolMod();
	}

	@Override
	protected float defineHealthRegen() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatHPRegenMod();
	}

	@Override
	protected float defineHealthRegenPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatHPRegenModPerLevel();
	}

	@Override
	protected float defineMana() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatMPPoolMod();
	}

	@Override
	protected float defineManaPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatMPModPerLevel();
	}

	@Override
	protected float defineManaRegen() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatMPRegenMod();
	}

	@Override
	protected float defineManaRegenPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatMPRegenModPerLevel();
	}

	@Override
	protected float defineDamage() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatPhysicalDamageMod();
	}

	@Override
	protected float defineDamagePerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatPhysicalDamageModPerLevel();
	}

	@Override
	protected float defineAttackSpeed() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatAttackSpeedMod();
	}

	@Override
	protected float defineArmor() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatArmorMod();
	}

	@Override
	protected float defineArmorPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatArmorModPerLevel();
	}

	@Override
	protected float defineMagicResist() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatSpellBlockMod();
	}

	@Override
	protected float defineMagicResistPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatSpellBlockModPerLevel();
	}

	@Override
	protected float defineMovementSpeed() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatMovementSpeedMod();
	}

	@Override
	protected float definePercentageArmorPenetration() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getPercentArmorPenetration();
	}

	@Override
	protected float defineFlatArmorPenetration() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatArmorPenetration();
	}

	@Override
	protected float defineCriticalChance() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatCritChanceMod();
	}

	@Override
	protected float defineCriticalBonusDamage() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatCritDamageMod();
	}

	@Override
	protected float defineAbilityPower() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatMagicDamageMod();
	}

	@Override
	protected float defineAbilityPowerPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatMagicDamageModPerLevel();
	}

	@Override
	protected float defineMagicPenetration() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatMagicPenetration();
	}

	@Override
	protected float defineLifeSteal() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getPercentLifeStealMod();
	}

	@Override
	protected float defineSpellVamp() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getPercentSpellVampMod();
	}

	@Override
	protected float defineEnergy() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatEnergyPoolMod();
	}

	@Override
	protected float defineEnergyPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatEnergyModPerLevel();
	}

	@Override
	protected float defineEnergyRegen() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getFlatEnergyRegenMod();
	}

	@Override
	protected float defineEnergyRegenPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRFlatEnergyRegenModPerLevel();
	}

	@Override
	protected float defineCooldownReduction() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getPercentCooldownReduction();
	}

	@Override
	protected float defineCooldownReductionPerLevel() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return (float) item.getStats().getRPercentCooldownModPerLevel();
	}

	@Override
	protected int defineTotalCost() {
		if(item==null)
			item = RaroRiotAPI.getItemByName(name);

		return item.getGold().getTotal();
	}

	@Override
	protected HashSet<Item> defineUniqueEffects() {
		return new HashSet<Item>();
	}

	@Override
	protected HashSet<Skill> defineUniqueSkills() {
		return new HashSet<Skill>();
	}
}
