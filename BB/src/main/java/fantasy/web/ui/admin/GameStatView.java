package fantasy.web.ui.admin;

import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.GameStat.class)
public class GameStatView extends AbstractEntityView<fantasy.domain.GameStat> {
	
	public static final Object[]  VISIBLE_COLUMNS = new Object[]{"dateWhen","points", "rebounds", "assists", "blocks",
																"steals", "turnovers", "fgMade", "fgAttempts", "ftMade", "threePointsMade"};
	
	public static final String[] COLUMN_HEADERS = new String[] {"Date", "Points", "Rebounds", "Assists", "Blocks", "Steals", "Turnovers",
																"FG Made", "FG Attempts", "FT Made", "3Points Made"};
    @Override
    protected EntityEditor createForm() {
        return new GameStatForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(VISIBLE_COLUMNS);
        table.setColumnHeaders(COLUMN_HEADERS);

        setupGeneratedColumns(table);
    }

}
