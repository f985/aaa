package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "blogauthor")
public class BlogAuthor extends BaseEntity {

    @Column(name = "bio", nullable = false, columnDefinition = "text")
    private String bio;

    @Column(name = "post_date", nullable = false)
    private String postDate;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(mappedBy = "blogAuthor")
//    private List<Blog> blogs;
}
