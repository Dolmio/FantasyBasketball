package fantasy.web;

import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDate;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

import fantasy.domain.scraping.DataScraper;

public class UpdateView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button updateButton;
	@AutoGenerated
	private PopupDateField endDateField;
	@AutoGenerated
	private PopupDateField startDateField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */



	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public UpdateView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		startDateField.setValue(new Date(System.currentTimeMillis()));
		endDateField.setValue(new Date(System.currentTimeMillis()));
		
		startDateField.setImmediate(true);
		startDateField.setResolution(DateField.RESOLUTION_DAY);
		startDateField.setLocale(new Locale("fi", "FI"));
		
		endDateField.setImmediate(true);
		endDateField.setResolution(DateField.RESOLUTION_DAY);
		endDateField.setLocale(new Locale("fi", "FI"));

		updateButton.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				
				if(startDateField.getValue() instanceof Date && endDateField.getValue() instanceof Date){
					DataScraper scraper = new DataScraper();
					LocalDate startDate = new LocalDate(((Date)startDateField.getValue()).getTime());
					LocalDate endDate = new LocalDate(((Date)endDateField.getValue()).getTime());
					scraper.updateStats(startDate, endDate);
					System.out.println("Päivitetty");
					
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

		// startDateField
		startDateField = new PopupDateField();
		startDateField.setCaption("From:");
		startDateField.setImmediate(false);
		startDateField.setWidth("-1px");
		startDateField.setHeight("-1px");
		startDateField.setInvalidAllowed(false);
		mainLayout.addComponent(startDateField);
		mainLayout.setComponentAlignment(startDateField, new Alignment(20));

		// popupDateField_1
		endDateField = new PopupDateField();
		endDateField.setCaption("To:");
		endDateField.setImmediate(false);
		endDateField.setWidth("-1px");
		endDateField.setHeight("-1px");
		endDateField.setInvalidAllowed(false);
		mainLayout.addComponent(endDateField);
		mainLayout.setComponentAlignment(endDateField, new Alignment(20));

		// updateButton
		updateButton = new Button();
		updateButton.setCaption("Update Database");
		updateButton.setImmediate(true);
		updateButton.setWidth("-1px");
		updateButton.setHeight("-1px");
		mainLayout.addComponent(updateButton);
		mainLayout.setExpandRatio(updateButton, 1.0f);
		mainLayout.setComponentAlignment(updateButton, new Alignment(20));

		return mainLayout;
	}

}
