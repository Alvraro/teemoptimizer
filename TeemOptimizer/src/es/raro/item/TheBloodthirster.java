package es.raro.item;

import java.util.HashSet;

public class TheBloodthirster extends RiotAPIItem {

	public TheBloodthirster() {
		super(TheBloodthirster.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		Item effect = new EmptyItem(name+"-Unique");
		effect.lifeSteal = Float.parseFloat(item.getEffect().get("Effect5Amount"));
		// TODO TheBloodthirster's shield
		
		ret.add(effect);
		
		return ret;
	}
}
