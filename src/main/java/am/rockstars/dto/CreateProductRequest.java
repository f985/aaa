package am.rockstars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {

    private String name;

    private String type;

    private String quantity;

    private String description;

    private long createdById;
}
