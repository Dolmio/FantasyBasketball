package fantasy.web.ui;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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

public class EntityEditor<E> extends Window implements ClickListener,
FormFieldFactory {

	private Item entityItem; 
	private Form editorForm; 
	private Button saveButton; 
	private Button cancelButton; 
	private List<String> fields;

	public EntityEditor(final Class<E> entityClass, List<String> fields) { 
		
		this.fields = fields;
		editorForm = 
				new BeanValidationForm<E>(entityClass);
		
		editorForm.setFormFieldFactory(this); 
		editorForm.setWriteThrough(false); 
		editorForm.setImmediate(true);
		
		saveButton = new Button("Save",this);
		
		cancelButton = new Button("Cancel", this);
		editorForm.getFooter().addComponent(saveButton);
		editorForm.getFooter().addComponent(cancelButton);
		addComponent(editorForm);
		getContent().setSizeUndefined();
		

	}
	public void setEntityItem(Item entityItem){
		this.entityItem = entityItem;
		editorForm.setItemDataSource(entityItem, fields);
	}
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		Field field = DefaultFieldFactory.get().createField(item, propertyId,
				uiContext);
		if (field instanceof TextField) {
			((TextField) field).setNullRepresentation("");
		}
		return field;
	}



	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton) { 
			editorForm.commit(); 
			fireEvent(new EditorSavedEvent(this, entityItem)); 
		} else if (event.getButton() == cancelButton) { 
			editorForm.discard(); 
		} 
		close(); 
	} 

	public void addListener(EditorSavedListener listener) {
		try {
			Method method = EditorSavedListener.class.getDeclaredMethod(
					"editorSaved", new Class[] { EditorSavedEvent.class });
			addListener(EditorSavedEvent.class, listener, method);
		} catch (final java.lang.NoSuchMethodException e) {
			// This should never happen
			throw new java.lang.RuntimeException(
					"Internal error, editor saved method not found");
		}
	}

	public void removeListener(EditorSavedListener listener) {
		removeListener(EditorSavedEvent.class, listener);
	}

}


