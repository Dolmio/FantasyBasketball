package fantasy.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.authentication.Role;
import fantasy.domain.positions.PlayerPosition;

import javax.persistence.Enumerated;
import fantasy.domain.Team;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity
public class UserClass {
	
	
    private String username;
	
	@Enumerated
    @NotNull
    private Role userRole = Role.VISITOR;

    @ManyToOne
    private Team team;
    
    
    private String password;
}