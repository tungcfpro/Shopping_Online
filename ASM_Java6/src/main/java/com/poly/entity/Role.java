package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@SuppressWarnings("serial")
@Table(name = "roles")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{
	@Id
	@Column(columnDefinition = "varchar(10)")
	private String Id;
	@Column(columnDefinition = "nvarchar(50)")
	private String Name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> authorities;
}
