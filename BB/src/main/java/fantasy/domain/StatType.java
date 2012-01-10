package fantasy.domain;

import java.io.Serializable;

public enum StatType implements Serializable{
	POINTS, REBOUNDS, ASSISTS, BLOCKS, STEALS, TURNOVERS, FTMADE, THREEPOINTSMADE, FGPERCENTAGE;


	public double getMultiplier(){
		switch(this){
		case POINTS: return 2;
		case REBOUNDS: return 2;
		case ASSISTS: return 2;
		case TURNOVERS: 
			return -0.5;
		default: return 1;
		}
	}
}