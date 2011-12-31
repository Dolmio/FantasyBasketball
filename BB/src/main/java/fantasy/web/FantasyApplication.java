package fantasy.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;

import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import fantasy.domain.Player;
import fantasy.domain.PlayerPos;
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
	
	private void initDB(){
		
		
		
		
		
		//JPAContainer<Player> players = JPAContainerFactory.make(Player.class, PERSISTENCE_UNIT);
		Player p = new Player();
		
		p.setFirstName("Juho");
		p.setLastName("Salmio");
		p.persist();
		
		Player p2 = new Player();
		p2.setFirstName("Teppo");
		p2.setLastName("Numminen");
		p2.persist();
		//players.addEntity(p);
		//players.commit();
		
		

	
		//JPAContainer<Team>  teams = JPAContainerFactory.make(Team.class, PERSISTENCE_UNIT);
		Team t = new Team();
		t.setName("Boston");
		t.setPlayers(new HashSet<Player>(Arrays.asList(new Player[] {p, p2})));
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
