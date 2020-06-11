package am.rockstars.entity;

import am.rockstars.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "about_information")
public class AboutInformation extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "heading", nullable = false, columnDefinition = "text")
    private String heading;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "sub_heading", nullable = false, columnDefinition = "text")
    private String subHeading;
}
