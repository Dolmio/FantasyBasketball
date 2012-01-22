package fantasy.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import fantasy.domain.GameStat;
import fantasy.domain.Player;
import fantasy.domain.Team;
import fantasy.domain.UserClass;
import fantasy.domain.authentication.Role;
import fantasy.web.FantasyApplication;

public class DatabaseFactory {



	private Set<GameStat> getRandomStatSet(){
		Set<GameStat> statSet = new HashSet<GameStat>();
		int statCount = new Random().nextInt(30) +1;
		for(int i = 0; i< statCount; i++){
			GameStat gs = new GameStat();
			gs.setPoints(new Random().nextInt(50));
			gs.setDateWhen(new Date(System.currentTimeMillis() - new Random().nextInt(30) * 1000 * 60 *60 *24));
			gs.persist();
			statSet.add(gs);
		}
		return statSet;
	}


	private Player getPlayer(String firstName, String lastName, Team team){
		Player p = new Player();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.persist();
		p.setTeam(team);
		return p;
	}

	public static void initDB(){
		/*
			Team t = new Team();
			t.setName("Boston");

			t.persist();


			Player oma1 = getPlayer("Al", "Horford",t);
			oma1.setCurrentPosition(TeamPosition.ANY);
			Player oma2 = getPlayer("Marc", "Gasol",t);
			oma2.setCurrentPosition(TeamPosition.ANY);
			Player oma3 = getPlayer("Danny", "Granger",t);
			Player oma4 = getPlayer("Kyrie", "Irving",t);
			Player oma5 = getPlayer("Brandon", "Jennings",t);
			Player oma6 = getPlayer("Marreese", "Speights",t);
			Player oma7 = getPlayer("Jason", "Terry",t);
			Player oma8 = getPlayer("John", "Salmons",t);
			Player oma9 = getPlayer("Boris", "Diaw",t);
			Player oma10 = getPlayer("Jarrett", "Jack",t);
			Player oma11 = getPlayer("Derrick", "Williams",t);
			Player oma12 = getPlayer("Jason", "Richardson",t);
			Player oma13 = getPlayer("Marcin", "Gortat",t);
			Player oma14 = getPlayer("Jamal", "Crawford",t);


			//JPAContainer<Team>  teams = JPAContainerFactory.make(Team.class, PERSISTENCE_UNIT);


			Team team2 = new Team();
			team2.setName("Chicago");
			team2.persist();
			Player player = getPlayer("Derrick", "Rose", team2);
			player.setCurrentPosition(TeamPosition.G);


			//teams.addEntity(t);
			//teams.commit();

			//JPAContainer<UserClass> users = JPAContainerFactory.make(UserClass.class, PERSISTENCE_UNIT);
			//users.setWriteThrough(false);

			Team team3 = new Team();
			team3.setName("Dallas");
			team3.persist();
			Player player3 = getPlayer("Lebron", "James", team3);
			player3.setCurrentPosition(TeamPosition.PF);



			Team team4 = new Team();
			team4.setName("Lakers");
			team4.persist();
			Player player4 = getPlayer("Kobe", "Bryant", team4);
			player4.setCurrentPosition(TeamPosition.SF);




			Game game = new Game();
			game.persist();
			game.setAwayTeam(t);
			game.setHomeTeam(team2);


			Game game2 = new Game();
			game2.persist();
			game2.setAwayTeam(team3);
			game2.setHomeTeam(team4);


			Round round = new Round();
			round.setName("Kierros1");
			round.setStartDate(new LocalDate(2012, 1, 1).toDate());
			round.setEndDate(new LocalDate(2012,1,10).toDate());
			round.persist();

			game.setRound(round);
			game2.setRound(round);





			round.flush();
		 */


		UserClass user = new UserClass();

		user.setUsername("admin");
		user.setPassword(FantasyApplication.getHash("sana"));
		user.setUserRole(Role.ADMIN);
		//user.setTeam(t);
		user.persist();
		user.flush();
		//users.addEntity(user);
		/*
			UserClass manager = new UserClass();

			manager.setUserRole(Role.MANAGER);
			manager.setUsername("manager");
			manager.setPassword(getHash("manager"));
			manager.setTeam(t);
			manager.persist();
			manager.flush();
			//users.addEntity(manager);
			DataScraper scraper = new DataScraper();
			scraper.updateStats(new LocalDate(2012, 1, 10),  new LocalDate(2012, 1, 10));

			//users.commit();

		 */



	}
}


