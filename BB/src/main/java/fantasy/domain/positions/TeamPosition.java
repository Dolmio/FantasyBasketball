package fantasy.domain.positions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TeamPosition {
	PG,
	SG,
	G,
	SG_SF,
	SF,
	PF,
	PF_C,
	C,
	ANY,
	BENCH1,
	BENCH2,
	BENCH3,
	BENCH4,
	BENCH5,
	BENCH6,
	IR;
	
	public static final Set<TeamPosition>getOutOfTheFieldPositions(){
		return new HashSet<TeamPosition>(Arrays.asList(new TeamPosition[]{
				BENCH1, 
				BENCH2,
				BENCH3,
				BENCH4,
				BENCH5,
				BENCH6,
				IR
		}));
	}
}
