package mate.academy.model;

import java.util.Objects;

public class Driver {
    private Long id;
    private String name;
    private String licenceNumber;
    private String login;
    private String password;

    public Driver(String name, String licenceNumber) {
        this.name = name;
        this.licenceNumber = licenceNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id)
                && Objects.equals(name, driver.name)
                && Objects.equals(licenceNumber, driver.licenceNumber)
                && Objects.equals(login, driver.login)
                && Objects.equals(password, driver.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, licenceNumber, login, password);
    }

    @Override
    public String toString() {
        return "Driver{"
                + "id=" + id
                + ", name='" + name
                + '\'' + ", licenceNumber='"
                + licenceNumber + '\'' + '}';
    }
}
