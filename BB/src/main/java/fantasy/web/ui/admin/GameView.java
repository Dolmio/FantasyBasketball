package fantasy.web.ui.admin;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import fantasy.domain.Game;
import fantasy.web.ui.admin.AbstractEntityView;
import fantasy.web.ui.admin.EntityEditor;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.spring.roo.addon.annotations.RooVaadinEntityView;
import com.vaadin.ui.Table;

@RooVaadinEntityView(formBackingObject = fantasy.domain.Game.class)
public class GameView extends AbstractEntityView<fantasy.domain.Game> {

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
	@Transactional
	public boolean doCommit() {
		try {
			getForm().commit();
			Game game = (Game) getEntityForItem(getForm().getItemDataSource());
			
			EntityManager em = game.giveEntityManager();
			//we have to make merging in two parts because when we first merge game savedGame 
			//doesn't have information about updates made to reference relationships because they
			//aren't yet part of the Persistence context. We can't also merge references first because
			//game doesn't have id yet.
			
			Game savedGame = em.merge(game);
			em.flush();
			
			
			game.setId(savedGame.getId());
			game.setVersion(savedGame.getVersion());
			if(game.getAwayTeam() != null){
				em.merge(game.getAwayTeam());
			}
			if(game.getHomeTeam() != null){
				em.merge(game.getHomeTeam().merge());
			}
			if(game.getRound() != null){
				em.merge(game.getRound().merge());
			}
		
			em.flush();
			
			

			return true;
		} catch (InvalidValueException e) {
			// show validation error also on the save button
			getForm().setCommitErrorMessage(e.getMessage());
			return false;
		}
	}
	
	
	 public void doDelete() {
	     Game game = (Game) getEntityForItem(getForm().getItemDataSource());   
		 //remove refrences to this entity before deleting
	     if(game.getAwayTeam() != null){
			 game.getAwayTeam().removeAwayGame(game);
			 game.getAwayTeam().merge();
		 }
		 if(game.getHomeTeam() != null){
			 game.getHomeTeam().removeHomeGame(game);
			 game.getHomeTeam().merge();
		 }
		 if(game.getRound() != null){
			 game.getRound().removeGame(game);
			 game.getRound().merge();
		 }
			 
	     deleteEntity(game);
	    }

}
