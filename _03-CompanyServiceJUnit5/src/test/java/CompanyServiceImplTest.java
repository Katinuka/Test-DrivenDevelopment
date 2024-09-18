/**
 * This is code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
import org.example.Company;
import org.example.ICompanyService;
import org.example.ICompanyServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyServiceImplTest {
    private static final ICompanyService service = new ICompanyServiceImpl();

    @Test
    public void testTopLevelParent() {
        {
            var parent = new Company(9);
            var child = new Company(new Company(parent, 11), 15);
            assertEquals(parent, service.getTopLevelParent(child));
        }
        {
            var parent = new Company(5);
            var child = new Company(new Company(new Company(new Company(5), 4), 17), 10);
            assertNotEquals(parent, service.getTopLevelParent(child));
        }
        {
            var parent = new Company();
            var child = new Company(parent, 15);
            assertEquals(parent, service.getTopLevelParent(child));
        }
        {
            var parent = new Company(11);
            assertEquals(parent, service.getTopLevelParent(parent));
        }
        {
            assertNull(service.getTopLevelParent(null));
        }
    }

    @Test
    public void testBasicHierarchies() {
        {
            var parent = new Company(new Company(15), 5);
            var companies = List.of(
                    new Company(),
                    new Company(new Company()),
                    new Company(parent, 10),
                    new Company(new Company(new Company(12), 8), 4),
                    new Company(new Company(parent, 6), 9)
            );
            assertEquals(30, service.getEmployeeCountForCompanyAndChildren(parent, companies));
        }
        {
            var parent = new Company(8);
            var companies = List.of(
                    new Company(),
                    new Company(parent, 2),
                    new Company(new Company(parent, 3), 4),
                    new Company(new Company(new Company(parent, 7), 5), 6)
            );
            assertEquals(35, service.getEmployeeCountForCompanyAndChildren(parent, companies));
        }
        {
            var parent = new Company(new Company(new Company(11), 10), 9);
            var companies = List.of(
                    new Company(),
                    new Company(new Company()),
                    new Company(new Company(new Company(parent), 10), 5),
                    new Company(parent, 3),
                    new Company(new Company(new Company(new Company(11),9)), 7),
                    new Company(new Company(new Company(new Company(new Company(parent, 10), 11), 5), 6), 3)
            );
            assertEquals(62, service.getEmployeeCountForCompanyAndChildren(parent, companies));
        }
    }

    @Test
    public void testEdgeCases() {
        {
            var parent = new Company();
            var companies = List.of(
                    new Company(),
                    new Company(new Company(new Company(7), 3), 5),
                    new Company(parent, 2),
                    new Company(new Company(new Company(9), 1), 3)
            );
            assertEquals(2, service.getEmployeeCountForCompanyAndChildren(parent, companies));
        }
        {
            var parent = new Company(100);
            List<Company> companies = List.of();
            assertEquals(100, service.getEmployeeCountForCompanyAndChildren(parent, companies));
        }
        {
            var parent = new Company(100);
            List<Company> companies = List.of(parent, parent, new Company(parent, 20));
            assertEquals(120, service.getEmployeeCountForCompanyAndChildren(parent, companies));
        }
    }

    @Test
    public void testDeepHierarchy() {
        var greatGreatGrandparent = new Company(5);
        var greatGrandparent = new Company(greatGreatGrandparent, 10);
        var grandparent = new Company(greatGrandparent, 15);
        var parent = new Company(grandparent, 20);
        var companies = List.of(
                new Company(parent, 25),
                new Company(grandparent, 30),
                new Company(greatGrandparent, 35),
                new Company(greatGreatGrandparent, 40)
        );
        assertEquals(180, service.getEmployeeCountForCompanyAndChildren(greatGreatGrandparent, companies));
    }

    @Test
    public void testCircularReference() {
        var company1 = new Company(10);
        var company2 = new Company(company1, 20);
        company1.setParent(company2);  // This creates a circular reference
        var companies = List.of(company1, company2);
        assertEquals(30, service.getEmployeeCountForCompanyAndChildren(company1, companies));
    }

    @Test
    public void testMultipleBranches() {
        var root = new Company(100);
        var child1 = new Company(root, 50);
        var child2 = new Company(root, 75);
        var grandchild1 = new Company(child1, 25);
        var grandchild2 = new Company(child2, 30);
        var greatGrandchild = new Company(grandchild1, 10);
        var companies = List.of(root, child1, child2, grandchild1, grandchild2, greatGrandchild);
        assertEquals(290, service.getEmployeeCountForCompanyAndChildren(root, companies));
    }

    @Test
    public void testZeroEmployees() {
        var parent = new Company(0);
        var child1 = new Company(parent, 0);
        var child2 = new Company(parent, 10);
        var grandchild = new Company(child1, 0);
        var companies = List.of(parent, child1, child2, grandchild);
        assertEquals(10, service.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    @Test
    public void testNegativeEmployees() {
        var parent = new Company(50);
        var child1 = new Company(parent, -10);
        var child2 = new Company(parent, 30);
        var companies = List.of(parent, child1, child2);
        assertEquals(70, service.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    @Test
    public void testLargeHierarchy() {
        var root = new Company(1000);
        var companies = new ArrayList<Company>();
        companies.add(root);
        var current = root;
        for (int i = 0; i < 1000; i++) {
            var newCompany = new Company(current, i + 1);
            companies.add(newCompany);
            current = newCompany;
        }
        assertEquals(501500, service.getEmployeeCountForCompanyAndChildren(root, companies));
    }

    @Test
    public void testMultipleUnrelatedHierarchies() {
        var parent1 = new Company(100);
        var child1 = new Company(parent1, 50);
        var parent2 = new Company(200);
        var child2 = new Company(parent2, 75);
        var unrelatedCompany = new Company(300);
        var companies = List.of(parent1, child1, parent2, child2, unrelatedCompany);
        assertEquals(150, service.getEmployeeCountForCompanyAndChildren(parent1, companies));
        assertEquals(275, service.getEmployeeCountForCompanyAndChildren(parent2, companies));
        assertEquals(300, service.getEmployeeCountForCompanyAndChildren(unrelatedCompany, companies));
    }
}
