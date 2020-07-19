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
@Table(name = "usersubcomment")
public class UserSubComment extends BaseEntity {

    @Column(name = "comment", nullable = false, columnDefinition = "text")
    private String comment;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;
}
