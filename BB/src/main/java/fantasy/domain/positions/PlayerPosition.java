package fantasy.domain.positions;

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
		
		if(tp == TeamPosition.ANY || tp == TeamPosition.BENCH){
			return true;
		}
		
		switch(this){
		case PG:
			if(tp == TeamPosition.PG) return true;
		case SG:
			if(tp == TeamPosition.SG || tp == TeamPosition.SG_SF) return true;
		case PF:
			if(tp == TeamPosition.PF || tp == TeamPosition.PF_C) return true;
		case C:
			if(tp == TeamPosition.C || tp == TeamPosition.PF_C) return true;
		case SF:
			if(tp == TeamPosition.SF) return true;
		default: return false;
		
		}
		
		
		
	}
}
