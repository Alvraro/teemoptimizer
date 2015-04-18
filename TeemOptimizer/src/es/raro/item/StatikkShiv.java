package es.raro.item;

import java.util.HashSet;

public class StatikkShiv extends RiotAPIItem {

	public StatikkShiv() {
		super(StatikkShiv.class.getSimpleName());
	}

	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		//Item effect = new EmptyItem(name+"-Unique");
		// TODO StatikkShiv's lightning strike
		//ret.add(effect);
		
		return ret;
	}	
}
