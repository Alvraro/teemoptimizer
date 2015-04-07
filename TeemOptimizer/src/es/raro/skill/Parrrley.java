package es.raro.skill;

import java.util.Collections;
import java.util.HashMap;

import es.raro.champion.Champion;
import es.raro.champion.ChampionStatType;
import es.raro.model.UnmatchingDescription;
import es.raro.riot.api.RaroRiotAPI;

public class Parrrley extends RiotAPISkill {

	public Parrrley(Champion champion, Integer level) throws UnmatchingDescription {
		super("Parrrley", champion, level);
	}

	@Override
	public boolean canCriticallyHit() {
		return true;
	}

	@Override
	public boolean applyOnHitEffects() {
		return true;
	}


	@Override
	protected float defineBasePhysicalDamageDone() {
		if(spell==null)
			spell = RaroRiotAPI.getChampionSpellByName(name, champion.getName());
		
		return spell.getEffect().get(1).get(level-1).floatValue();
	}

	@Override
	protected HashMap<ChampionStatType, Float> defineBonusPhysicalDamageDone() {
		HashMap<ChampionStatType, Float> ret = new HashMap<ChampionStatType, Float>();
		
		ret.put(ChampionStatType.damage, (float) 1);
		
		return ret;
	}

	@Override
	protected float defineBaseMagicDamageDone() {
		return 0;
	}

	@Override
	protected HashMap<ChampionStatType, Float> defineMagicPhysicalDamageDone() {
		return (HashMap<ChampionStatType, Float>) Collections.EMPTY_MAP;
	}
}
