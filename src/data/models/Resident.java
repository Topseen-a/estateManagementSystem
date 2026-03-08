package data.models;

public class Resident {
    private int id;
    private String name;
    private String phoneNumber;
    private String houseAddress;

    public Resident(String name, String phoneNumber, String houseAddress) {
        validateName(name);
        this.name = name;
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        validateAddress(houseAddress);
        this.houseAddress = houseAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    private static void validateName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private static void validatePhoneNumber(String phoneNumber){
        if(phoneNumber == null || phoneNumber.isBlank()){
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
    }

    private static void validateAddress(String houseAddress){
        if(houseAddress == null || houseAddress.isBlank()){
            throw new IllegalArgumentException("Address cannot be empty");
        }
    }
}
