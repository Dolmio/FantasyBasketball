package fantasy.web;

import java.util.Arrays;

import fantasy.domain.Team;
import com.vaadin.ui.Window;

import fantasy.web.ui.EntityTab;
import fantasy.web.ui.EntityTable;
import fantasy.web.ui.TeamTable;
import fantasy.web.ui.TeamView;

public class FantasyWindow extends Window {

    public FantasyWindow() {

        // entity manager
        //FantasyEntityManagerView entityManagerView = new FantasyEntityManagerView();
        //TeamTable tt = new TeamTable();
    	//TeamView tv = new TeamView();
    	//EntityTable<Team>et = new EntityTable<Team>();
    	//et.init(Team.class, 
    		//	new Object[] {"name", "players"},  Arrays.asList(new String[] {"name"}));
    	EntityTab eTab = new EntityTab();
    	setContent(eTab);

        // select window theme
        setTheme("fantasy");
    }
}
