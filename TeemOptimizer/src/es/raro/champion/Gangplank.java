package es.raro.champion;

import java.util.ArrayList;

import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;

public class Gangplank extends Champion {
	public Gangplank(Integer level, ArrayList<Mastery> masteries, ArrayList<Rune> runes, ArrayList<Item> items){
		super(level, masteries, runes, items, 495f, 81f, 4.25f, 0.75f, 215f, 40f, 6.5f, 0.7f, 54f, 3f, 0.651f, 0.0235f, 16.5f, 3.3f, 30f, 1.25f, 320f, 125);
	}
	
}
