// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.PlayerPos;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PlayerPos_Roo_Entity {
    
    declare @type: PlayerPos: @Entity;
    
    @PersistenceContext
    transient EntityManager PlayerPos.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PlayerPos.id;
    
    @Version
    @Column(name = "version")
    private Integer PlayerPos.version;
    
    public Long PlayerPos.getId() {
        return this.id;
    }
    
    public void PlayerPos.setId(Long id) {
        this.id = id;
    }
    
    public Integer PlayerPos.getVersion() {
        return this.version;
    }
    
    public void PlayerPos.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void PlayerPos.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PlayerPos.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PlayerPos attached = PlayerPos.findPlayerPos(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PlayerPos.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PlayerPos.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PlayerPos PlayerPos.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PlayerPos merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager PlayerPos.entityManager() {
        EntityManager em = new PlayerPos().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PlayerPos.countPlayerPoses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PlayerPos o", Long.class).getSingleResult();
    }
    
    public static List<PlayerPos> PlayerPos.findAllPlayerPoses() {
        return entityManager().createQuery("SELECT o FROM PlayerPos o", PlayerPos.class).getResultList();
    }
    
    public static PlayerPos PlayerPos.findPlayerPos(Long id) {
        if (id == null) return null;
        return entityManager().find(PlayerPos.class, id);
    }
    
    public static List<PlayerPos> PlayerPos.findPlayerPosEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PlayerPos o", PlayerPos.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
