package fantasy.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import fantasy.domain.GameStat;
import fantasy.domain.Player;
import fantasy.domain.Team;
import fantasy.domain.UserClass;
import fantasy.domain.authentication.Role;
import fantasy.domain.positions.PlayerPosition;
import fantasy.web.authentication.LoginWindow;


//TODO Jos poistaa pelaajan joukkuuesta tuhoutuu pelaaja samalla

public class FantasyApplication extends Application implements ApplicationContext.TransactionListener {
	
	public static final String PERSISTENCE_UNIT = 
            "persistenceUnit"; 
	
	
	private static ThreadLocal<FantasyApplication> currentApplication =
            new ThreadLocal<FantasyApplication> ();
	
	public static final UserClass VISITOR = new UserClass();
	
	private UserClass currentUser = VISITOR;
	
	
	@Override
	public void init() {
		//usercode
		initDB();		
		getContext ().addTransactionListener ( this );
		
		
				
		//Window window = createNewWindow();
		//setMainWindow(window);
		
		
		final LoginWindow login = new LoginWindow();
		//avoid memory leaks
		login.addListener(new CloseListener() {
			public void windowClose(CloseEvent e) {
				if (getMainWindow() != login) {
					FantasyApplication.this.removeWindow(login);
				}
			}
		});
		setMainWindow (login);
		
		
	}
	
	public void authenticate( String login, String password) throws Exception
    {
        
		String hash = getHash(password);
		
		UserClass user = getUser(login, hash);
		
		if (user != null) 
      
		{
			 System.out.println(user.getUsername());
            currentUser = user;
			loadMainResources();
            return;
        }
       
       throw new Exception("Login failed!");

    }
	
	private UserClass getUser(final String userName, final String password){
		JPAContainer<UserClass> users = JPAContainerFactory.make(UserClass.class, PERSISTENCE_UNIT);
		
		users.addContainerFilter("username", userName, false, true);
		users.addContainerFilter("password", password, false, true);
		if(users.size() == 1){ 
			return users.getItem(users.getIdByIndex(0)).getEntity();
		}
		else return null;
	}
	
	public static String getHash(String text){
		byte[] data = text.getBytes();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(data);
			byte[] hash = md.digest();
			String hashStr = new String(hash);
			return hashStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
    public void loadMainResources ()
    {
    	
    	setMainWindow (createNewWindow());
    }
	
	

	public Window createNewWindow() {
		final Window window = new FantasyWindow();

		// remove window on close to avoid memory leaks
		window.addListener(new CloseListener() {
			public void windowClose(CloseEvent e) {
				if (getMainWindow() != window) {
					FantasyApplication.this.removeWindow(window);
				}
			}
		});

		return window;
	}

	@Override
	public Window getWindow(String name) {
		// See if the window already exists in the application
		Window window = super.getWindow(name);

		// If a dynamically created window is requested, but
		// it does not exist yet, create it.
		if (window == null) {
			// Create the window object.
			window = createNewWindow();
			window.setName(name);

			// Add it to the application as a regular
			// application-level window
			addWindow(window);
		}

		return window;
	}
	
	
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
	
	
	private Player getPlayer(String firstName, String lastName){
		Player p = new Player();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.persist();
		return p;
	}
	
	private void initDB(){
		
		
		
		
		
		//JPAContainer<Player> players = JPAContainerFactory.make(Player.class, PERSISTENCE_UNIT);
		Player p = new Player();
		
		p.setFirstName("Juho");
		p.setLastName("Salmio");
		p.setPossiblePositions(new HashSet<PlayerPosition>(Arrays.asList(new PlayerPosition[] {PlayerPosition.SF, PlayerPosition.SG})));
		p.persist();
		
		
		//p.setStats(getRandomStatSet());
		
		
		Player p2 = new Player();
		p2.setFirstName("Teppo");
		p2.setLastName("Numminen");
		p2.setPossiblePositions(new HashSet<PlayerPosition>(Arrays.asList(new PlayerPosition[] {PlayerPosition.C, PlayerPosition.PF})));
		//p2.setStats(getRandomStatSet());
		p2.persist();
		//players.addEntity(p);
		//players.commit();
		
		Player p3 = new Player();
		p3.setFirstName("Dwight");
		p3.setLastName("Howard");
		p3.setPossiblePositions(new HashSet<PlayerPosition>(Arrays.asList(new PlayerPosition[] {PlayerPosition.C, PlayerPosition.PF})));
		//p3.setStats(getRandomStatSet());
		p3.persist();
		
		Player oma1 = getPlayer("Al", "Horford");
		Player oma2 = getPlayer("Marc", "Gasol");
		Player oma3 = getPlayer("Danny", "Granger");
		Player oma4 = getPlayer("Kyrie", "Irving");
		Player oma5 = getPlayer("Brandon", "Jennings");
		Player oma6 = getPlayer("Marreese", "Speights");
		Player oma7 = getPlayer("Jason", "Terry");
		Player oma8 = getPlayer("John", "Salmons");
		Player oma9 = getPlayer("Boris", "Diaw");
		Player oma10 = getPlayer("Jarrett", "Jack");
		Player oma11 = getPlayer("Derrick", "Williams");
		Player oma12 = getPlayer("Jason", "Richardson");
		Player oma13 = getPlayer("Marcin", "Gortat");
		Player oma14 = getPlayer("Jamal", "Crawford");
		
	
		//JPAContainer<Team>  teams = JPAContainerFactory.make(Team.class, PERSISTENCE_UNIT);
		Team t = new Team();
		t.setName("Boston");
		t.setPlayers(new HashSet<Player>(Arrays.asList(new Player[] {p, p2, p3, oma1, oma2 ,oma3 , oma4, oma5 ,oma6 , oma7, oma8 ,oma9 , oma10, oma11 ,oma12 , oma13, oma14})));
		t.persist();
		//teams.addEntity(t);
		//teams.commit();
		
		//JPAContainer<UserClass> users = JPAContainerFactory.make(UserClass.class, PERSISTENCE_UNIT);
		//users.setWriteThrough(false);
		
		UserClass user = new UserClass();
		
		user.setUsername("admin");
		user.setPassword(getHash("sana"));
		user.setUserRole(Role.ADMIN);
		user.setTeam(t);
		user.persist();
		user.flush();
		//users.addEntity(user);
		
		UserClass manager = new UserClass();
		
		manager.setUserRole(Role.MANAGER);
		manager.setUsername("manager");
		manager.setPassword(getHash("manager"));
		manager.setTeam(t);
		manager.persist();
		manager.flush();
		//users.addEntity(manager);
		
		//users.commit();
		
		
	
		
		
	}
	
	@Override
	public void transactionStart ( Application application, Object o )
    {
        if ( application == FantasyApplication.this )
        {
            currentApplication.set ( this );
        }
    }
	
	@Override
    public void transactionEnd ( Application application, Object o )
    {
        if ( application == FantasyApplication.this )
        {
            currentApplication.set ( null );
            currentApplication.remove ();
        }
    }
   
    public static FantasyApplication getInstance()
    {
    
        return currentApplication.get ();
    }
    
    public UserClass getCurrentUser(){
    	return this.currentUser;
    }
    
    public void setCurrentUser(UserClass user){
    	this.currentUser = user;
    }
    
}
