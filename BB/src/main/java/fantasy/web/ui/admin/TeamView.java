package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Team.class)
public class TeamView extends AbstractEntityView<fantasy.domain.Team> {

   private static final long serialVersionUID = 1L;

	@Override
    protected EntityEditor createForm() {
    	return new TeamForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        

        setupGeneratedColumns(table);
        table.setVisibleColumns(new Object[]{"name", "playerCount", "roundCount", "gameCount"});
        table.setColumnHeaders(new String[] {"Name", "Players", "Rounds", "Games"});
    }
    
    
}
