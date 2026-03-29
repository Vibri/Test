package cz.vibri.orderservice.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "customerAddress.address",
                column = @Column(name = "address")
        ),
        @AttributeOverride(
                name = "customerAddress.city",
                column = @Column(name = "city")
        ),
        @AttributeOverride(
                name = "customerAddress.state",
                column = @Column(name = "state")
        ),
        @AttributeOverride(
                name = "customerAddress.zipCode",
                column = @Column(name = "zip_code")
        )
})
public class Customer extends BaseEntity{

    @Size(max = 50)
    private String customerName;

    @Size(max = 20)
    private String phone;

    @Email
    private String email;

    @Version
    private Integer version;

    @Valid
    @Embedded
    private Address customerAddress;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orderHeaders;

    public void addOrderHeader(OrderHeader orderHeader) {
        if (orderHeaders == null) {
            orderHeaders = new HashSet<>();
        }

        orderHeaders.add(orderHeader);
        orderHeader.setCustomer(this);

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Set<OrderHeader> getOrderHeaders() {
        return orderHeaders;
    }

    public void setOrderHeaders(Set<OrderHeader> orderHeaders) {
        this.orderHeaders = orderHeaders;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
