package edu.cmart.model.dto;

import edu.cmart.entity.enums.TypeDiscount;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@ToString
@Data
public class PromoDto {

    private Long id;
    @NonNull
    private String name;
    private String image;
    @NonNull
    private String description;
    @NonNull
    private Double discount;
    @NonNull
    private Double minDiscount;
    @NonNull
    private Double maxDiscount;
    @NonNull
    private Date startTime;
    @NonNull
    private Date endTime;
    @NonNull
    private Integer quantity;
    @NonNull
    private TypeDiscount typeDiscount;
}
