package es.raro.item;

import java.util.HashSet;

import es.raro.skill.Skill;

public class TrinityForce extends RiotAPIItem {

	public TrinityForce() {
		super(TrinityForce.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		//Item effect = new EmptyItem(name+"-Unique");
		// TODO TrinityForce's rage (movement speed bonus)
		//effect.criticalBonusDamage = Float.parseFloat(item.getEffect().get("Effect2Amount"));
		// TODO TrinityForce's Spellblade effect (on hit)
		//ret.add(effect);
		
		return ret;
	}
}
