package es.raro.skill;

import com.robrua.orianna.type.core.staticdata.ChampionSpell;

import es.raro.champion.Champion;
import es.raro.model.UnmatchingDescription;
import es.raro.riot.api.RaroRiotAPI;

public abstract class RiotAPISkill extends Skill {

	protected ChampionSpell spell;

	public RiotAPISkill(String name, Champion champion, int level)
			throws UnmatchingDescription {
		super(name, champion, level);
	}

	@Override
	protected int defineManaCost(int level) {
		if(spell==null)
			spell = RaroRiotAPI.getChampionSpellByName(name, champion.getName());
		
		if(spell.getCostType().equalsIgnoreCase("Mana"))
			return spell.getCost().get(level-1);
		else
			return 0;
	}

	// TODO defineLifeCost
	// TODO defineEnergyCost
	
	@Override
	protected int defineRange(int level) {
		if(spell==null)
			spell = RaroRiotAPI.getChampionSpellByName(name, champion.getName());
		
		return spell.getRange().get(level-1);
	}

	@Override
	protected double defineCooldown(int level) {
		if(spell==null)
			spell = RaroRiotAPI.getChampionSpellByName(name, champion.getName());
		
		return spell.getCooldown().get(level-1);
	}

	@Override
	protected String defineDescription() {
		if(spell==null)
			spell = RaroRiotAPI.getChampionSpellByName(name, champion.getName());
		
		return spell.getDescription();
	}
	
}
