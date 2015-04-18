package es.raro.item;

import java.util.HashSet;

public class InfinityEdge extends RiotAPIItem {

	public InfinityEdge() {
		super(InfinityEdge.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		Item effect = new EmptyItem(name+"-Unique");
		effect.criticalBonusDamage = Float.parseFloat(item.getEffect().get("Effect2Amount"));
		
		ret.add(effect);
		
		return ret;
	}
}
