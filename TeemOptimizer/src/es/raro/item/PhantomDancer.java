package es.raro.item;

import java.util.HashSet;

import es.raro.skill.Skill;

public class PhantomDancer extends RiotAPIItem {

	public PhantomDancer() {
		super(PhantomDancer.class.getSimpleName());
	}
	
	@Override
	protected HashSet<Item> defineUniqueEffects() {
		HashSet<Item> ret = new HashSet<Item>();
		
		//Item effect = new EmptyItem(name+"-Unique");
		// TODO Phantom Dancer's movement effect
		//ret.add(effect);
		
		return ret;
	}
}
