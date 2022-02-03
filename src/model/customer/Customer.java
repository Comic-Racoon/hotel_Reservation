package model.customer;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    private String emailPattern = "^(.+)@(.+).com$";
    private Pattern pattern = Pattern.compile(emailPattern);

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;

        if(pattern.matcher(email).matches()) {
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("Please Enter A Valid Email Address");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Name : " + firstName + " " + lastName + "\n" + "Email Address: " + email;
    }
}
