package edu.cmart.model.dto;

import edu.cmart.entity.enums.TypeDiscount;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class PromoDto {

    private Long id;
    private String name;
    private String image;
    private String description;
    private Double discount;
    private Double minDiscount;
    private Double maxDiscount;
    private Date startTime;
    private Date endTime;
    private Integer quantity;
    private TypeDiscount typeDiscount;
}
