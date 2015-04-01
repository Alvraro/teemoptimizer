package es.raro.skill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.raro.champion.Champion;
import es.raro.model.UnmatchingDescription;

public class Parrrley extends Skill {

	public Parrrley(Champion champion, Integer level) throws UnmatchingDescription {
		super(champion, level);
	}

	@Override
	protected int defineCost(int level) {
		switch(level){
		case 1:
			return 50;
		case 2:
			return 55;
		case 3:
			return 60;
		case 4:
			return 65;
		case 5:
			return 70;
		default:
			throw new IllegalArgumentException("Level must be between 1 and 5");
		}
	}

	@Override
	protected int defineRange(int level) {
		return 625;
	}

	@Override
	protected int defineCooldown(int level) {
		return 5;
	}

	@Override
	protected String defineDescription() {
		return "Physical Damage: 20 / 45 / 70 / 95 / 120 (+ 100% AD)";
	}

	@Override
	protected void parseDescription(int level, String description) throws UnmatchingDescription {
		String regex = ".*? ([\\d /]+) \\(\\+ (\\d+)% AD\\)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(description);
		
		if(m.matches())
			throw new UnmatchingDescription(regex, description);
		
		// Base damage per level
		String aux = m.group(1);
		basePhysicalDamageDone = Integer.parseInt(aux.split("/")[level-1]);
		
		// Bonus damage per %AD
		aux = m.group(2);
		bonusPhysicalDamagePerAttackDamage = Integer.parseInt(aux);
	}

	@Override
	public boolean canCriticallyHit() {
		return true;
	}

}
