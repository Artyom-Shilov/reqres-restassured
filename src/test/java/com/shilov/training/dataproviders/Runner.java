package com.shilov.training.dataproviders;

public class Runner {

    public static void main(String[] args) {
        System.out.println(ParametersReaderForAccountRequests.getRequestBodiesWithValidData().length);
        System.out.println(ParametersReaderForAccountRequests.getRequestBodiesWithInvalidPasswords().length);
        System.out.println(ParametersReaderForAccountRequests.getRequestBodiesWithInvalidEmails().length);
    }
}
