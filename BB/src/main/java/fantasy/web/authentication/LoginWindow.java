package fantasy.web.authentication;

import java.awt.TextField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
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
		/*
		// common part: create layout
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		LoginForm form = new LoginForm();
		mainLayout.addComponent(form);
		mainLayout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		*/
		addComponent(new LoginForm());

	}

}
