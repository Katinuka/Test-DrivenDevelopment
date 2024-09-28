/**
 * This code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example;

import java.util.List;

public interface ICompanyService {
    /**
     * @param child The company from which we search
     *                  the top-level parent (i.e., the parent of the parent of...)
     * @return The top-level parent of the specified company.
     */
    Company getTopLevelParent(Company child);

    /**
     * @param company The company for which to count employees, including the employees of all
     *                  its descendant companies.
     * @param companies All available companies
     * @return The total number of employees for the provided company and all its descendants.
     */
    long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies);
}
