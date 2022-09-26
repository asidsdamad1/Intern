package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "accounts")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private double amount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<User>  users;
    public Account() {
    }

    public Account(String name, double amount) {

        this.name = name;
        this.amount = amount;
    }

    public long getId() {
        return accountId;
    }

    public void setId(long id) {
        this.accountId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
