package fantasy.web.authentication;

import java.awt.TextField;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Window;

public class LoginWindow extends Window {

	private Button loginButton;
	private Button visitorButton;
	private TextField userNameField;
	private PasswordField pwField;
	
	
	public LoginWindow(){
		super("Authentication Required !");
        setName ( "login" );
        initUI();
	}
	
	private void initUI(){
		addComponent (new LoginForm());
		
	}
	
}
