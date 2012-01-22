package fantasy.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import fantasy.domain.UserClass;


//TODO Jos poistaa pelaajan joukkuuesta tuhoutuu pelaaja samalla

public class FantasyApplication extends Application implements ApplicationContext.TransactionListener {
	
	private static final long serialVersionUID = 1L;


	public static final String PERSISTENCE_UNIT = 
            "persistenceUnit"; 
	
	
	private static ThreadLocal<FantasyApplication> currentApplication =
            new ThreadLocal<FantasyApplication>();
	
	public static final UserClass VISITOR = new UserClass();
	
	private UserClass currentUser;
	
	
	@Override
	public void init() {
		//usercode
		//DatabaseFactory.initDB();		
		currentUser = VISITOR;
		getContext().addTransactionListener ( this );
		
		setMainWindow(createNewWindow());
		
	}
	
	/**
	 * Tries to authenticate with given username and password if fails throws exception
	 * @param login
	 * @param password
	 * @throws Exception
	 */
	public void authenticate( String username, String password) throws Exception
    {
        //passwords are hashed in db so get hash from input pw
		String hash = getHash(password);
		
		//find the user
		UserClass user = getUser(username, hash);
		if(user == null){
			throw new Exception("Login failed!");
		}
		else{
			currentUser = user;
			return;
		}
		
       

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
	
   public Window createNewWindow() {
		final Window window = new FantasyWindow();

		// remove window on close to avoid memory leaks
		window.addListener(new CloseListener() {
			
			private static final long serialVersionUID = 1L;

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
