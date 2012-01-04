package fantasy.web;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.VerticalLayout;

import fantasy.domain.authentication.Role;

public class TabPanel extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TabSheet tabSheet_1;
	@AutoGenerated
	private UpdateView updateView_1;
	@AutoGenerated
	private PlayersView playersView_1;
	@AutoGenerated
	private OwnTeamView ownTeamView;
	@AutoGenerated
	private TeamsView teamsView_1;
	@AutoGenerated
	private FantasyEntityManagerView fantasyEntityManagerView_1;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

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
	public TabPanel() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		
		Role currentRole = FantasyApplication.getInstance().getCurrentUser().getUserRole();
		if(currentRole != Role.ADMIN){
			tabSheet_1.removeTab(tabSheet_1.getTab(fantasyEntityManagerView_1));
		}
		
		if(currentRole != Role.MANAGER){
			tabSheet_1.removeTab(tabSheet_1.getTab(ownTeamView));
		}
		
		//update TeamsView when loading it
		tabSheet_1.addListener(new SelectedTabChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				
				if(tabSheet_1.getSelectedTab() instanceof TeamsView){
					teamsView_1.updateTeamsView();
				}
				else if(tabSheet_1.getSelectedTab() instanceof OwnTeamView){
					ownTeamView.updateOwnTeamView();
				}
				else if(tabSheet_1.getSelectedTab() instanceof PlayersView){
					playersView_1.updatePlayersView();
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
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// tabSheet_1
		tabSheet_1 = buildTabSheet_1();
		mainLayout.addComponent(tabSheet_1);
		
		return mainLayout;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_1() {
		// common part: create layout
		tabSheet_1 = new TabSheet();
		tabSheet_1.setImmediate(true);
		tabSheet_1.setWidth("100.0%");
		tabSheet_1.setHeight("-1px");
		
		// fantasyEntityManagerView_1
		fantasyEntityManagerView_1 = new FantasyEntityManagerView();
		fantasyEntityManagerView_1.setImmediate(false);
		fantasyEntityManagerView_1.setWidth("1000px");
		fantasyEntityManagerView_1.setHeight("1000px");
		tabSheet_1.addTab(fantasyEntityManagerView_1, "Admin", null);
		
		// teamsView_1
		teamsView_1 = new TeamsView();
		teamsView_1.setImmediate(false);
		teamsView_1.setWidth("100.0%");
		teamsView_1.setHeight("-1px");
		tabSheet_1.addTab(teamsView_1, "Teams", null);
		
		// ownTeamView
		ownTeamView = new OwnTeamView();
		ownTeamView.setImmediate(false);
		ownTeamView.setWidth("100.0%");
		ownTeamView.setHeight("-1px");
		tabSheet_1.addTab(ownTeamView, "Own Team", null);
		
		// playersView_1
		playersView_1 = new PlayersView();
		playersView_1.setImmediate(false);
		playersView_1.setWidth("100.0%");
		playersView_1.setHeight("-1px");
		tabSheet_1.addTab(playersView_1, "Players", null);
		
		// updateView_1
		updateView_1 = new UpdateView();
		updateView_1.setImmediate(false);
		updateView_1.setWidth("100.0%");
		updateView_1.setHeight("-1px");
		tabSheet_1.addTab(updateView_1, "Tab", null);
		
		return tabSheet_1;
	}

}
