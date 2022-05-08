package com.sirma.collaboration.services.implementations;

import com.sirma.collaboration.models.EmployeeDto;
import com.sirma.collaboration.models.EmployeesPairDto;
import com.sirma.collaboration.models.ResultPairDto;
import com.sirma.collaboration.services.EmployeesService;
import com.sirma.collaboration.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeesServiceImplementation implements EmployeesService {

    public List<ResultPairDto> findEmployeesPair(List<EmployeeDto> employeeDtoList) {
        // Key = projectId, Value = list of all employees worked on the project
        Map<Integer, List<EmployeeDto>> pairByProjId = new HashMap<>();
        // Key = days pair worked together on project, Value = list of pairs worked for same period together
        Map<Long, List<EmployeesPairDto>> daysCommonProjects = new HashMap<>();
        // Key = pair worked together on common projects, Value = sorted queue of common projectIds and days
        Map<EmployeesPairDto, Queue<ResultPairDto>> pairsCommonProjects = new HashMap<>();
        long maxPeriod = Long.MIN_VALUE;
        for (int em = 0; em < employeeDtoList.size(); em++) {
            EmployeeDto currentEmployee = employeeDtoList.get(em);
            if (pairByProjId.containsKey(currentEmployee.getProjectId())) {
                List<EmployeeDto> currentTeam = pairByProjId.get(currentEmployee.getProjectId());
                for (int i = 0; i < currentTeam.size(); i++) {
                    EmployeeDto first = currentTeam.get(i);
                    if (currentEmployee.getId() != first.getId()) {
                        if (!first.getFrom().after(currentEmployee.getTo()) && !currentEmployee.getFrom().after(first.getTo())) {
                            Date maxDateFrom = DateUtils.max(first.getFrom(), currentEmployee.getFrom());
                            Date minDateTo = DateUtils.min(first.getTo(), currentEmployee.getTo());
                            long curMax = DateUtils.getDifferenceDays(maxDateFrom, minDateTo);
                            if (curMax > maxPeriod) {
                                maxPeriod = curMax;
                            }
                            EmployeesPairDto twoEmp = new EmployeesPairDto(currentEmployee.getId(), first.getId());
                            ResultPairDto pairEmp = new ResultPairDto(currentEmployee.getId(),
                                    first.getId(), currentEmployee.getProjectId(), curMax);
                            if (!daysCommonProjects.containsKey(curMax)) {
                                daysCommonProjects.put(curMax, new ArrayList<>());
                            }
                            List<EmployeesPairDto> currList = daysCommonProjects.get(curMax);
                            currList.add(twoEmp);
                            if (!pairsCommonProjects.containsKey(twoEmp)) {
                                pairsCommonProjects.put(twoEmp, new PriorityQueue<>());
                            }
                            Queue<ResultPairDto> currPairCommProj = pairsCommonProjects.get(twoEmp);
                            currPairCommProj.add(pairEmp);
                        }
                    }
                }
                currentTeam.add(currentEmployee);
            } else {
                List<EmployeeDto> curTeam = new ArrayList<>();
                curTeam.add(currentEmployee);
                pairByProjId.put(currentEmployee.getProjectId(), curTeam);
            }
        }
        List<ResultPairDto> result = new ArrayList<>();
        // It may not be just one pair worked for the "maxPeriod"
        List<EmployeesPairDto> res = daysCommonProjects.get(maxPeriod);
        for (int i = 0; i < res.size(); i++) {
            Queue<ResultPairDto> queue = pairsCommonProjects.get(res.get(i));
            result.addAll(queue);
        }

        return result;
    }
}

