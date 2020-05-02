package am.rockstars.entity;

import am.rockstars.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String quantity;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private User createdBy;
}
