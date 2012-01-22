package fantasy.domain;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import fantasy.domain.authentication.Role;

@RooJavaBean
@RooToString
@RooEntity
public class UserClass implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	@Enumerated(EnumType.STRING)
    @NotNull
    private Role userRole = Role.VISITOR;

    @ManyToOne
    private Team team;
    
    
    private String password;
}
