package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeControl {
    public static List<Employee> employees = new ArrayList<>();

    public List<Employee> getAllData() {
        Employee employee1 = new Employee(1, "alibaba1", 17, "male");
        Employee employee2 = new Employee(2, "alibaba2", 18, "female");
        Employee employee3 = new Employee(3, "oocl1", 19, "male");
        Employee employee4 = new Employee(4, "oocl2", 20, "female");
        if (employees.size() == 0) {
            employees.add(employee1);
            employees.add(employee2);
            employees.add(employee3);
            employees.add(employee4);
        }
        return employees;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees() {
        return getAllData();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getSpecifiedIdEmployee(@PathVariable Integer employeeId){
        for(Employee employee:getAllData()){
            if (employee.getId()==employeeId){
                return employee;
            }
        }
        return null;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmployee(@PathVariable Integer employeeId,@RequestBody Employee employee) {
        Employee specifiedEmployee=getSpecifiedIdEmployee(employeeId);
        if(specifiedEmployee!=null){
            specifiedEmployee.setName(employee.getName());
            specifiedEmployee.setGender(employee.getGender());
            specifiedEmployee.setAge(employee.getAge());

        }
        return specifiedEmployee;
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable Integer employeeId) {
        Employee specifiedIdEmployee = getSpecifiedIdEmployee(employeeId);
        getAllData().remove(specifiedIdEmployee);
    }

}
