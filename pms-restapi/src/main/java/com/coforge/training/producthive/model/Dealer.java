package com.coforge.training.producthive.model;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 *Author :Mekapothula.Reddy
 *Date   :25 Nov 2024
 *Time   :12:53:10 pm
 *
 */

//Model class for Registration of users Personal fields with 1-1 mapping with address class
@Entity
@Table(name="dealers")
public class Dealer {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "dealer_seq")
	@SequenceGenerator(name = "dealer_seq", initialValue = 101, allocationSize = 1)
	@Column(name="did")
	private Long id;

	@Column(unique=true, nullable= false)
	private String email;

	@Column(name="first_name")
	private String fname;

	@Column(name="last_name")
	private String lname;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, max = 25, message = "Password must be between 8 and 255 Characters ")
	@Column(name = "password", nullable = false)
	private String password;

	@JsonFormat(pattern="yyyy-mm-dd")
	@Column(nullable = false)
	private Date dob;

	@Column(name="phone",unique= true, nullable= false)
	private String phoneNo;


	/*Primary Key - Foreign Key Relationship
	 * Model 1-1 Bi-Directional mapping b/w Dealer & Address */
	@OneToOne(mappedBy = "dealer", cascade = CascadeType.ALL)
	private Address address;		//has-a relationship - Embedded object

	public Dealer() {

	}

	public Dealer(Long id, String email, String fname, String lname, String password,
			Date dob, String phoneNo) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.dob = dob;
		this.phoneNo = phoneNo;
	}

	public Dealer(Long id, String email, String fname, String lname, String password,
			Date dob, String phoneNo, Address address) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.dob = dob;
		this.phoneNo = phoneNo;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();			//USE Base64 class for Password Entity
		String normalString = password;
		String encodedString = encoder.encodeToString(
				normalString.getBytes(StandardCharsets.UTF_8));
		this.password = encodedString;

	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
