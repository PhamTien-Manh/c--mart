package edu.cmart.controller;

import edu.cmart.exception.core.ArchitectureException;
import edu.cmart.facade.AccountFacade;
import edu.cmart.model.dto.AccountDto;
import edu.cmart.model.dto.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final AccountFacade accountFacade;

    @GetMapping("/demo")
    public Page<AccountDto> demo(
            @RequestParam(defaultValue = "1") Integer size,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "id") String columSort
    ) throws ArchitectureException {
        return accountFacade.findAllByRole("ADMIN", new SearchCriteria(page, size, columSort));
    }
}

