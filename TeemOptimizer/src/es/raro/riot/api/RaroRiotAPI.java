package es.raro.riot.api;

import java.util.HashMap;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.staticdata.ChampionSpell;
import com.robrua.orianna.type.core.staticdata.Item;

public class RaroRiotAPI extends RiotAPI {
	public static HashMap<String,Item> items = new HashMap<String,Item>();
	public static HashMap<String,ChampionSpell> championSpells = new HashMap<String,ChampionSpell>();
	
	public synchronized static Item getItemByName(String name){
		// Firstly look on the cache
		Item item = items.get(name);
		if(item!=null)
			return item;

		// If not found, use RiotAPI
		for(Item itemAux : RiotAPI.getItems()){
			if(normalizeRiotName(itemAux.getName()).equalsIgnoreCase(name)){
				items.put(name, itemAux);
				return itemAux;
			}
		}
		
		throw new IllegalArgumentException("Item '"+name+"' not found");
	}

	public static ChampionSpell getChampionSpellByName(String name, String championName) {
		String key = name+"-"+championName;

		// Firstly look on the cache
		ChampionSpell spell = championSpells.get(key);
		if(spell!=null)
			return spell;

		// If not found, use RiotAPI
		for(ChampionSpell spellAux : getChampionByName(championName).getSpells()){
			if(normalizeRiotName(spellAux.getName()).equalsIgnoreCase(name)){
				championSpells.put(key, spellAux);
				return spellAux;
			}
		}
		
		throw new IllegalArgumentException("Skill '"+name+"' not found for champion '"+championName+"'");
	}

	public static String normalizeRiotName(String name) {
		return name.replaceAll("\\W", "");
	}

	
	
}
