package fantasy.domain;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

public class DataUpdater {



	public void updateAllRoundTotals(Round round){
		
		Set<Team> teamsInRound = getTeamsInRound(round);


		for(Team team : teamsInRound){
			boolean roundTotalExists = false;
			RoundTotal rtToUpdate = null;
			//find out if we need brand new RoundTotal or if we overwrite existing one
			for(RoundTotal rt : team.getRoundTotals()){
				if(rt.getId() == round.getId()){
					rtToUpdate = rt;
					rtToUpdate.resetStats();
					roundTotalExists = true;
					break;
				}

			}

			if(!roundTotalExists){
				rtToUpdate = new RoundTotal();
				rtToUpdate.setRound(round);
			}
			
			updateRoundTotal(rtToUpdate, round, team);

		}

		round.merge();


	}
	/**
	 * Updates roundTotal for one team
	 * @param rt
	 * @param round
	 * @param team
	 */
	private void updateRoundTotal(RoundTotal rt, Round round, Team team){
		LocalDate startDate = new LocalDate(round.getStartDate());
		LocalDate endDate = new LocalDate(round.getEndDate());
		
		//iterate over all players and their stats
		for(Player player : team.getPlayers()){
			for(GameStat stat : player.getStats()){
				
				long statTime = stat.getDateWhen().getTime();
				//update totals if stats time between limits
				if(statTime >= startDate.toDate().getTime() &&  statTime <= endDate.toDate().getTime()){
					addStatsToTotals(rt, stat);
				}
			}
		}
		
		
		
	}
	
	private void addStatsToTotals(RoundTotal rt, GameStat stat){
		rt.setAssists(rt.getAssists() + stat.getAssists());
		rt.setBlocks(rt.getBlocks() + stat.getBlocks());
		rt.setFgAttempts(rt.getFgAttempts() + stat.getFgAttempts());
		rt.setFgMade(rt.getFgMade() + stat.getFgMade());
		rt.setFtMade(rt.getFtMade() + stat.getFtMade());
		rt.setPoints(rt.getPoints() + stat.getPoints());
		rt.setRebounds(rt.getRebounds() + stat.getRebounds());
		rt.setSteals(rt.getSteals() + stat.getSteals());
		rt.setThreePointsMade(rt.getThreePointsMade() + stat.getThreePointsMade());
		rt.setTurnovers(rt.getTurnovers() + stat.getTurnovers());
	}

	/**
	 * Get participating teams in round
	 * @param round
	 * @return teams in round
	 */
	private Set<Team> getTeamsInRound(Round round){
		Set<Team> teamsInRound = new HashSet<Team>();
		for(Game game : round.getGames()){
			teamsInRound.add(game.getHomeTeam());
			teamsInRound.add(game.getAwayTeam());
		}
		return teamsInRound;
	}


}
