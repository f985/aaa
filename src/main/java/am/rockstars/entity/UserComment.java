package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter()
@Setter
@Entity
@Table(name = "usercomment")
public class UserComment extends BaseEntity {

    @Column(name = "comment", nullable = false, columnDefinition = "text")
    private String comment;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usercomment_id")
    private List<UserSubComment> subComments;

    public void addSubComment(final UserSubComment userSubComment) {
        if (this.getSubComments() == null) {
            this.setSubComments(new ArrayList<>());
        }
        this.getSubComments().add(userSubComment);
    }
}
