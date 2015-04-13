package es.raro.item;

import java.util.HashSet;

import es.raro.skill.Skill;

public class YoumuusGhostblade extends RiotAPIItem {

	public YoumuusGhostblade() {
		super(YoumuusGhostblade.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		Item effect = new EmptyItem(name+"-Unique");
		effect.flatArmorPenetration = Float.parseFloat(item.getEffect().get("Effect2Amount"));
		
		ret.add(effect);
		
		return ret;
	}
}
