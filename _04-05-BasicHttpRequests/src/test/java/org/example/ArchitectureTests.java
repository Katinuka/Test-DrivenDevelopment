/**
 * This code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@SpringBootTest
class ArchitectureTests {

    private JavaClasses applicationClasses;
    private final String controllerPackage = "..controller..";
    private final String modelPackage = "..model..";
    private final String repositoryPackage = "..repository..";
    private final String servicePackage = "..service..";


    @BeforeEach
    void init() {
        applicationClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("org.example");
    }

    @Test
    void shouldFollowArchitectureLayer() {
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy(controllerPackage)
                .layer("Service").definedBy(servicePackage)
                .layer("Repository").definedBy(repositoryPackage)
                //
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                //
                .check(applicationClasses);
    }

    @Test
    void controllersShouldHaveServiceDependencies() {
        classes()
                .that().resideInAPackage(controllerPackage)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(servicePackage)
                .because("We are developing a layered architecture, aren't we ? :)")
                .check(applicationClasses);
    }

    @Test
    void controllersShouldNotDependOnOtherControllers() {
        noClasses()
                .that().resideInAPackage(controllerPackage)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(controllerPackage)
                .because("Controllers depending on other controllers aren't recommended to use")
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldNotDependOnServices() {
        noClasses()
                .that().resideInAPackage(repositoryPackage)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(servicePackage)
                .because("It's out of our architecture rules :)")
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldNotDependOnControllers() {
        noClasses()
                .that().resideInAPackage(repositoryPackage)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(controllerPackage)
                .because("It's out of our architecture rules :)")
                .check(applicationClasses);
    }

    @Test
    void servicesShouldNotDependOnControllers() {
        noClasses()
                .that()
                .resideInAPackage(servicePackage)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(controllerPackage)
                .check(applicationClasses);
    }

    @Test
    void modelsShouldNotDependOnControllers() {
        noClasses()
                .that().resideInAPackage(modelPackage)
                .should()
                .dependOnClassesThat()
                .resideInAPackage(controllerPackage)
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldOnlyBeAccessedByServices() {
        classes()
                .that().resideInAPackage(repositoryPackage)
                .should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAPackage(servicePackage)
                .because("Repositories should only be accessed by the service layer")
                .check(applicationClasses);
    }

    @Test
    void controllerClassesNamesShouldEndWithTheWordController() {
        classes()
                .that().resideInAPackage(controllerPackage)
                .should()
                .haveSimpleNameEndingWith("Controller")
                .check(applicationClasses);
    }

    @Test
    void repositoryClassesNamesShouldEndWithTheWordRepository() {
        classes()
                .that().resideInAPackage(repositoryPackage)
                .should()
                .haveSimpleNameEndingWith("Repository")
                .check(applicationClasses);
    }

    @Test
    void serviceClassesNamesShouldEndWithTheWordService() {
        classes()
                .that().resideInAPackage(servicePackage)
                .should()
                .haveSimpleNameEndingWith("Service")
                .check(applicationClasses);
    }

    @Test
    void controllerClassesShouldBeAnnotated() {
        classes()
                .that().resideInAPackage(controllerPackage)
                .should()
                .beAnnotatedWith(RestController.class)
                .orShould()
                .beAnnotatedWith(Controller.class)
                .check(applicationClasses);
    }

    @Test
    void repositoryClassesShouldBeAnnotated() {
        classes()
                .that().resideInAPackage(repositoryPackage)
                .should()
                .beAnnotatedWith(Repository.class)
                .check(applicationClasses);
    }

    @Test
    void serviceClassesShouldBeAnnotated() {
        classes()
                .that().resideInAPackage(servicePackage)
                .should()
                .beAnnotatedWith(Service.class)
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldBeInterfaces() {
        classes()
                .that().resideInAPackage(repositoryPackage)
                .should()
                .beInterfaces()
                .check(applicationClasses);
    }

    @Test
    void modelsShouldNotBeInterfaces() {
        noClasses()
                .that().resideInAPackage(modelPackage)
                .should()
                .beInterfaces()
                .check(applicationClasses);
    }

    @Test
    void controllerFieldsShouldNotBeAutoWired() {
        noFields()
                .that()
                .areDeclaredInClassesThat()
                .resideInAPackage(controllerPackage)
                .should()
                .beAnnotatedWith(Autowired.class)
                .check(applicationClasses);
    }

    @Test
    void serviceFieldsShouldNotBeAutoWired() {
        noFields()
                .that()
                .areDeclaredInClassesThat()
                .resideInAPackage(servicePackage)
                .should()
                .beAnnotatedWith(Autowired.class)
                .check(applicationClasses);
    }

    @Test
    void modelFieldsShouldBePrivate() {
        fields()
                .that()
                .areDeclaredInClassesThat()
                .resideInAPackage(modelPackage)
                .should().bePrivate()
                .check(applicationClasses);
    }

    @Test
    void modelIdFieldsShouldBeAnnotated() {
        fields()
                .that()
                .areDeclaredInClassesThat()
                .resideInAPackage(modelPackage)
                .and()
                .haveName("id")
                .should()
                .beAnnotatedWith(Id.class)
                .check(applicationClasses);
    }

    @Test
    void modelIdFieldsShouldBeString() {
        fields()
                .that().areDeclaredInClassesThat().resideInAPackage(modelPackage)
                .and().haveName("id")
                .should().haveRawType(String.class)
                .because("ID fields should always be of type String")
                .check(applicationClasses);
    }

    @Test
    void noCyclicDependenciesBetweenLayers() {
        slices()
                .matching("org.example.(*)..")
                .should().beFreeOfCycles()
                .because("There should be no cyclic dependencies between layers")
                .check(applicationClasses);
    }
}
