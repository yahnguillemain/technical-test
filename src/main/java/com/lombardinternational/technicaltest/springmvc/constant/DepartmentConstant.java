package com.lombardinternational.technicaltest.springmvc.constant;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepartmentConstant {

    public final static Set<String> DEPARTMENTS = Stream.of("HR", "IT", "Marketing", "Sales", "Finance").collect(Collectors.toSet());
}
