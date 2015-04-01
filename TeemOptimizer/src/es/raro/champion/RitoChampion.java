package es.raro.champion;

import java.util.ArrayList;

import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;

public class RitoChampion extends Champion {

	public RitoChampion(String name, int level, ArrayList<Mastery> masteries,
			ArrayList<Rune> runes, ArrayList<Item> items, float baseHealth,
			float healthGain, float baseHealthRegen, float healthRegenGain,
			float baseMana, float manaGain, float baseManaRegen,
			float manaRegenGain, float baseDamage, float damageGain,
			float baseAttackSpeed, float attackSpeedGain, float baseArmor,
			float armorGain, float baseMagicResist, float magicResistGain,
			float baseMovementSpeed, float baseRange) {
		super(level, masteries, runes, items, baseHealth, healthGain, baseHealthRegen,
				healthRegenGain, baseMana, manaGain, baseManaRegen, manaRegenGain,
				baseDamage, damageGain, baseAttackSpeed, attackSpeedGain, baseArmor,
				armorGain, baseMagicResist, magicResistGain, baseMovementSpeed,
				baseRange);

		
	}

}
