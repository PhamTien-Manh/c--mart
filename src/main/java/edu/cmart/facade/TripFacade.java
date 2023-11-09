package edu.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import edu.cmart.entity.Payment;
import edu.cmart.entity.Promo;
import edu.cmart.entity.Role;
import edu.cmart.exception.common.ForeignKeyIsNotFound;
import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.model.dto.ServiceCarDto;
import edu.cmart.model.dto.TripDto;
import edu.cmart.model.request.DistanceRequest;
import edu.cmart.model.response.TripSaveResponse;
import edu.cmart.repository.PaymentRepository;
import edu.cmart.repository.PromoRepository;
import edu.cmart.repository.RoleRepository;
import edu.cmart.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TripFacade {

    private final TripService tripService;
    private final ServiceCarFacade serviceCarFacade;
    private final GoogleMapFacade googleMapFacade;
    private final PaymentRepository paymentRepository;
    private final RoleRepository roleRepository;
    private final PromoRepository promoRepository;

    public TripDto getTripByServiceCarAndLatLng(DistanceRequest distanceRequest,
                                                Long serviceCarId
    ) throws ArchitectureException, IOException, InterruptedException, ApiException {

        ServiceCarDto serviceCarDto = serviceCarFacade.findById(serviceCarId);
        DistanceMatrix distanceMatrix = googleMapFacade.getDistance(distanceRequest);

        TripDto trip = tripService
                .getTripByServiceCarAndLatLng(serviceCarDto, distanceMatrix, distanceRequest);
        if (trip == null) {
            throw new InvalidParamException();
        }
        return trip;
    }

    public TripSaveResponse create(TripDto tripDto, Long serviceCarId)
            throws ArchitectureException, IOException, InterruptedException, ApiException {

        if(tripDto.getId() != null){
            throw new IdMustBeNullException();
        }

        Payment payment = paymentRepository.findById(tripDto.getPaymentId()).orElse(null);
        Role roleUser = roleRepository.findById(tripDto.getRoleUserId()).orElse(null);
        Promo promo = promoRepository.findById(tripDto.getPromoId()).orElse(null);

        if (payment == null || roleUser == null) {
            throw new ForeignKeyIsNotFound("PaymentId or RoleDriverId or RoleUserId or Promo");
        }

        return tripService.save(tripDto, payment, promo, roleUser, serviceCarId);
    }
}
