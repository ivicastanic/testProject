package com.imconsalting.projekat.action;

import com.imconsalting.projekat.channel.Channel;
import com.imconsalting.projekat.customer.Customer;
import com.imconsalting.projekat.employee.Employee;
import com.imconsalting.projekat.response.Response;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.Objects;

@Entity
@Table(name = "actions", catalog = "project")
@NamedQueries(value = {
        @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
        @NamedQuery(name = "Action.findByEmployee", query = "SELECT a FROM Action a WHERE a.employee = :employee"),
        @NamedQuery(name = "Action.findByCustomer", query = "SELECT a FROM Action a WHERE a.customer = :customer")
})
public class Action implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int id;

    @Basic(optional = false)
    private LocalDate date;

    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;

    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employee employee;

    @JoinColumn(name = "id_channels", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Channel channel;

    @JoinColumn(name = "id_response", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Response response;

    private String description;

    public Action() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return id == action.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", employee=" + employee +
                ", channel=" + channel +
                ", response=" + response +
                ", description='" + description + '\'' +
                '}';
    }
}