package am.rockstars.dto.header.edit;

import am.rockstars.dto.header.BaseHeader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
abstract class BaseHeaderEdit extends BaseHeader {
    private Long id;

    @Min(1)
    @Max(50)
    private Integer orderNumber;

    @NotNull
    private Boolean disabled;
}
