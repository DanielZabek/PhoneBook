package models;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
	
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber; 
    
    public Contact(String fName, String lName, String pNumber) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.phoneNumber = new SimpleStringProperty(pNumber);
    }
    
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }
        
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lName) {
        lastName.set(lName);
    }
    
    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public void setEmail(String pNumber) {
        phoneNumber.set(pNumber);
    }
        
}


