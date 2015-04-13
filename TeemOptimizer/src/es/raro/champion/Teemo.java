package es.raro.champion;

public class Teemo extends RiotAPIChampion {
	public Teemo(Integer level){
		//super(level, masteries, runes, items, 383f, 77f, 4.65f, 0.65f, 200f, 40f, 3.95f, 0.45f, 44.5f, 3f, 0.687f, 0.0338f, 14f, 3.75f, 30f, 0f, 305f, 500);
		super(Teemo.class.getSimpleName(), level);
	}

}
