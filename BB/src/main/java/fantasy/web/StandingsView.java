package fantasy.web;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import fantasy.domain.Team;

public class StandingsView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table standingsTable;
	@AutoGenerated
	private Label header;




	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */




	




	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */




	




	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */




	




	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */




	




	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */




	




	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */




	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public StandingsView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		updateStandingsView();
		
	}

	public void updateStandingsView(){
		List<Team> teams = Team.findAllTeams();
		BeanContainer<Long, Team> teamContainer = new BeanContainer<Long, Team>(Team.class);
		teamContainer.setBeanIdProperty("id");
		teamContainer.addAll(teams);
		
		standingsTable.setContainerDataSource(teamContainer);
		standingsTable.setVisibleColumns(new Object[] { "name", "winCount", "finishedGameCount"});
		standingsTable.setColumnHeaders(new String[] {"Team", "Wins", "Games"});
		
		//first sort criterias:wins, games, name  wins are in descending order
		standingsTable.sort(new Object[] {"winCount", "gameCount", "name"}, new boolean[]{false, true, true});
		standingsTable.setSortDisabled(true);
		
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
		
		// header
		header = new Label();
		header.setStyleName("standingsHeader");
		header.setImmediate(false);
		header.setWidth("-1px");
		header.setHeight("35px");
		header.setValue("Standings");
		mainLayout.addComponent(header);
		mainLayout.setComponentAlignment(header, new Alignment(20));
		
		// standingsTable
		standingsTable = new Table();
		standingsTable.setImmediate(true);
		standingsTable.setWidth("-1px");
		standingsTable.setHeight("-1px");
		mainLayout.addComponent(standingsTable);
		mainLayout.setComponentAlignment(standingsTable, new Alignment(20));
		
		return mainLayout;
	}

}
