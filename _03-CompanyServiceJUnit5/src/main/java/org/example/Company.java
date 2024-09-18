/**
 * This is code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example;

public class Company {
    private Company parent; // the parent for this company, null when there is no parent
    private long employeeCount;

    public Company() {
    }

    public Company(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Company(Company parent) {
        this.parent = parent;
    }

    public Company(Company parent, long employeeCount) {
        this.parent = parent;
        this.employeeCount = employeeCount;
    }

    public Company getParent() {
        return parent;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }
}
