package fantasy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;

import fantasy.domain.positions.TeamPosition;

public class DataUpdater implements Serializable {


	/**
	 * Updates or creates new RoundTotal for every team playing this round.
	 * @param round
	 */
	public void updateAllRoundTotals(Round round){

		Set<Team> teamsInRound = getTeamsInRound(round);
		System.out.println("TeamsSIze:" + teamsInRound.size());

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
				rtToUpdate.setTeam(team);
				rtToUpdate.persist();
				team.addRoundTotal(rtToUpdate);
			}
			//update roundTotal
			updateRoundTotal(rtToUpdate, round, team);
			team.merge();
		}

		


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

		//iterate over all players and their stats if they are in the game
		for(Player player : team.getPlayers()){
			if(player.getCurrentPosition() != TeamPosition.BENCH ){
				for(GameStat stat : player.getStats()){

					long statTime = stat.getDateWhen().getTime();
					//update totals if stats time between limits
					if(statTime >= startDate.toDate().getTime() &&  statTime <= endDate.toDate().getTime()){
						addStatsToTotals(rt, stat);
					}
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
			
			System.out.println("Lisätään peli");
			teamsInRound.add(game.getHomeTeam());
			teamsInRound.add(game.getAwayTeam());
		}
		return teamsInRound;
	}


	public void updateRoundLeaguePoints(Round round){
		Set<Team> teams = getTeamsInRound(round);
		List<RoundTotal> totals = new ArrayList<RoundTotal>();
		
		//get all roundTotals from this round
		for(Team team : teams){
			for(RoundTotal total : team.getRoundTotals()){
				if(total.getRound().getId() == round.getId()){
					totals.add(total);
				}
			}
		}
		System.out.println("RoundTotalSize: " + totals.size());
		//update all leaguePoints properties 
		for(StatType currentStatType: StatType.values()){
			//sort totals  in order according to one stat
			Collections.sort(totals, new BeanComparator(RoundTotal.class, RoundTotal.getMethodSignature(currentStatType) , false));
			System.out.println("CurrentStat: " + currentStatType + " totals: " + totals );
			for(int i = 0; i< totals.size(); i++){
				RoundTotal total = totals.get(i);
				//highest value and first
				if(i == 0){
					total.setLeaguePoints(currentStatType, ( totals.size() - i)  * currentStatType.getMultiplier());
				}
				else{
					//if two totals have equal value in certain stat they both get equal (better) points. So we skip the lesser value.
					RoundTotal previousTotal = totals.get( i - 1);
					if(total.getStat(currentStatType) == previousTotal.getStat(currentStatType)){
						total.setLeaguePoints(currentStatType, previousTotal.getLpStat(currentStatType));
					}
					//most common case
					else{
						total.setLeaguePoints(currentStatType, (totals.size() - i) * currentStatType.getMultiplier());
					}
					
				}
				total.merge();
			}
			

		}
	}


}
