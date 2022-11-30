package com.shilov.training.tests;

import com.shilov.training.dataproviders.ParametersReaderForResourcesRequests;
import com.shilov.training.dataproviders.ResourcesReader;
import com.shilov.training.models.Resource;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.shilov.training.tests.BaseReqresTest.Messages.*;

public class GetResourceTests extends BaseReqresTest {

    private String defaultValidPageNumber  = "2";
    private final String defaultValidResourcesPerPage  = "2";

    @Test
    public void testGetResourcesWithoutParameters() {
        ExtractableResponse<Response> response = resourceManagementRequests.getResources().extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.headers().getValue("Content-Type"),
                "application/json; charset=utf-8", HEADER_CONTENT_TYPE.toString());
        softAssert.assertEquals(response.body().jsonPath().getString("page"), "1", RESOURCES_NUMBER.toString());
        softAssert.assertEquals(response.body().jsonPath().getString("per_page"), "6", RESOURCES_PER_PAGE.name());
        softAssert.assertEquals(
                response.body().jsonPath().getList("data", Resource.class),
                ResourcesReader.getResourcesInRange(1, 6), RESOURCES_OBJECTS.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "resourcesValidId")
    public void testGetResourceByIdValidIdValue(String resourceId) {
        ExtractableResponse<Response> response = resourceManagementRequests.getResourceById(resourceId).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(
                response.jsonPath().getObject("data", Resource.class),
                ResourcesReader.getResourceById(Long.parseLong(resourceId)), RESOURCES_OBJECTS.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "resourcesInvalidId")
    public void testGetResourceByIdInvalidIdValue(String responseId) {
        ExtractableResponse<Response> response = resourceManagementRequests.getResourceById(responseId).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 404, CODE.toString());
        softAssert.assertEquals(response.body().asString(), "{}", RESPONSE_BODY.toString());
        softAssert.assertAll();
    }


    @Test (dataProvider = "invalidPageNumbersForResources")
    public void testGetResourcesInvalidPageNumber(String pageNumber) {
        ExtractableResponse<Response> response = resourceManagementRequests.getResources(pageNumber, defaultValidResourcesPerPage).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.jsonPath().getString("page"), pageNumber, PAGE_NUMBER.toString());
        softAssert.assertEquals(response.jsonPath().getList("data").size(), 0, RESOURCES_PER_PAGE.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidPageNumbersForResources")
    public Object[][] invalidPageNumberForResourcesDataProvider() {
        return ParametersReaderForResourcesRequests.getInvalidPageNumbers();
    }

    @Test(dataProvider = "invalidResourcesPerPage")
    public void testGetResourcesInvalidResourcesPerPage(String resourcesPerPage) {
        ExtractableResponse<Response> response = resourceManagementRequests.getResources(defaultValidPageNumber, resourcesPerPage).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.jsonPath().getString("per_page"), resourcesPerPage, RESOURCES_PER_PAGE.toString());
        softAssert.assertEquals(response.jsonPath().getList("data").size(), 0, RESOURCES_NUMBER.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidResourcesPerPage")
    public Object[][] invalidResourcesPerPageDataProvider() {
        return ParametersReaderForResourcesRequests.getInvalidResourcesPerPage();
    }

    @Test(dataProvider = "validPageNumbersForResources")
    public void testGetResourcesValidPageNumber(String pageNumber) {
        ExtractableResponse<Response> response = resourceManagementRequests.getResources(pageNumber, defaultValidResourcesPerPage).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.body().jsonPath().getString("page"), pageNumber, PAGE_NUMBER.toString());
        int resourceIdLimit = Integer.parseInt(pageNumber) * Integer.parseInt(defaultValidResourcesPerPage);
        softAssert.assertEquals(response.body().jsonPath().getList("data", Resource.class),
                ResourcesReader.getResourcesInRange(resourceIdLimit - 1, resourceIdLimit), RESOURCES_OBJECTS.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "validPageNumbersForResources")
    public Object[][] validPageNumberForResourcesDataProvider() {
        return ParametersReaderForResourcesRequests.getValidPageNumbers();
    }

    @Test(dataProvider = "validResourcesPerPage")
    public void testGetResourcesValidResourcesPerPage(String resourcesPerPage) {
        defaultValidPageNumber = "1";
        ExtractableResponse<Response> response = resourceManagementRequests.getResources(defaultValidPageNumber, resourcesPerPage).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.body().jsonPath().getString("per_page"), resourcesPerPage, RESOURCES_PER_PAGE.toString());
        softAssert.assertEquals(response.body().jsonPath().getList("data", Resource.class).size(),
                Integer.parseInt(resourcesPerPage), RESOURCES_NUMBER.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "validResourcesPerPage")
    public Object[][] validResourcesPerPageDataProvider() {
        return ParametersReaderForResourcesRequests.getValidResourcesPerPage();
    }

}
