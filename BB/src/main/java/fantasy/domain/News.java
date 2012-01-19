package fantasy.domain;

import java.io.Serializable;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
@RooJavaBean
@RooToString
@RooEntity
public class News implements Serializable{
	
	@Lob
	@NotNull
    private String text = "";
}
