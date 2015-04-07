package es.raro.champion;

import java.util.ArrayList;

import com.robrua.orianna.api.core.RiotAPI;

import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;

public class RiotAPIChampion extends Champion {
	protected com.robrua.orianna.type.core.staticdata.Champion champion;

	public RiotAPIChampion(String name, int level, ArrayList<Mastery> masteries,
			ArrayList<Rune> runes, ArrayList<Item> items) {
		super(name, level, masteries, runes, items);
	}

	@Override
	protected float defineBaseHealth() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getHP();
	}

	@Override
	protected float defineHealthGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getHPPerLevel();
	}

	@Override
	protected float defineBaseHealthRegen() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getHPRegen();
	}

	@Override
	protected float defineHealthRegenGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getHPRegenPerLevel();
	}

	@Override
	protected float defineBaseMana() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getMP();
	}

	@Override
	protected float defineManaGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getMPPerLevel();
	}

	@Override
	protected float defineBaseManaRegen() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getMPRegen();
	}

	@Override
	protected float defineManaRegenGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getMPRegenPerLevel();
	}

	@Override
	protected float defineBaseDamage() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getAttackDamage();
	}

	@Override
	protected float defineDamageGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getAttackDamagePerLevel();
	}

	@Override
	protected float defineBaseAttackSpeed() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) (0.625 / (1 + champion.getStats().getAttackSpeedOffset()));
	}

	@Override
	protected float defineAttackSpeedGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getAttackDamagePerLevel();
	}

	@Override
	protected float defineBaseArmor() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getArmor();
	}

	@Override
	protected float defineArmorGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getArmorPerLevel();
	}

	@Override
	protected float defineBaseMagicResist() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getSpellBlock();
	}

	@Override
	protected float defineMagicResistGain() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getSpellBlockPerLevel();
	}

	@Override
	protected float defineBaseMovementSpeed() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getMovespeed();
	}

	@Override
	protected float defineBaseRange() {
		if(champion==null)
			champion = RiotAPI.getChampionByName(name);
		
		return (float) champion.getStats().getAttackRange();
	}

}
