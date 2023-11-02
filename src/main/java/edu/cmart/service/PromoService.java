package edu.cmart.service;

import edu.cmart.model.dto.PromoDto;
import edu.cmart.model.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PromoService {

    Page<PromoDto> findAll(SearchCriteria searchCriteria);
    Page<PromoDto> findAllByName(String name, SearchCriteria searchCriteria);
    PromoDto save(PromoDto promoDto);
    PromoDto findById(Long id);
    void deleteById(Long id);


}
