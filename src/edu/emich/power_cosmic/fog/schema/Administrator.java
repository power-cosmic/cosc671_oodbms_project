package edu.emich.power_cosmic.fog.schema;

public class Administrator extends FogUser {
    
    private String firstName;
    private String lastName;
    private long employeeId;
    
    public Administrator() {
    	this(null, null);
    }
    
    public Administrator(String username, String password) {
        super(username, password);
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

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
    
}
