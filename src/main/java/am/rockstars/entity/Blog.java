package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "blog")
public class Blog extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "banner_img", nullable = false)
    private String bannerImg;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "post_type", nullable = false)
    private String postType;

    @Column(name = "short_content", nullable = false, columnDefinition = "text")
    private String shortContent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private List<UserComment> userComments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private List<Tag> tags;

    @ManyToOne
    private BlogAuthor author;

    public void addComment(final UserComment userComment) {
        if (this.getUserComments() == null) {
            this.setUserComments(new ArrayList<>());
        }
        this.getUserComments().add(userComment);
    }
}
