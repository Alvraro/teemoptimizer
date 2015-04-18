package es.raro.problem;

import java.util.ArrayList;

import es.raro.item.Item;

public class DamageItemBuyList{
	public ArrayList<Item> items;
	public float damage;
	
	public DamageItemBuyList(ArrayList<Item> items, float damage){
		this.items = items;
		this.damage = damage;
	}
	
	public DamageItemBuyList clone() {
		ArrayList<Item> newItems = new ArrayList<Item>(items);  
		return new DamageItemBuyList(newItems, damage);
	}

	@Override
	public String toString() {
		return "DamageItemBuyList [items=" + items + ", damage=" + damage + "]";
	}
	
}