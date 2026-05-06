package es.raro.problem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.robrua.orianna.api.core.RiotAPI;

import es.raro.item.Item;
import es.raro.model.Teemodel;
import es.raro.riot.api.RaroRiotAPI;

public class ItemBuyListCalculator extends Problem {

	private int gold;
	private DamageCalculator calc;
	private CallRegister damageItemBuyListRegister;
	private CallRegister calculateDamageRegister;

	public ItemBuyListCalculator(Properties properties) throws FileNotFoundException, IOException {
		super(properties);

		gold = Integer.parseInt(properties.getProperty("attacker.gold"));
		
		damageItemBuyListRegister = new CallRegister();
		calculateDamageRegister = new CallRegister();
	}

	public DamageItemBuyList getItemBuyList() throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchFieldException, IOException {
		// Initialize the damage calculator
		calc = new DamageCalculator(properties);

		//Champion attacker = calc.getAttacker();

		// Initially eligible items (i.e. all)
		ArrayList<Item> eligibleItems = getPossibleItems();

		// Initially selected items (i.e. none)
		DamageItemBuyList selectedItems = new DamageItemBuyList(new ArrayList<Item>(), 0);
		
		float damage = calculateDamage(new ArrayList<Item>());
		Logger.getGlobal().info("Damage without items = "+damage);
		
		// Find the item list to buy with given gold and eligible items
		DamageItemBuyList ret = getItemBuyList(eligibleItems, gold, selectedItems);
		
		Logger.getGlobal().info("Best damage achieved = "+ret.damage);

		return ret;
	}

	private float calculateDamage(ArrayList<Item> selectedItems) {
		calculateDamageRegister.register(new Object[]{selectedItems});
		
		calc.getAttacker().setItems(selectedItems);
		return calc.calculateDamage();
	}

	private DamageItemBuyList getItemBuyList(List<Item> eligibleItems, int gold, DamageItemBuyList selectedItems) {
		damageItemBuyListRegister.register(new Object[]{eligibleItems.size(), gold, selectedItems.items});
		
		// No damage can be done if we have ran out of gold 
		if(gold<0)
			return new DamageItemBuyList(new ArrayList<Item>(), 0);
		
		// If there are no more eligible items, we can calculate straight-forward how much damage we can make 
		if(eligibleItems.isEmpty()){
			float damage = calculateDamage(selectedItems.items);
			DamageItemBuyList ret = selectedItems.clone();
			ret.damage = damage;
			return ret;
		}
		
		// Current item being analyzed
		Item currentItem = eligibleItems.get(eligibleItems.size()-1);

		// We have 2 options:
		// 1) Include (once more) the item if we have enough gold
		float withBestDamage = 0;
		DamageItemBuyList withBestList = null;
		if(gold >= currentItem.totalCost){
			// Select the item and find the best sublist recursively
			DamageItemBuyList selectedItemsWith = selectedItems.clone();
			selectedItemsWith.items.add(currentItem);
			withBestList = getItemBuyList(eligibleItems, gold - currentItem.totalCost, selectedItemsWith);
			withBestDamage = withBestList.damage;
		}

		// 2) Don't include the item:
		// Without selecting the item find the best sublist recursively
		DamageItemBuyList withoutBestList = getItemBuyList(eligibleItems.subList(0, eligibleItems.size()-1), gold, selectedItems);
		float withoutBestDamage = withoutBestList.damage;
		
		// Return the best (if tied, return without as we don't need the item)
		if(withBestDamage > withoutBestDamage)
			return withBestList;
		else
			return withoutBestList;
	}

	private ArrayList<Item> getPossibleItems() throws InstantiationException, IllegalAccessException {
		ArrayList<Item> items = new ArrayList<Item>();

		// Get all items
		for(com.robrua.orianna.type.core.staticdata.Item i : RiotAPI.getItems()){
			if(i.getRequiredChampionName().isEmpty() && i.getGold().getTotal()>0)
				items.add(Teemodel.getItemInstance(RaroRiotAPI.normalizeRiotName(i.getName())));
		}

		//for(Item i : RaroRiotAPI.getItems()){
		
		// TODO filter by map or other criteria

		return items;
	}



}
