package com.lombardinternational.technicaltest.mapstruct;

import org.springframework.stereotype.Service;

@Service
public class EmployerService {

    public String getEmployerOf(String firstname, String lastname) {
        if (lastname.equals("Henri")) {
            return "WorldCompany";
        } else {
            return "SmallCompany";
        }
    }
}
