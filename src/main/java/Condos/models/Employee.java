package Condos.models;


public class Employee {

    private int idM;
    private String nameM;
    private String usernameM;
    private String passwordM;




    public Employee(int idM, String nameM, String usernameM, String passwordM) {
        this.idM = idM;
        this.nameM = nameM;
        this.usernameM = usernameM;
        this.passwordM = passwordM;
    }

    public Employee(String nameM, String usernameM, String passwordM) {
        this.nameM = nameM;
        this.usernameM = usernameM;
        this.passwordM = passwordM;
    }

    public Employee(int idM, String usernameM, String passwordM) {
        this.idM = idM;
        this.usernameM = usernameM;
        this.passwordM = passwordM;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public Employee() {
    }

    public String getNameM() {
        return nameM;
    }

    public void setNameM(String nameM) {
        this.nameM = nameM;
    }

    public String getUsernameM() {
        return usernameM;
    }

    public void setUsernameM(String usernameM) {
        this.usernameM = usernameM;
    }

    public String getPasswordM() {
        return passwordM;
    }

    public void setPasswordM(String passwordM) {
        this.passwordM = passwordM;
    }


//    public boolean entryCheck(String username,String password){
//        if(usernameM.equals(username)) {
//            if(passwordM.equals(password)) return true;
//            else throw new IllegalArgumentException("Wrong password.");
//        }
//        return false;
//    }
}
