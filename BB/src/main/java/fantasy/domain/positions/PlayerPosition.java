package fantasy.domain.positions;

import java.util.ArrayList;

public enum PlayerPosition {
	PG,
	SG,
	PF,
	C,
	SF;

/**
 * 
 * If this position is able to play in the parameter position returns true
 * @param tp
 * @return
 */
public boolean canPlay(TeamPosition tp){
		
		
		if(tp == TeamPosition.ANY ||  TeamPosition.getOutOfTheFieldPositions().contains(tp)){
			return true;
		}
		
		switch(this){
		case PG:
			if(tp == TeamPosition.PG || tp == TeamPosition.G) return true;
			else return false;
		case SG:
			if(tp == TeamPosition.SG || tp == TeamPosition.SG_SF || tp == TeamPosition.G) return true;
			else return false;
		case PF:
			if(tp == TeamPosition.PF || tp == TeamPosition.PF_C) return true;
			else return false;
		case C:
			if(tp == TeamPosition.C || tp == TeamPosition.PF_C) return true;
			else return false;
		case SF:
			if(tp == TeamPosition.SF || tp == TeamPosition.SG_SF) return true;
			else return false;
			
		default: return false;
		
		}
		
		
		
	}
}
