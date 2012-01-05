package fantasy.domain.scraping;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fantasy.domain.GameStat;
import fantasy.domain.Player;


public class DataScraper implements Serializable {

	private LocalDate date;
	
	/**
	 * Update All gameStats for players in database in given range
	 * @param startDate
	 * @param endDate
	 */
	public void updateStats(LocalDate startDate, LocalDate endDate){
		LocalDate currentDate = startDate;
		
		while(currentDate.toDate().getTime() <= endDate.toDate().getTime()){
			System.out.println("Day: " + currentDate + " update success: " + updateStats(currentDate));
			currentDate = currentDate.plusDays(1);
		}
	}

	/**
	 * Updates all gameStats for players in database for given day
	 * @param date
	 * @return
	 */
	public boolean updateStats(LocalDate date){
		this.date = date;
		try {
			//update stats from every game
			for(String gameUrl : getGameUrls(date)){
				updateStatsFromGame(gameUrl);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Updates stats from single game
	 * @param gameUrl
	 * @throws IOException
	 */
	private void updateStatsFromGame(String gameUrl) throws IOException{
		
		//get every player from game
		Elements playerElements = getPlayerElements(gameUrl);
		for (Element element : playerElements) {
			Element nameElement = element.child(0);
			Player dbPlayer = getPlayerFromDatabase(nameElement);
			//if player is in db update it stats
			if(dbPlayer != null){
				updateGameStatForPlayer(element, dbPlayer);
			}
		}

	}
	
	/**
	 * Updates player in database with information in playerElement
	 * @param playerElement
	 * @param player
	 */
	private void updateGameStatForPlayer(Element playerElement, Player player){
		GameStat stat = null;
		boolean foundExistingStat = false;
		for(GameStat existingStat: player.getStats()){
			if(existingStat.getDateWhen().equals(date.toDate())){
				System.out.println("Vanha statsi löytynyt");
				stat = existingStat;
				foundExistingStat = true;
				break;
			}
		}
		if(!foundExistingStat){
			stat = new GameStat();
			player.addGameStat(stat);
			System.out.println("uusiStatsi");
		}
		//parse meaningful information
		stat.setFgMade(Integer.parseInt(playerElement.child(2).text()));
		stat.setFgAttempts(Integer.parseInt(playerElement.child(3).text()));
		stat.setThreePointsMade(Integer.parseInt(playerElement.child(5).text()));
		stat.setFtMade(Integer.parseInt(playerElement.child(8).text()));
		stat.setRebounds(Integer.parseInt(playerElement.child(13).text()));
		stat.setAssists(Integer.parseInt(playerElement.child(14).text()));
		stat.setSteals(Integer.parseInt(playerElement.child(15).text()));
		stat.setBlocks(Integer.parseInt(playerElement.child(16).text()));
		stat.setTurnovers(Integer.parseInt(playerElement.child(17).text()));
		stat.setPoints(Integer.parseInt(playerElement.child(19).text()));
		stat.setDateWhen(date.toDate());

		player.flush();

	}
	
	/**
	 * Parses nameElement and returns matching player from database
	 * @param nameElement
	 * @return
	 */
	private Player getPlayerFromDatabase(Element nameElement){
		String name = nameElement.attr("csk");
		String[] nameSplitted = name.split(",");
		String lastName = nameSplitted[0];
		String firstName = nameSplitted[1];
		for(Player onePlayer: Player.findAllPlayers()){
			if(onePlayer.getFirstName().equalsIgnoreCase(firstName) && 
					onePlayer.getLastName().equalsIgnoreCase(lastName)){
				System.out.println("Pelaaja löytnyt: " + onePlayer);
				return onePlayer;
			}
		}
		return null;
	}
	
	/**
	 * Parses meaningful elements from gameUrl and returns them
	 * @param gameUrl
	 * @return
	 * @throws IOException
	 */
	private Elements getPlayerElements(String gameUrl) throws IOException{
		//Jsoup library is very cool and easy to use
		Document doc = Jsoup.connect(gameUrl).get();
		Elements playerElements = doc.select(".table_container[id$=basic] tr:has(td[csk])");
		return playerElements;
	}

	/**
	 * Returns urls to every game's boxscore in given day
	 * @param date
	 * @return
	 * @throws IOException
	 */
	private List<String> getGameUrls(LocalDate date) throws IOException{

		Document doc = Jsoup.connect(String.format("http://www.basketball-reference.com/boxscores/index.cgi?month=%d&day=%d&year=%d", date.getMonthOfYear(), date.getDayOfMonth(), date.getYear())).get();
		Elements gameIdElements = doc.select("a[href^=/boxscores/]:contains(Final)");
		List<String> gameUrls = new ArrayList<String>();
		for(Element element : gameIdElements){
			String gameUrl = "http://www.basketball-reference.com" + element.attr("href");
			gameUrls.add(gameUrl);
		}
		return gameUrls;

	}




	public static void main(String[] args){
		DataScraper scraper = new DataScraper();
		scraper.updateStats(new LocalDate(2011, 12, 25));
	}

}
