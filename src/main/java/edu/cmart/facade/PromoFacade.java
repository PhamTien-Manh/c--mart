package edu.cmart.facade;

import edu.cmart.exception.common.IdMustBeNullException;
import edu.cmart.exception.common.InvalidParamException;
import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.exception.entity.EntityNotFoundException;
import edu.cmart.model.dto.PromoDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoFacade {

    private final PromoService promoService;

    public Page<PromoDto> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<PromoDto> promos = promoService.findAll(searchCriteria);
        if(promos.isEmpty()){
            throw new EntityNotFoundException();
        }
        return promos;
    }

    public Page<PromoDto> findAllByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        if (name.isEmpty())
            throw new InvalidParamException();
        Page<PromoDto> promos = promoService.findAllByName(name, searchCriteria);
        if(promos.isEmpty()){
            throw new EntityNotFoundException();
        }
        return promos;
    }

    public PromoDto create(PromoDto promoDto) throws ArchitectureException {
        if(promoDto.getId() != null)
            throw new IdMustBeNullException();
        return promoService.save(promoDto);
    }

    public PromoDto update(Long promoId, PromoDto promoDto) throws ArchitectureException {
        findById(promoId);
        promoDto.setId(promoId);
        return promoService.save(promoDto);
    }

    public PromoDto findById(Long promoId) throws ArchitectureException {
        PromoDto promoDto = promoService.findById(promoId);
        if(promoDto == null)
            throw new EntityNotFoundException();
        return promoDto;
    }

    public void deleteById(Long promoId) throws ArchitectureException {
        findById(promoId);
        promoService.deleteById(promoId);
    }
}
