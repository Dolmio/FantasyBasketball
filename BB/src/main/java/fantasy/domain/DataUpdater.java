package fantasy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.transaction.TransactionSystemException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import fantasy.domain.positions.TeamPosition;

public class DataUpdater implements Serializable {


	/**
	 * Updates or creates new RoundTotal for every team playing this round.
	 * @param round
	 */
	public void updateAllRoundTotals (Round round) throws TransactionSystemException{

		Set<Team> teamsInRound = getTeamsInRound(round);
		System.out.println("TeamsSIze:" + teamsInRound.size());

		for(Team team : teamsInRound){
			RoundTotal total = null;
			for(RoundTotal existingTotal : team.getRoundTotals()){
				if(existingTotal.getRound().getId() == round.getId()){
					total = existingTotal;
					break;
				}
			}
			
			if(total == null){
				total = new RoundTotal();
			}
			total.resetStats();
			total.setRound(round);
			total.setTeam(team);
			total.persist();
			team.addRoundTotal(total);
			
			//update roundTotal
			updateRoundTotal(total, round, team);
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

	/**
	 * Updates all games in certain round to have right winner according to the points team has in the round
	 * @param round
	 */
	public void updateWins(Round round){
		
		for(Game game : round.getGames()){
			Team homeTeam = game.getHomeTeam();
			Team awayTeam = game.getAwayTeam();
			RoundTotal homeRoundTotal = getMatchingRoundTotal(homeTeam.getRoundTotals(), round);
			RoundTotal awayRoundTotal = getMatchingRoundTotal(awayTeam.getRoundTotals(), round);

			//hometeam is winner when compare returns > 0
			if(homeRoundTotal.compareTo(awayRoundTotal) > 0){
				game.setWinner(GameWinner.HOME);
				
			}
			else{
				game.setWinner(GameWinner.AWAY);
			}

			game.merge();
		}


	}

	private RoundTotal getMatchingRoundTotal(Set<RoundTotal> totals, Round round){
		System.out.println("RoundID: " + round.getId());
		for(RoundTotal total : totals){
			System.out.println("totalId: " + total.getRound().getId());
			if(total.getRound().getId().equals(round.getId())){
				return total;
			}
		}
		return null;
	}


}
