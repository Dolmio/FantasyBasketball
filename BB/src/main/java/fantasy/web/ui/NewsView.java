package fantasy.web.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fantasy.domain.News;

public class NewsView extends CustomComponent implements ContentUpdateable {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Label textContent;
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public NewsView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	
	}
	
	
	public void updateContent(){
		if(News.countNews() != 0){
			News news = News.findNewsEntries(0, 1).get(0);
			textContent.setContentMode(Label.CONTENT_XHTML);
			textContent.setValue(news.getText());
			
		}
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
		
		// textContent
		textContent = new Label();
		textContent.setImmediate(false);
		textContent.setWidth("-1px");
		textContent.setHeight("-1px");
		textContent.setValue("Label");
		mainLayout.addComponent(textContent);
		mainLayout.setComponentAlignment(textContent, new Alignment(20));
		
		return mainLayout;
	}


	

}
