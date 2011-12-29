package fantasy.web;

import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

import fantasy.domain.PlayerPos;
import fantasy.domain.Team;
import fantasy.domain.positions.PlayerPosition;

public class FantasyApplication extends Application {
	
	public static final String PERSISTENCE_UNIT = 
            "persistenceUnit"; 
	
	@Override
	public void init() {
		Window window = createNewWindow();
		setMainWindow(window);
		
		//usercode
		initDB();
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
		JPAContainer<PlayerPos>  positions = JPAContainerFactory.make(PlayerPos.class, PERSISTENCE_UNIT);
		//positions.setWriteThrough(false);
		for(PlayerPosition position : PlayerPosition.values()){
			PlayerPos pos = new PlayerPos();
			pos.setPlayerPosition(position);
			positions.addEntity(pos);
		}
		positions.commit();
	
		JPAContainer<Team>  teams = JPAContainerFactory.make(Team.class, PERSISTENCE_UNIT);
		Team t = new Team();
		t.setName("Boston");
		teams.addEntity(t);
		teams.commit();
		
		
	}

}
