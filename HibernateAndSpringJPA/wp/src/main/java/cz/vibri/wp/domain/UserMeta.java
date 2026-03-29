package cz.vibri.wp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "wp_usermeta")
public class UserMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "umeta_id")
    private Long id;
//    private Long userId;
    @ManyToOne
    private User user;

    @Size(max = 255)
    private String metaKey;

    @Column(columnDefinition = "CLOB")
    private String metaValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }
}
