package com.sirma.collaboration.services;

import com.sirma.collaboration.models.EmployeeDto;
import com.sirma.collaboration.models.ResultPairDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeesService {

    List<ResultPairDto> findEmployeesPair(List<EmployeeDto> employeeDtoList);
}
