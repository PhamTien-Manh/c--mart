package edu.cmart.model.mapper;

import edu.cmart.entity.Promo;
import edu.cmart.model.dto.PromoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PromoMapper implements Function<Promo, PromoDto> {

    @Override
    public PromoDto apply(Promo promo) {
        return PromoDto
                .builder()
                .id(promo.getId())
                .name(promo.getName())
                .image(promo.getImage())
                .description(promo.getDescription())
                .discount(promo.getDiscount())
                .minDiscount(promo.getMinDiscount())
                .maxDiscount(promo.getMaxDiscount())
                .startTime(promo.getStartTime())
                .endTime(promo.getEndTime())
                .quantity(promo.getQuantity())
                .typeDiscount(promo.getTypeDiscount())
                .build();
    }

    public Promo applyToPromo(PromoDto promoDto){
        return Promo
                .builder()
                .id(promoDto.getId())
                .name(promoDto.getName())
                .image(promoDto.getImage())
                .description(promoDto.getDescription())
                .discount(promoDto.getDiscount())
                .minDiscount(promoDto.getMinDiscount())
                .maxDiscount(promoDto.getMaxDiscount())
                .startTime(promoDto.getStartTime())
                .endTime(promoDto.getEndTime())
                .quantity(promoDto.getQuantity())
                .typeDiscount(promoDto.getTypeDiscount())
                .build();
    }
}
