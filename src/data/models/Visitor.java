package data.models;

public class Visitor {
    private int id;
    private String name;
    private String purposeOfComing;
    private String phoneNumber;

    public Visitor(String name, String purposeOfComing, String phoneNumber) {
        validateName(name);
        this.name = name;
        validatePurpose(purposeOfComing);
        this.purposeOfComing = purposeOfComing;
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
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

    public String getPurposeOfComing() {
        return purposeOfComing;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private static void validatePurpose(String purpose) {
        if (purpose == null || purpose.isBlank()) {
            throw new IllegalArgumentException("Purpose of coming cannot be empty");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        if (!phoneNumber.matches("\\d{11}")) {
            throw new IllegalArgumentException("Phone number must be exactly 11 digits");
        }
    }
}