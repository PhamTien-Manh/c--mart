package edu.cmart.service;

import edu.cmart.entity.Account;
import edu.cmart.model.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface EncodePassAndMapper {

    public Account encodePassAndMapper(RegisterRequest request);
}
