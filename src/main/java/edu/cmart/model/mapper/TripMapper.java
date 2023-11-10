package edu.cmart.model.mapper;

import edu.cmart.entity.Payment;
import edu.cmart.entity.Promo;
import edu.cmart.entity.Role;
import edu.cmart.entity.Trip;
import edu.cmart.model.dto.TripDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class TripMapper implements Function<Trip, TripDto> {
    @Override
    public TripDto apply(Trip trip) {
        return TripDto.builder()
                .id(trip.getId())
                .totalMoney(trip.getTotalMoney())
                .distance(trip.getDistance())
                .startTime(trip.getStartTime())
                .finishTime(trip.getFinishTime())
                .receiveTime(trip.getReceiveTime())
                .time(trip.getTime())
                .startLat(trip.getStartLat())
                .startLng(trip.getStartLng())
                .finishLat(trip.getFinishLat())
                .finishLng(trip.getFinishLng())
                .isScheduled(trip.getIsScheduled())
                .typeTrip(trip.getTypeTrip())
                .paymentId(trip.getPayment().getId())
                .promoId(trip.getPromo().getId())
                .roleDriverId(trip.getRoleDriver().getId())
                .roleUserId(trip.getRoleUser().getId())
                .build();
    }

    public Trip applyToTrip(
            TripDto tripDto,
            Payment payment,
            Promo promo,
            Role roleDriver,
            Role roleUser
    ) {
        return Trip.builder()
                .id(tripDto.getId())
                .totalMoney(tripDto.getTotalMoney())
                .distance(tripDto.getDistance())
                .startTime(new Date())
                .finishTime(tripDto.getFinishTime())
                .receiveTime(tripDto.getReceiveTime())
                .time(tripDto.getTime())
                .startLat(tripDto.getStartLat())
                .startLng(tripDto.getStartLng())
                .finishLat(tripDto.getFinishLat())
                .finishLng(tripDto.getFinishLng())
                .isScheduled(tripDto.getIsScheduled())
                .typeTrip(tripDto.getTypeTrip())
                .payment(payment)
                .promo(promo)
                .roleDriver(roleDriver)
                .roleUser(roleUser)
                .build();
    }
}
