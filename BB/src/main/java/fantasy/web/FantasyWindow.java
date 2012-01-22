package fantasy.web;

import com.vaadin.ui.Window;

import fantasy.web.ui.MainFrame;

public class FantasyWindow extends Window {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FantasyWindow() {

    	setContent(new MainFrame());

        // select window theme
        setTheme("fantasy");
    }
}
