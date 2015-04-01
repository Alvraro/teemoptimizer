package es.raro.champion;

import java.util.ArrayList;

import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;

public class Caitlyn extends Champion {
	public Caitlyn(Integer level, ArrayList<Mastery> masteries, ArrayList<Rune> runes, ArrayList<Item> items){
		super(level, masteries, runes, items, 390f, 80f, 4.75f, 0.55f, 255f, 35f, 6.5f, 0.55f, 47f, 3f, 0.668f, 0.03f, 13f, 3.5f, 30f, 0f, 300f, 650);
	}

}
