package fantasy.web;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.OptimisticLockException;

import org.joda.time.LocalDate;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.transaction.TransactionSystemException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.VerticalLayout;

import fantasy.domain.Round;
import fantasy.util.DataScraper;
import fantasy.util.DataUpdater;

import com.vaadin.ui.Window;
public class UpdateView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button updateWinsButton;
	@AutoGenerated
	private Select roundSelectWins;
	@AutoGenerated
	private Button roundUpdateButton;
	@AutoGenerated
	private Select roundSelect;
	@AutoGenerated
	private Button updateButton;
	@AutoGenerated
	private PopupDateField endDateField;
	@AutoGenerated
	private PopupDateField startDateField;

	

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
					final Window main = (Window)(getParent().getParent().getParent().getParent().getParent().getParent());
					DataScraper scraper = new DataScraper();
					LocalDate startDate = new LocalDate(((Date)startDateField.getValue()).getTime());
					LocalDate endDate = new LocalDate(((Date)endDateField.getValue()).getTime());
					
					try{
						scraper.updateStats(startDate, endDate);
						main.showNotification("Update succesful!");
					}
					catch(IOException e){
						main.showNotification(e.getMessage());
					}
					
					
					
				}

			}
		});
		
		
		roundUpdateButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(roundSelect.getValue() != null){
					final Window main = (Window)(getParent().getParent().getParent().getParent().getParent().getParent());
					DataUpdater dataUpdater = new DataUpdater();
					
					Round selectedRound = ((BeanItem<Round>) (roundSelect.getContainerDataSource().getItem(roundSelect.getValue()))).getBean();
					try{
						dataUpdater.updateAllRoundTotals(selectedRound);
						dataUpdater.updateRoundLeaguePoints(selectedRound);
						
						main.showNotification("Update succesful!");
					}
					catch(JpaOptimisticLockingFailureException e){
						main.showNotification("Something went wrong. Please try again. " + e.getCause().getMessage());
					}
				}
				
			}
		});
		
		updateWinsButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(roundSelectWins.getValue() != null){
					final Window main = (Window)(getParent().getParent().getParent().getParent().getParent().getParent());
					DataUpdater dataUpdater = new DataUpdater();
					Round selectedRound = ((BeanItem<Round>) (roundSelectWins.getContainerDataSource().getItem(roundSelectWins.getValue()))).getBean();
					try{
						dataUpdater.updateWins(selectedRound);
						main.showNotification("Update succesful!");
					}
					catch(JpaOptimisticLockingFailureException e){
						main.showNotification("Something went wrong. Please try again. " + e.getCause().getMessage());
					}
					
				}
				
			}
		});
		
		
	}
	
	public void updateUpdateView(){
		roundSelect.removeAllItems();
		List<Round> rounds = Round.findAllRounds();
		BeanContainer<String, Round> roundContainer = new BeanContainer<String, Round>(Round.class);
		roundContainer.setBeanIdProperty("name");
		roundContainer.addAll(rounds);
		roundSelect.setContainerDataSource(roundContainer);
		roundSelectWins.setContainerDataSource(roundContainer);
		System.out.println("UpdateView" + rounds.size());
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
		
		// endDateField
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
		updateButton.setCaption("Update player stats");
		updateButton.setImmediate(true);
		updateButton.setWidth("-1px");
		updateButton.setHeight("-1px");
		mainLayout.addComponent(updateButton);
		mainLayout.setComponentAlignment(updateButton, new Alignment(20));
		
		// roundSelect
		roundSelect = new Select();
		roundSelect.setImmediate(false);
		roundSelect.setWidth("-1px");
		roundSelect.setHeight("24px");
		mainLayout.addComponent(roundSelect);
		mainLayout.setComponentAlignment(roundSelect, new Alignment(20));
		
		// roundUpdateButton
		roundUpdateButton = new Button();
		roundUpdateButton.setCaption("Update League Points in Round");
		roundUpdateButton.setImmediate(true);
		roundUpdateButton.setWidth("-1px");
		roundUpdateButton.setHeight("-1px");
		mainLayout.addComponent(roundUpdateButton);
		mainLayout.setComponentAlignment(roundUpdateButton, new Alignment(20));
		
		// roundSelectWins
		roundSelectWins = new Select();
		roundSelectWins.setImmediate(true);
		roundSelectWins.setWidth("-1px");
		roundSelectWins.setHeight("-1px");
		mainLayout.addComponent(roundSelectWins);
		mainLayout.setComponentAlignment(roundSelectWins, new Alignment(20));
		
		// updateWinsButton
		updateWinsButton = new Button();
		updateWinsButton.setCaption("Update Wins to Round");
		updateWinsButton.setImmediate(true);
		updateWinsButton.setWidth("-1px");
		updateWinsButton.setHeight("-1px");
		mainLayout.addComponent(updateWinsButton);
		mainLayout.setExpandRatio(updateWinsButton, 1.0f);
		mainLayout.setComponentAlignment(updateWinsButton, new Alignment(20));
		
		return mainLayout;
	}

}
