package fantasy.web.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class PlayerDetailsComponent extends CustomComponent {

	
	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private Label turnoversValue;
	@AutoGenerated
	private Label lnTurnovers;
	@AutoGenerated
	private Label ftmValue;
	@AutoGenerated
	private Label lnFtMade;
	@AutoGenerated
	private Label threePointsInValue;
	@AutoGenerated
	private Label ln3PointsMade;
	@AutoGenerated
	private Label fgPercentageValue;
	@AutoGenerated
	private Label lnFgPercentage;
	@AutoGenerated
	private Label fgaValue;
	@AutoGenerated
	private Label lnFgAttempts;
	@AutoGenerated
	private Label fgMadeValue;
	@AutoGenerated
	private Label lnFgMade;
	@AutoGenerated
	private Label stealValue;
	@AutoGenerated
	private Label lnSteals;
	@AutoGenerated
	private Label blkValue;
	@AutoGenerated
	private Label lnBlock;
	@AutoGenerated
	private Label assValue;
	@AutoGenerated
	private Label lnAss;
	@AutoGenerated
	private Label rebValue;
	@AutoGenerated
	private Label lNReb;
	@AutoGenerated
	private Label ptsValue;
	@AutoGenerated
	private Label lNPts;
	@AutoGenerated
	private Label label_1_Value;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private Label header;

	

	private static final long serialVersionUID = 1L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public PlayerDetailsComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}
	
	/**
	 * Sets values to all the labels with the values given in the parameter. Order is descending from the view point of view.
	 * @param values
	 */
	public void updateStatLabels(String[] values){
		if(values.length != 11){
			return;
		}
		ptsValue.setValue(values[0]);
		rebValue.setValue(values[1]);
		assValue.setValue(values[2]);
		blkValue.setValue(values[3]);
		stealValue.setValue(values[4]);
		fgMadeValue.setValue(values[5]);
		fgaValue.setValue(values[6]);
		fgPercentageValue.setValue(values[7]);
		threePointsInValue.setValue(values[8]);
		ftmValue.setValue(values[9]);
		turnoversValue.setValue(values[10]);
		
	}
	
	public void setHeader(String txt){
		header.setValue(txt);
	}
	
	/**
	 * set values for the second row labels
	 * @param label
	 * @param value
	 */
	public void setFirstStatLabel(String label, String value){
		label_1.setValue(label);
		label_1_Value.setValue(value);
		
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		mainLayout.setColumns(2);
		mainLayout.setRows(13);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("100.0%");
		
		// header
		header = new Label();
		header.setStyleName("playerDetailsHeader");
		header.setImmediate(false);
		header.setWidth("-1px");
		header.setHeight("-1px");
		header.setValue("Edellinen ottelu:");
		mainLayout.addComponent(header, 0, 0);
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Label");
		mainLayout.addComponent(label_1, 0, 1);
		
		// label_1_Value
		label_1_Value = new Label();
		label_1_Value.setImmediate(false);
		label_1_Value.setWidth("-1px");
		label_1_Value.setHeight("-1px");
		label_1_Value.setValue("Label");
		mainLayout.addComponent(label_1_Value, 1, 1);
		
		// lNPts
		lNPts = new Label();
		lNPts.setImmediate(false);
		lNPts.setWidth("-1px");
		lNPts.setHeight("-1px");
		lNPts.setValue("Pisteet:");
		mainLayout.addComponent(lNPts, 0, 2);
		
		// ptsValue
		ptsValue = new Label();
		ptsValue.setImmediate(false);
		ptsValue.setWidth("-1px");
		ptsValue.setHeight("-1px");
		ptsValue.setValue("Value");
		mainLayout.addComponent(ptsValue, 1, 2);
		
		// lNReb
		lNReb = new Label();
		lNReb.setImmediate(false);
		lNReb.setWidth("-1px");
		lNReb.setHeight("-1px");
		lNReb.setValue("Levypallot:");
		mainLayout.addComponent(lNReb, 0, 3);
		
		// rebValue
		rebValue = new Label();
		rebValue.setImmediate(false);
		rebValue.setWidth("-1px");
		rebValue.setHeight("-1px");
		rebValue.setValue("Value");
		mainLayout.addComponent(rebValue, 1, 3);
		
		// lnAss
		lnAss = new Label();
		lnAss.setImmediate(false);
		lnAss.setWidth("-1px");
		lnAss.setHeight("-1px");
		lnAss.setValue("Syötöt");
		mainLayout.addComponent(lnAss, 0, 4);
		
		// assValue
		assValue = new Label();
		assValue.setImmediate(false);
		assValue.setWidth("-1px");
		assValue.setHeight("-1px");
		assValue.setValue("Value");
		mainLayout.addComponent(assValue, 1, 4);
		
		// lnBlock
		lnBlock = new Label();
		lnBlock.setImmediate(false);
		lnBlock.setWidth("-1px");
		lnBlock.setHeight("-1px");
		lnBlock.setValue("Torjunnat:");
		mainLayout.addComponent(lnBlock, 0, 5);
		
		// blkValue
		blkValue = new Label();
		blkValue.setImmediate(false);
		blkValue.setWidth("-1px");
		blkValue.setHeight("-1px");
		blkValue.setValue("Value");
		mainLayout.addComponent(blkValue, 1, 5);
		
		// lnSteals
		lnSteals = new Label();
		lnSteals.setImmediate(false);
		lnSteals.setWidth("-1px");
		lnSteals.setHeight("-1px");
		lnSteals.setValue("Riistot:");
		mainLayout.addComponent(lnSteals, 0, 6);
		
		// stealValue
		stealValue = new Label();
		stealValue.setImmediate(false);
		stealValue.setWidth("-1px");
		stealValue.setHeight("-1px");
		stealValue.setValue("Value");
		mainLayout.addComponent(stealValue, 1, 6);
		
		// lnFgMade
		lnFgMade = new Label();
		lnFgMade.setImmediate(false);
		lnFgMade.setWidth("-1px");
		lnFgMade.setHeight("-1px");
		lnFgMade.setValue("Heitot sisään:");
		mainLayout.addComponent(lnFgMade, 0, 7);
		
		// fgMadeValue
		fgMadeValue = new Label();
		fgMadeValue.setImmediate(false);
		fgMadeValue.setWidth("-1px");
		fgMadeValue.setHeight("-1px");
		fgMadeValue.setValue("Value");
		mainLayout.addComponent(fgMadeValue, 1, 7);
		
		// lnFgAttempts
		lnFgAttempts = new Label();
		lnFgAttempts.setImmediate(false);
		lnFgAttempts.setWidth("-1px");
		lnFgAttempts.setHeight("-1px");
		lnFgAttempts.setValue("Heittoyritykset:");
		mainLayout.addComponent(lnFgAttempts, 0, 8);
		
		// fgaValue
		fgaValue = new Label();
		fgaValue.setImmediate(false);
		fgaValue.setWidth("-1px");
		fgaValue.setHeight("-1px");
		fgaValue.setValue("Value");
		mainLayout.addComponent(fgaValue, 1, 8);
		
		// lnFgPercentage
		lnFgPercentage = new Label();
		lnFgPercentage.setImmediate(false);
		lnFgPercentage.setWidth("-1px");
		lnFgPercentage.setHeight("-1px");
		lnFgPercentage.setValue("Heittoprosentti:");
		mainLayout.addComponent(lnFgPercentage, 0, 9);
		
		// fgPercentageValue
		fgPercentageValue = new Label();
		fgPercentageValue.setImmediate(false);
		fgPercentageValue.setWidth("-1px");
		fgPercentageValue.setHeight("-1px");
		fgPercentageValue.setValue("Value");
		mainLayout.addComponent(fgPercentageValue, 1, 9);
		
		// ln3PointsMade
		ln3PointsMade = new Label();
		ln3PointsMade.setImmediate(false);
		ln3PointsMade.setWidth("-1px");
		ln3PointsMade.setHeight("22px");
		ln3PointsMade.setValue("Kolmoset sisään:");
		mainLayout.addComponent(ln3PointsMade, 0, 10);
		
		// threePointsInValue
		threePointsInValue = new Label();
		threePointsInValue.setImmediate(false);
		threePointsInValue.setWidth("-1px");
		threePointsInValue.setHeight("-1px");
		threePointsInValue.setValue("Value");
		mainLayout.addComponent(threePointsInValue, 1, 10);
		
		// lnFtMade
		lnFtMade = new Label();
		lnFtMade.setImmediate(false);
		lnFtMade.setWidth("-1px");
		lnFtMade.setHeight("-1px");
		lnFtMade.setValue("Vapaaheitot sisään:");
		mainLayout.addComponent(lnFtMade, 0, 11);
		
		// ftmValue
		ftmValue = new Label();
		ftmValue.setImmediate(false);
		ftmValue.setWidth("-1px");
		ftmValue.setHeight("-1px");
		ftmValue.setValue("Value");
		mainLayout.addComponent(ftmValue, 1, 11);
		
		// lnTurnovers
		lnTurnovers = new Label();
		lnTurnovers.setImmediate(false);
		lnTurnovers.setWidth("-1px");
		lnTurnovers.setHeight("-1px");
		lnTurnovers.setValue("Menetykset");
		mainLayout.addComponent(lnTurnovers, 0, 12);
		
		// turnoversValue
		turnoversValue = new Label();
		turnoversValue.setImmediate(false);
		turnoversValue.setWidth("-1px");
		turnoversValue.setHeight("-1px");
		turnoversValue.setValue("Value");
		mainLayout.addComponent(turnoversValue, 1, 12);
		
		return mainLayout;
	}

}