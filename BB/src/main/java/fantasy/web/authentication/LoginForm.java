package fantasy.web.authentication;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import fantasy.web.FantasyApplication;
import fantasy.web.ui.TabPanel;

public class LoginForm extends CustomComponent {

	
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	
	@AutoGenerated
	private Button loginButton;
	@AutoGenerated
	private PasswordField passwordField;
	@AutoGenerated
	private TextField userNameField;

	/**
	 * Login view
	 */
	public LoginForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);


		loginButton.addListener ( new Button.ClickListener()
        {
			private static final long serialVersionUID = 1L;

			public void buttonClick ( Button.ClickEvent event )
            {	
				//ugly boy slim strikes...
            	final TabPanel parentTabPanel = (TabPanel)getParent().getParent().getParent();
            	final Window appWindow =  (Window)getParent().getParent().getParent().getParent().getParent().getParent();
            	try
                {
                    //try to authenticate
            		FantasyApplication.getInstance ().authenticate((String)userNameField.getValue (), (String)passwordField.getValue ());
                    
            		//after succesfull authentication refresh window to have right tabs according to role
                    parentTabPanel.refreshAfterLogin();
                    //reset fields so they are not remembered after logout
                    userNameField.setValue("");
                    passwordField.setValue("");
                   
                }
                catch ( Exception e )
                {
                	appWindow.showNotification ( "Login Failed.\nWrong username or password");
                }
            }
        });
	
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// userNameField
		userNameField = new TextField();
		userNameField.setCaption("Username");
		userNameField.setImmediate(false);
		userNameField.setWidth("-1px");
		userNameField.setHeight("-1px");
		mainLayout.addComponent(userNameField);
		mainLayout.setComponentAlignment(userNameField, new Alignment(48));
		
		// passwordField
		passwordField = new PasswordField();
		passwordField.setCaption("Password");
		passwordField.setImmediate(false);
		passwordField.setWidth("-1px");
		passwordField.setHeight("-1px");
		passwordField.setNullRepresentation("\"\"");
		mainLayout.addComponent(passwordField);
		mainLayout.setComponentAlignment(passwordField, new Alignment(48));
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		mainLayout.setExpandRatio(horizontalLayout_1, 1.0f);
		mainLayout.setComponentAlignment(horizontalLayout_1, new Alignment(48));
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		
		// loginButton
		loginButton = new Button();
		loginButton.setCaption("Login");
		loginButton.setImmediate(true);
		loginButton.setWidth("-1px");
		loginButton.setHeight("-1px");
		horizontalLayout_1.addComponent(loginButton);
		
	
		return horizontalLayout_1;
	}

}
