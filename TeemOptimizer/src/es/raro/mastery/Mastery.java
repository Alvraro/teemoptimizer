package es.raro.mastery;

public class Mastery {
	private int health;
	private float healthRegen;
	private int mana;
	private float manaRegen;
	private float damage;
	private float attackSpeed;
	private float armor;
	private float magicResist;
	private float movementSpeed;
	private float range;
	private float armorReduction;
	private float percentageArmorPenetration;
	private float flatArmorPenetration;
	private float criticalChance;
	private float criticalBonusDamage;
	
	public Mastery(int health, float healthRegen, int mana, float manaRegen,
			float damage, float attackSpeed, float armor, float magicResist,
			float movementSpeed, float range, float armorReduction,
			float percentageArmorPenetration, float flatArmorPenetration,
			float criticalChance, float criticalBonusDamage) {
		super();
		this.health = health;
		this.healthRegen = healthRegen;
		this.mana = mana;
		this.manaRegen = manaRegen;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.armor = armor;
		this.magicResist = magicResist;
		this.movementSpeed = movementSpeed;
		this.range = range;
		this.armorReduction = armorReduction;
		this.percentageArmorPenetration = percentageArmorPenetration;
		this.flatArmorPenetration = flatArmorPenetration;
		this.criticalChance = criticalChance;
		this.criticalBonusDamage = criticalBonusDamage;
	}
	
	public int getHealth() {
		return health;
	}
	public float getHealthRegen() {
		return healthRegen;
	}
	public int getMana() {
		return mana;
	}
	public float getManaRegen() {
		return manaRegen;
	}
	public float getDamage() {
		return damage;
	}
	public float getAttackSpeed() {
		return attackSpeed;
	}
	public float getArmor() {
		return armor;
	}
	public float getMagicResist() {
		return magicResist;
	}
	public float getMovementSpeed() {
		return movementSpeed;
	}
	public float getRange() {
		return range;
	}
	public float getArmorReduction() {
		return armorReduction;
	}
	public float getPercentageArmorPenetration() {
		return percentageArmorPenetration;
	}
	public float getFlatArmorPenetration() {
		return flatArmorPenetration;
	}
	public float getCriticalChance() {
		return criticalChance;
	}
	public float getCriticalBonusDamage() {
		return criticalBonusDamage;
	}
}
