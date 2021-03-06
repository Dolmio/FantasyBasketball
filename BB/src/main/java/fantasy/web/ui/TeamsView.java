package fantasy.web.ui;


import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import fantasy.domain.Player;
import fantasy.domain.Team;
public class TeamsView extends CustomComponent implements ContentUpdateable{

	
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table teamTable;
	@AutoGenerated
	private Select teamChoiceSelect;

	private final BeanContainer<Long, Team> teams;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public TeamsView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		//user code
		teams = new BeanContainer<Long, Team>(Team.class);
		teams.setBeanIdProperty("name");
		teamChoiceSelect.setNullSelectionAllowed(false);

		

		//update table when select value is changed
		teamChoiceSelect.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				Team selectedTeam = (Team) teams.getItem(event.getProperty().getValue()).getBean();
				updateTeamTable(selectedTeam);

			}
		});

		


	}

	/**
	 * Makes table showing all players in parameter team.
	 * @param selectedTeam 
	 */
	private void updateTeamTable(Team selectedTeam){
		BeanContainer<Long, Player> players = new BeanContainer<Long, Player>(Player.class);
		players.setBeanIdProperty("id");
		players.addAll(selectedTeam.getPlayers());
		teamTable.setContainerDataSource(players);
		teamTable.setVisibleColumns(
				new String[] { "lastName", "firstName","value", "currentPosition", "possiblePositions"} );
		teamTable.setColumnHeaders(new String[]{"Sukunimi", "Etunimi", "Arvo", "Pelipaikka", "Mahd. Pelipaikat"});
	}

	/**
	 * Updates values in dropdown and selects the first value. Makes table from the first value.
	 */
	public void updateContent(){
		teams.removeAllItems();
		teams.addAll(Team.findAllTeams());
		teamChoiceSelect.setContainerDataSource(teams);
		//select first team
		if(teams.size() != 0){
			Team firstTeam = teams.getItem(teams.getIdByIndex(0)).getBean();
			teamChoiceSelect.select(teams.getIdByIndex(0));
			updateTeamTable(firstTeam);
		}
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// teamChoiceSelect
		teamChoiceSelect = new Select();
		teamChoiceSelect.setCaption("Valitse joukkue:");
		teamChoiceSelect.setImmediate(true);
		teamChoiceSelect.setWidth("-1px");
		teamChoiceSelect.setHeight("-1px");
		mainLayout.addComponent(teamChoiceSelect);
		mainLayout.setComponentAlignment(teamChoiceSelect, new Alignment(20));
		
		// teamTable
		teamTable = new Table();
		teamTable.setImmediate(true);
		teamTable.setWidth("-1px");
		teamTable.setHeight("-1px");
		mainLayout.addComponent(teamTable);
		mainLayout.setExpandRatio(teamTable, 1.0f);
		mainLayout.setComponentAlignment(teamTable, new Alignment(20));
		
		return mainLayout;
	}

}
