package es.raro.item;

import java.util.HashSet;

public class WickedHatchet extends RiotAPIItem {

	public WickedHatchet() {
		super(WickedHatchet.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		//Item effect = new EmptyItem(name+"-Unique");
		// TODO WickedHatchet's bleed effect
		//ret.add(effect);
		
		return ret;
	}
}
