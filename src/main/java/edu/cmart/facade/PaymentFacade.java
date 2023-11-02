package edu.cmart.facade;


import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.PaymentDto;
import edu.cmart.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;

    public List<PaymentDto> findAll() throws ArchitectureException{
        List<PaymentDto> paymentDtoList = paymentService.findAll();
        if(paymentDtoList.isEmpty()){
            throw new EntityNotFoundException();
        }
        return paymentDtoList;
    }

    public PaymentDto findById(Long id) throws ArchitectureException{
        PaymentDto paymentDto = paymentService.findById(id);
        if(paymentDto == null){
            throw new EntityNotFoundException();
        }
        return paymentDto;
    }

    public PaymentDto create(PaymentDto paymentDto) throws ArchitectureException{
        if(paymentDto.getId() != null){
            throw new IdMustBeNullException();
        }
        return paymentService.save(paymentDto);
    }

    public PaymentDto update(PaymentDto paymentDto, Long paymentId) throws ArchitectureException{
        findById(paymentId);
        paymentDto.setId(paymentId);
        return paymentService.save(paymentDto);
    }

    public void deleteById(Long id) throws ArchitectureException{
        findById(id);
        paymentService.deleteById(id);
    }
}
