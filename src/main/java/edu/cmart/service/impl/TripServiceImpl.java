package edu.cmart.service.impl;

import com.google.maps.model.DistanceMatrix;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.model.dto.TripDto;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    @Override
    public TripDto getTripByServiceCarAndLatLng(
            ServiceCarDto serviceCarDto,
            DistanceMatrix distanceMatrix,
            DistanceRequest distanceRequest) {

        Long meters = distanceMatrix.rows[0].elements[0].distance.inMeters;
        Long seconds = distanceMatrix.rows[0].elements[0].duration.inSeconds;

        Double kilometers = meters / 1000.0;
        Double minutes = seconds / 60.0;

        Double totalMoney = totalMoney(serviceCarDto, kilometers, minutes);

        TripDto tripDto = new TripDto();
        tripDto.setFinishLng(distanceRequest.getFinishLng());
        tripDto.setFinishLat(distanceRequest.getFinishLat());
        tripDto.setStartLng(distanceRequest.getStartLng());
        tripDto.setStartLat(distanceRequest.getStartLat());
        tripDto.setDistance(kilometers);
        tripDto.setTime(Math.round(minutes * 100.0) / 100.0);
        tripDto.setTotalMoney(totalMoney);
        return tripDto;
    }


    // Nhận vào dịch vụ cần tính và số km, số phút
    // Tính thời gian ngắn nhất để chạy số km cố định của dịch vụ
    // Từ thời gian ngắn nhất đó suy ra thời gian biến động và số km biến động
    // Tính tổng tiền dựa trên số tiền cố định và km biến động và thời gian biến động
    public Double totalMoney(ServiceCarDto serviceCarDto, Double kilometers, Double minutes){
        Double timeOf1Km = minutes / kilometers;
        Double minTime = timeOf1Km * serviceCarDto.getFixedKm();
        Double fluctuatesKm = kilometers - serviceCarDto.getFixedKm();
        Double fluctuatesTime = minutes - minTime;

        Double totalMoney =
                serviceCarDto.getFixedPrice() +
                serviceCarDto.getPriceFluctuates() * fluctuatesKm +
                serviceCarDto.getTimeFluctuates() * fluctuatesTime ;
        return Math.round(totalMoney * 100.0) / 100.0;
    }
}
