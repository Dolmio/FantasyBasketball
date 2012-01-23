package fantasy.web.ui.admin;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

import fantasy.domain.Game;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Game.class)
public class GameView extends AbstractEntityView<fantasy.domain.Game> {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected EntityEditor createForm() {
		return new GameForm();
	}

	@Override
	protected void configureTable(Table table) {
		table.setContainerDataSource(getTableContainer());
		table.setVisibleColumns(new Object[] {"id", "awayTeam", "homeTeam", "winner"});

		setupGeneratedColumns(table);
	}
	/**
	 * Method saves entity and manages bidirectional relationships so they are also updated.
	 * @return
	 */
	@Override
	public boolean doCommit() {
		try {
			getForm().commit();
			Game game = (Game) getEntityForItem(getForm().getItemDataSource());
			game.saveEntity();
			return true;
		} catch (InvalidValueException e) {
			// show validation error also on the save button
			getForm().setCommitErrorMessage(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Method deletes entity and manages bidirectional relationships so they are also updated.
	 * @return
	 **/
	@Override
	public void doDelete() {
		Game game = (Game) getEntityForItem(getForm().getItemDataSource());   
		game.deleteEntity();
	}

}
