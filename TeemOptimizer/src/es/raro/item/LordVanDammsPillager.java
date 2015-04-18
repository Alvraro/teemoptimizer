package es.raro.item;

import java.util.HashSet;

public class LordVanDammsPillager extends RiotAPIItem {

	public LordVanDammsPillager() {
		super(LordVanDammsPillager.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		Item effect = new EmptyItem(name+"-Unique");
		effect.criticalBonusDamage = 0.5f;
		
		ret.add(effect);
		
		return ret;
	}
}
