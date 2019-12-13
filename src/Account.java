import java.util.ArrayList;
import java.util.Objects;

public class Account {

    private String serviceName;
    private String description;
    private String password;
    private ArrayList<String> recoveryKeys;

    /**
     * Full constructor
     * @param serviceName The service name of the account
     * @param password The password of the account
     * @param description The description for the account
     * @param recoveryKeys The recovery keys for the account
     */
    public Account(String serviceName, String password, String description, ArrayList<String> recoveryKeys){
        this.serviceName = serviceName;
        this.password = password;
        this.description = description;
        this.recoveryKeys = recoveryKeys;
    }

    /**
     * Constructor without recovery keys
     * @param serviceName The service name of the account
     * @param password The password of the account
     * @param description The description for the account
     */
    public Account(String serviceName, String password, String description){
        this.serviceName = serviceName;
        this.password = password;
        this.description = description;
    }

    /**
     * Gets the service name of the account
     * @return service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Gets the description of the account
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the password of the account
     * @return password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Gets the recovery keys of the account
     * @return recovery keys
     */
    public ArrayList<String> getRecoveryKeys() {
        return Objects.requireNonNullElseGet(recoveryKeys, ArrayList::new);
    }

    @Override
    public String toString() {
        return "Account{" +
                "serviceName='" + serviceName + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", recoveryKeys=" + recoveryKeys +
                '}';
    }
}
