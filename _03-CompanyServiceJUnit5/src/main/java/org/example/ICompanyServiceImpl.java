/**
 * This code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ICompanyServiceImpl implements ICompanyService {
    private final Set<Company> visitedCompanies = new HashSet<>();

    @Override
    public Company getTopLevelParent(Company child) {
        if (child == null) {
            return null;
        }

        var current = child;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current;
    }

    private long getEmployeeCountForDescendentCompanies(Company parent, Company child) {
        if (child == parent) {
            return 0;
        }

        var current = child;
        var employeeCount = 0L;

        while (current.getParent() != null) {
            if (!visitedCompanies.contains(current)) {
                employeeCount += current.getEmployeeCount();
                visitedCompanies.add(current);
            }
            current = current.getParent();
            if (current == parent) {
                return employeeCount;
            }
        }

        return 0L;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        visitedCompanies.clear();
        return company.getEmployeeCount() + companies.stream()
                .map(eachCompany -> getEmployeeCountForDescendentCompanies(company, eachCompany))
                .reduce(0L, Long::sum);
    }
}
