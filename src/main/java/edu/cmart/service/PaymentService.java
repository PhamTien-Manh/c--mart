package edu.cmart.service;

import edu.cmart.model.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<PaymentDto> findAll();

    PaymentDto findById(Long id);

    PaymentDto save(PaymentDto paymentDto);

    void deleteById(Long id);
}
