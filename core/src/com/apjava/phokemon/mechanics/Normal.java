package com.apjava.phokemon.mechanics;

public class Normal implements PhokeType{
	@Override
	public String toString() {
		return "normal";
	}

	@Override
	public boolean isSuperEffective(PhokeType op) {
		return op.toString().equals("nothing");
	}

	@Override
	public boolean isNotEffective(PhokeType op) {
		return  op.toString().equals("nothing");
	}

}
