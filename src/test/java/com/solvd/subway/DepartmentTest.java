package com.solvd.subway;

import com.solvd.subway.domain.Department;
import com.solvd.subway.persistence.DepartmentRepository;
import com.solvd.subway.persistence.Impl.DepartmentMybatisImpl;
import org.testng.Assert;
import org.testng.annotations.*;

public class DepartmentTest {

    private final DepartmentRepository departmentRepository;
    private final Department departmentTest;

    public DepartmentTest() {
        departmentRepository = new DepartmentMybatisImpl();
        departmentTest = new Department();
        departmentTest.setTitle("DepartmentTest");
    }

    @BeforeMethod
    public void createDepartment() {
        departmentRepository.createDepartment(departmentTest);
    }

    @AfterMethod
    public void deleteDepartment() {
        departmentRepository.delete(departmentTest);
    }

    @DataProvider
    public Object[][] deleteData() {
        return new Object[][] {
                {new Department("HR")},
                {new Department("Logistics")}
        };
    }

    @Test
    public void verifyDepartmentUpdate() {
        Long id = departmentRepository.getDepartmentId(departmentTest.getTitle());
        departmentRepository.update("Secure", id);
        Department department = departmentRepository.getById(id);
        Assert.assertEquals(department.getTitle(), "Secure", "Department update failed");
    }

    @Test (dataProvider = "deleteData")
    public void verifyMultipleDelete(Department department) {
        departmentRepository.delete(department);
        Department nullDepartment = departmentRepository.getByTitle(department.getTitle());
        Assert.assertNull(nullDepartment, "Department was not deleted");
    }
}
