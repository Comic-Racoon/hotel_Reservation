package service;

import model.customer.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    //----------Singleton static reference ------------------
    // ref: https://www.programiz.com/java-programming/singleton
    private static CustomerService customerService;
    private CustomerService(){}

    public static CustomerService getInstance(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }
    //---------------store customer(s) data----------------------

    static Map<String , Customer> customers = new HashMap<>();

    //--------------------addCustomer----------------------------

    /**
     *
     * @param email
     * @param firstName
     * @param lastName
     *
     * add customer to map<string (email), Customer <obj which has param >>
     */
    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName,lastName,email);
        customers.put(customer.getEmail(), customer);
    }

    //---------------------getCustomer--------------------------

    /**
     *
     * @param customerEmail
     * @return customer obj inside customers which is map having keys of emails
     */
    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);
    }

    //----------------------get All Customers -------------------

    /**
     *
     * @return list of obj customer
     */
    public Collection<Customer> getAllCustomers(){
        return  customers.values();
    }

}
