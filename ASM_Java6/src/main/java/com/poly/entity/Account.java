package com.poly.entity;

import java.io.Serializable;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Accounts")
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
//dđ
public class Account implements Serializable{
	@Id
	@Column(columnDefinition = "varchar(30)")
	private String username;
	@Column(columnDefinition = "varchar(20)")
	private String password;
	@Column(columnDefinition = "nvarchar(40)")
	private String fullname;
	
	@Column(columnDefinition = "varchar(50)")
	private String email;
	@Column(columnDefinition = "varchar(225)")
	private String photo;
	@JsonIgnore
	@OneToMany(mappedBy = "account",  fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
