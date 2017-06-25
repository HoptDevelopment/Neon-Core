package com.roguehcf.neon.pvpclass;

public enum ArmorType {

	LEATHER, GOLD, CHAIN, IRON;
	
	public String getName(){
		switch(this){
			case LEATHER: {
				return "Leather";
			}
		
			case GOLD: {
				return "Gold";
			}
		
			case CHAIN: {
				return "Chain";
			}
			
			case IRON: {
				return "Iron";
			}
		}
		return "None";
	}
}
