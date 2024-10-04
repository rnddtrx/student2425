package be.ipam.student.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @Column(name = "StudentID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StudentID", nullable = false)
    private Student student;

    @Nationalized
    @Column(name = "Street", length = 500)
    private String street;

    @Nationalized
    @Column(name = "Number", length = 50)
    private String number;

    @Nationalized
    @Column(name = "PostalCode", length = 50)
    private String postalCode;

    @Nationalized
    @Column(name = "City", length = 200)
    private String city;

}