package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;

    @Column(name = "UserName", columnDefinition = "VARCHAR(100)")
    private String username;

    @Column(name = "PassWord", columnDefinition = "VARCHAR(100)")
    private String password;

    @Column(name = "FullName", columnDefinition = "NVARCHAR(MAX)")
    private String fullName;

    @Column(name = "Email", columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "Code", columnDefinition = "VARCHAR(50)")
    private String code;

    @Column(name = "RoleID")
    private int roleId; // 1-user, 2-manager, 3-admin

    @Column(name = "Images")
    private String images;
	
    @Column(name = "Phone")
    private String phone;
    
	

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public User(int userId, String username, String password, String fullName, String email, String code, int roleId) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.code = code;
		this.roleId = roleId;
	}

	public User(String username, String password, String fullName, String email, int roleId) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.roleId = roleId;
	}

	public User() {
		super();
	}


	
	
    
    
}
