package com.doctorsoffice.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class User implements UserDetails{

    @Id
    @SequenceGenerator(name = "mySeqGenUsers", sequenceName = "mySeqUsers", initialValue = 5, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenUsers")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = true)
    private String username;

    @Column(name = "password", unique = false, nullable = true)
    private String password;

    @Column(name = "firstName", unique = false, nullable = false)
    private String firstName;

    @Column(name = "lastName", unique = false, nullable = false)
    private String lastName;

    @Column(name = "phoneNumber", unique = false, nullable = false)
    private String phoneNumber;
    
    @Column(name = "personalID", unique = true, nullable = true)
    private String personalID;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User() {
    }
       
    public Long getId() {
        return id;
    }

    public User(Long id, String username, String password, String firstName, String lastName, String phoneNumber,
			List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
	}
    
	public User(String firstName, String lastName, String phoneNumber, String personalID, List<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.personalID = personalID;
		this.roles = roles;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    public List<Role> getRoles() {
		return roles;
	}


	@Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	Set<Permission> permissions = new HashSet<>();
        for(Role role : this.roles){
            for(Permission permission : role.getPermission()){
                permissions.add(permission);
            }
        }
        return permissions;
    }

	public String getPersonalID() {
		return personalID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
	
    

}
