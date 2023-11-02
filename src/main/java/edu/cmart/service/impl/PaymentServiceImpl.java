package edu.cmart.service.impl;

import edu.cmart.model.dto.PaymentDto;
import edu.cmart.model.mapper.PaymentMapper;
import edu.cmart.repository.PaymentRepository;
import edu.cmart.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> findAll() {
        return paymentRepository.findAll().stream().map(paymentMapper::apply).toList();
    }

    @Override
    public PaymentDto findById(Long id) {
        return paymentRepository.findById(id).map(paymentMapper::apply).orElse(null);
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        return paymentMapper.apply(paymentRepository.save(paymentMapper.applyToPayment(paymentDto)));
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
