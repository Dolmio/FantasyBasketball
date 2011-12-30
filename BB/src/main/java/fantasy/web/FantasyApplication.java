package fantasy.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
import fantasy.domain.positions.PlayerPosition;
import fantasy.web.authentication.LoginWindow;

public class FantasyApplication extends Application implements ApplicationContext.TransactionListener {
	
	public static final String PERSISTENCE_UNIT = 
            "persistenceUnit"; 
	
	private static ThreadLocal<FantasyApplication> currentApplication =
            new ThreadLocal<FantasyApplication> ();
	
	private UserClass currentUser = null;
	
	
	@Override
	public void init() {
		//usercode
				
		getContext ().addTransactionListener ( this );
		initDB();
		
				
		//Window window = createNewWindow();
		//setMainWindow(window);
		setMainWindow ( new LoginWindow() );
		
		
	}
	
	public void authenticate( String login, String password) throws Exception
    {
        
		String hash = getHash(password);
		
		UserClass user = getUser(login, hash);
		
		if (user != null) 
        {
            currentUser = user;
			loadProtectedResources();
            return;
        }
       
       throw new Exception("Login failed!");

    }
	
	private UserClass getUser(final String userName, final String password){
		JPAContainer<UserClass> users = JPAContainerFactory.make(UserClass.class, PERSISTENCE_UNIT);
		
		users.addContainerFilter("username", userName, false, true);
		users.addContainerFilter("password", password, false, true);
		
//		users.addContainerFilter(new Filter() {
//			
//			@Override
//			public boolean passesFilter(Object itemId, Item item)
//					throws UnsupportedOperationException {
//				return item.getItemProperty("password").getValue().equals(password) &&
//						item.getItemProperty("username").getValue().equals(userName);
//				
//			}
//			
//			@Override
//			public boolean appliesToProperty(Object propertyId) {
//				if(propertyId.equals("password") || propertyId.equals("username")) return true;
//				else return false;
//			}
//		});
		
		if(users.size() == 1) return users.getItem(users.getIdByIndex(0)).getEntity();
		else return null;
	}
	
	private String getHash(String text){
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
	
    private void loadProtectedResources ()
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
		
		
		
		
		
		JPAContainer<Player> players = JPAContainerFactory.make(Player.class, PERSISTENCE_UNIT);
		Player p = new Player();
		p.setFirstName("Juho");
		p.setLastName("Salmio");
		players.addEntity(p);
		players.commit();
		
		
//		JPAContainer<PlayerPos>  positions = JPAContainerFactory.make(PlayerPos.class, PERSISTENCE_UNIT);
//		//positions.setWriteThrough(false);
//		for(PlayerPosition position : PlayerPosition.values()){
//			PlayerPos pos = new PlayerPos();
//			pos.setPlayerPosition(position);
//			positions.addEntity(pos);
//		}
//		positions.commit();
	
		JPAContainer<Team>  teams = JPAContainerFactory.make(Team.class, PERSISTENCE_UNIT);
		Team t = new Team();
		t.setName("Boston");
		//teams.addEntity(t);
		//teams.commit();
		
		JPAContainer<UserClass> users = JPAContainerFactory.make(UserClass.class, PERSISTENCE_UNIT);
		UserClass user = new UserClass();
		user.setUsername("admin");
		user.setPassword(getHash("sana"));
		user.setTeam(t);
		users.addEntity(user);
		users.commit();
		
		
		
		
		
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
