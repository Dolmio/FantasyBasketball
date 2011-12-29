package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Team.class)
public class TeamView extends AbstractEntityView<fantasy.domain.Team> {

    @Override
    protected EntityEditor createForm() {
    	EntityEditor editor = new TeamForm();
    	
    	return new TeamForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(new Object[]{"name", "players"});
        table.setColumnHeaders(new String[] {"Name", "Players"});

        setupGeneratedColumns(table);
    }

}
