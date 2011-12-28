package fantasy.web.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;

public class EditorSavedEvent extends Event {


	private Item savedItem; 

	public EditorSavedEvent(Component source, Item savedItem) { 
		super(source); 
		this.savedItem = savedItem; 
	} 

	public Item getSavedItem() { 
		return savedItem; 
	} 
} 

