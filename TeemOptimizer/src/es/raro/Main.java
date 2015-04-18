package es.raro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import es.raro.problem.DamageCalculator;
import es.raro.problem.DamageItemBuyList;
import es.raro.problem.ItemBuyListCalculator;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Properties properties = new Properties();
		properties.load(new FileReader("config.properties"));

		// TODO test
		//String option = args[0];
		String option =  "ItemBuyList";
		
		if(option.equalsIgnoreCase("DamageCalculator")){
			DamageCalculator problem = new DamageCalculator(properties);
			float damage = problem .calculateDamage();
			System.out.println("Final damage = "+damage);
		}
		else if(option.equalsIgnoreCase("ItemBuyList")){
			ItemBuyListCalculator problem = new ItemBuyListCalculator(properties);
			DamageItemBuyList itemList = problem.getItemBuyList();
			System.out.println("Item list = "+itemList);
		}
	}

}
