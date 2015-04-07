package es.raro.champion;

import java.util.ArrayList;

import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;

public class Teemo extends RiotAPIChampion {
	public Teemo(Integer level, ArrayList<Mastery> masteries, ArrayList<Rune> runes, ArrayList<Item> items){
		//super(level, masteries, runes, items, 383f, 77f, 4.65f, 0.65f, 200f, 40f, 3.95f, 0.45f, 44.5f, 3f, 0.687f, 0.0338f, 14f, 3.75f, 30f, 0f, 305f, 500);
		super("Teemo", level, masteries, runes, items);
	}

}
