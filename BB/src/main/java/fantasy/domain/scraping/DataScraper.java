package fantasy.domain.scraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;



import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fantasy.domain.GameStat;
import fantasy.domain.Player;


public class DataScraper {
	
	private LocalDate date;
	
	
	public boolean updateStats(LocalDate date){
		this.date = date;
		try {
			
//			List<String> urls = getGameUrls(date);
//			updateStatsFromGame(urls.get(0));
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
	
	private void updateStatsFromGame(String gameUrl) throws IOException{
		Elements playerElements = getPlayerElements(gameUrl);
		for (Element element : playerElements) {
			Element nameElement = element.child(0);
			Player dbPlayer = getPlayerFromDatabase(nameElement);
			if(dbPlayer != null){
				updateGameStatForPlayer(element, dbPlayer);
				
			}
		}
		
	}
	
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
	
	private Elements getPlayerElements(String gameUrl) throws IOException{
		Document doc = Jsoup.connect(gameUrl).get();
		Elements playerElements = doc.select(".table_container[id$=basic] tr:has(td[csk])");
		//System.out.println(playerElements);
		return playerElements;
	}
	
	
	private List<String> getGameUrls(LocalDate date) throws IOException{
		
		Document doc = Jsoup.connect(String.format("http://www.basketball-reference.com/boxscores/index.cgi?month=%d&day=%d&year=%d", date.getMonthOfYear(), date.getDayOfMonth(), date.getYear())).get();
		Elements gameIdElements = doc.select("a[href^=/boxscores/]:contains(Final)");
		List<String> gameUrls = new ArrayList<String>();
		for(Element element : gameIdElements){
			String gameUrl = "http://www.basketball-reference.com" + element.attr("href");
			gameUrls.add(gameUrl);
		}
		System.out.println(gameUrls);
		return gameUrls;
		
	}
	

	
	
	public static void main(String[] args){
		DataScraper scraper = new DataScraper();
		scraper.updateStats(new LocalDate(2011, 12, 25));
	}
	
}
