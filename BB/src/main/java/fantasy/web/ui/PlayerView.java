package fantasy.web.ui;

import fantasy.web.AbstractEntityView;
import fantasy.web.EntityEditor;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Player.class)
public class PlayerView extends AbstractEntityView<fantasy.domain.Player> {

    @Override
    protected EntityEditor createForm() {
        return new PlayerForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        table.setVisibleColumns(getTableColumns());

        setupGeneratedColumns(table);
    }
    
    public void setupGeneratedColumns(Table table){
    	
    }

}
