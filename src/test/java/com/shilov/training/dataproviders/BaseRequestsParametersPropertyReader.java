package com.shilov.training.dataproviders;

import java.util.List;
import java.util.Properties;

public abstract class BaseRequestsParametersPropertyReader {

    protected static int calculateCaseAmount(String keyPhrase, Properties properties) {
        return (int) properties.entrySet().stream()
                .filter(e -> ((String)e.getKey()).contains(keyPhrase))
                .count();
    }

    protected static List<String> getListOfTestData(String keyPhrase, Properties properties) {
        return properties.entrySet().stream()
                .filter(e -> ((String)e.getKey()).contains(keyPhrase))
                .map(e -> (String) e.getValue())
                .toList();
    }

    protected static Object[] formArrayFromOneCaseData(String data) {
        if (data.contains(",")) {
            return new Object[] {data.split(",")[0].split(":")[1], data.split(",")[1].split(":")[1]};
        }
        return new Object[]{data};
    }

    protected static Object[][] getDataForTestNgDataProvider(String keyPhrase, Properties properties) {
        int caseAmount = calculateCaseAmount(keyPhrase, properties);
        Object[][] dataArrays = new Object[caseAmount][];
        for (int i = 0; i < caseAmount; i++) {
            dataArrays[i] = formArrayFromOneCaseData(getListOfTestData(keyPhrase, properties).get(i));
        }
        return dataArrays;
    }
}
