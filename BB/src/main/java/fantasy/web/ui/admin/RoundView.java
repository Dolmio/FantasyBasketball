package fantasy.web.ui.admin;

import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Round.class)
public class RoundView extends AbstractEntityView<fantasy.domain.Round> {

	private static final long serialVersionUID = 1L;

	@Override
    protected EntityEditor createForm() {
        return new RoundForm();
    }

    @Override
    protected void configureTable(Table table) {
        table.setContainerDataSource(getTableContainer());
        setupGeneratedColumns(table);
        table.setVisibleColumns(new Object[] {"id", "name", "startDate", "endDate", "gameCount" });
        table.setColumnHeaders(new String[] {"Id", "Name", "Start", "End", "Games"});
    }

}
