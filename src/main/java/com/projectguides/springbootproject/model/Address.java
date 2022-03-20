package com.projectguides.springbootproject.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table  (name = "Address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "student_Id")
    private int student_Id;

    @Column (name = "streetline1")
    private String streetline1;

    @Column (name = "streetline2")
    private String streetline2;

    @Column (name = "city")
    private String city;

    @Column (name = "state")
    private String state;

    @Column (name = "country")
    private String country;

    @Column (name = "pincode")
    private int pincode;

}
