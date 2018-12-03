package com.stp.auth.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.springframework.context.annotation.Scope;

import java.util.HashSet;
import java.util.Set;

@Entity
@Scope("session")
@Table(name = "user")
public class User {
	private Long id;
	private String staffNo;
	private String namaStaff;
	private String noKP;
	private String unit;
	private String email;
	private String noTelefon;
	private String namaPengurus;
	private String passport;
	private String status;
	private String username;
	private String password;
	private String passwordConfirm;
	private String jawatan;
	private String cawangan;
	private String enrichNo;
	private Set<Role> roles;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Set<Permohonan> permohonan = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getNamaStaff() {
		return namaStaff;
	}

	public void setNamaStaff(String namaStaff) {
		this.namaStaff = namaStaff;
	}

	public String getNoKP() {
		return noKP;
	}

	public void setNoKP(String noKP) {
		this.noKP = noKP;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNoTelefon() {
		return noTelefon;
	}

	public void setNoTelefon(String noTelefon) {
		this.noTelefon = noTelefon;
	}

	public String getNamaPengurus() {
		return namaPengurus;
	}

	public void setNamaPengurus(String namaPengurus) {
		this.namaPengurus = namaPengurus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}
	
	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getCawangan() {
		return cawangan;
	}

	public void setCawangan(String cawangan) {
		this.cawangan = cawangan;
	}

	public String getEnrichNo() {
		return enrichNo;
	}

	public void setEnrichNo(String enrichNo) {
		this.enrichNo = enrichNo;
	}
	
}
