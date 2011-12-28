package fantasy.web.ui;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import fantasy.domain.Team;




public class TeamEditor extends EntityEditor{

	
	public TeamEditor(){
		super(Team.class,
				Arrays.asList(new String[] {"name"}));
		
	}




}


