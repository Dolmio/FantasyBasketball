package fantasy.web.ui.admin;

import fantasy.domain.GameStat;
import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.GameStat.class)
public class GameStatView extends AbstractEntityView<fantasy.domain.GameStat> {
	
	public static final Object[]  VISIBLE_COLUMNS = new Object[]{"player.lastName", "dateWhen","points", "rebounds", "assists", "blocks",
																"steals", "turnovers", "fgMade", "fgAttempts", "ftMade", "threePointsMade"};
	
	public static final String[] COLUMN_HEADERS = new String[] {"Player", "Date", "Points", "Rebounds", "Assists", "Blocks", "Steals", "Turnovers",
																"FG Made", "FG Attempts", "FT Made", "3Points Made"};
    @Override
    protected EntityEditor createForm() {
        return new GameStatForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        setupGeneratedColumns(table);
        table.setVisibleColumns(VISIBLE_COLUMNS);
        table.setColumnHeaders(COLUMN_HEADERS);
    }
    
    // we want to have player's lastname be shown first in the gameStat table so we need to override method in AJ.
    @Override
    public BeanContainer<Long, GameStat> getTableContainer() {
        BeanContainer<Long, GameStat> container = new BeanContainer<Long, GameStat>(GameStat.class);
        container.setBeanIdProperty("id");
        //user added
        container.addNestedContainerProperty("player.lastName");
        //
        for (GameStat entity : GameStat.findAllGameStats()) {
            container.addBean(entity);
        }
        return container;
    }

}
