package com.solvd.subway;


import com.solvd.subway.domain.Subway;
import com.solvd.subway.persistence.Impl.SubwayMybatisImpl;
import com.solvd.subway.persistence.SubwayRepository;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SubwayTest {

    private final SubwayRepository subwayRepository;
    private final Subway subwayTest;

    public SubwayTest() {
        subwayRepository = new SubwayMybatisImpl();
        subwayTest = new Subway();
        subwayTest.setCity("Test");
    }

    @Test
    public void verifySubwayCreate() {
        subwayRepository.create(subwayTest);
        Subway baseSubway = subwayRepository.getByCity(subwayTest.getCity());
        Assert.assertNotNull(baseSubway, "Creation failed");
    }

    @DataProvider
    public Object[][] updateData() {
        return new Object[][]{
                {"London", 2L},
                {"Brest", 3L},
        };
    }

    @Test(dataProvider = "updateData")
    public void verifySubwayUpdate(String city, Long id) {
        subwayRepository.update(city, id);
        Subway updatedSubway = subwayRepository.getById(id);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updatedSubway.getCity(), city, "Update failed");
        softAssert.assertAll();
    }

    @Test (groups = "selects")
    public void verifyGetSubwayByCity() {
        Subway baseSubway = subwayRepository.getByCity("Test");
        Assert.assertNotNull(baseSubway, "Subway not found");
    }

    @Test (groups = "selects")
    public void verifyGetSubways() {
        List<Subway> baseSubways = subwayRepository.findEmployees();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(baseSubways.size(), 4, "Subways not found");
        softAssert.assertAll();
    }

    @Test
    public void verifyDeleteSubway() {
        subwayRepository.delete(subwayTest);
        Subway nullSubway = subwayRepository.getByCity("Test");
        Assert.assertNull(nullSubway, "Subway cannot be deleted");
    }

    @BeforeTest
    public void beforeTestCheck() {
        System.out.println("New test run");
    }

    @AfterTest
    public void afterTestCheck() {
        System.out.println("\nTest run ended");
    }

    @AfterGroups(groups = "selects")
    public void afterGroupsCheck() {
        System.out.println("Group ended");
    }

    @BeforeGroups(groups = "selects")
    public void beforeGroupsCheck() {
        System.out.println("Group started");
    }
}