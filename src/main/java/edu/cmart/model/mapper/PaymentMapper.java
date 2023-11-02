package edu.cmart.model.mapper;

import edu.cmart.entity.Payment;
import edu.cmart.model.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PaymentMapper implements Function<Payment, PaymentDto> {
    @Override
    public PaymentDto apply(Payment payment) {
        return PaymentDto
                .builder()
                .id(payment.getId())
                .name(payment.getName())
                .description(payment.getDescription())
                .build();
    }

    public Payment applyToPayment(PaymentDto paymentDto){
        return Payment
                .builder()
                .id(paymentDto.getId())
                .name(paymentDto.getName())
                .description(paymentDto.getDescription())
                .build();
    }
}
