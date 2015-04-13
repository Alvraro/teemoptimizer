package es.raro.item;

import java.util.HashSet;

import es.raro.skill.Skill;

public class TheLightbringer extends RiotAPIItem {

	public TheLightbringer() {
		super(TheLightbringer.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		//Item effect = new EmptyItem(name+"-Unique");
		// TODO TheLightbringer's bleed effect
		//ret.add(effect);
		
		return ret;
	}
}
