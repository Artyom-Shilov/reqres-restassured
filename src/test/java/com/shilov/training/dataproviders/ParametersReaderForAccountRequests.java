package com.shilov.training.dataproviders;

import com.shilov.training.bodies.AccountOperationRequestBody;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Properties;

@UtilityClass
public class ParametersReaderForAccountRequests extends BaseRequestsParametersPropertyReader {

    private final Properties properties = PropertiesLoader.loadProperties(
            "src/test/resources/accounts.properties");

    private String defaultValidPassword;
    private String defaultValidEmail;

    static {
        defaultValidPassword = getListOfTestData("password.valid", properties).get(0);
        defaultValidEmail = getListOfTestData("email.valid", properties).get(0);
    }

    public static Object[][] getRequestBodiesWithValidData() {
        String validPassword = getListOfTestData("password.valid", properties).get(0);
        List<AccountOperationRequestBody> bodies = getListOfTestData("email.valid", properties).stream()
                .map(stringEmail -> AccountOperationRequestBody.builder().email(stringEmail).password(validPassword).build())
                .toList();
        return formArrayForTestDataProvider(bodies);
    }

    public static Object[][] getRequestBodiesWithInvalidPasswords() {
        List<AccountOperationRequestBody> bodies = getListOfTestData("password.invalid", properties).stream()
                .map(stringPassword -> AccountOperationRequestBody.builder().email(defaultValidEmail).password(stringPassword).build())
                .toList();
        return formArrayForTestDataProvider(bodies);
    }

    public static Object[][] getRequestBodiesWithInvalidEmails() {
        List<AccountOperationRequestBody> bodies = getListOfTestData("email.invalid", properties).stream()
                .map(stringEmail -> AccountOperationRequestBody.builder().email(stringEmail).password(defaultValidPassword).build())
                .toList();
       return formArrayForTestDataProvider(bodies);
    }

    private Object[][] formArrayForTestDataProvider(List<AccountOperationRequestBody> bodies) {
        int caseAmount = bodies.size();
        Object[][] dataArrays = new Object[caseAmount][];
        for (int i = 0; i < caseAmount; i++) {
            dataArrays[i] = new Object[]{bodies.get(i)};
        }
        return dataArrays;
    }
}
