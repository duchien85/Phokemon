package com.apjava.phokemon.mechanics;

public class Water implements PhokeType {
	
	@Override
	public String toString() {
		return "water";
	}

	@Override
	public boolean isSuperEffective(PhokeType op) {
		return op.toString().equals("electric") || op.toString().equals("grass");
	}

	@Override
	public boolean isNotEffective(PhokeType op) {
		return  op.toString().equals("fire");
	}
}
