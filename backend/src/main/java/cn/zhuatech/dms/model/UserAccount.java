/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.dms.model;
import jakarta.persistence.*;
@Entity @Table(name="dms_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, DOCUMENT_MANAGER, CONTRIBUTOR, QUALITY }
    @Column(nullable=false,unique=true,length=32) private String username; @Column(nullable=false) private String password;
    @Column(nullable=false,length=50) private String fullName; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
    @Column(name="work_center_code",length=32) private String libraryCode; @Column(nullable=false) private boolean enabled=true;
    protected UserAccount(){}
    public UserAccount(String username,String password,String fullName,Role role,String libraryCode){this.username=username;this.password=password;this.fullName=fullName;this.role=role;this.libraryCode=libraryCode;}
    public String getUsername(){return username;} public String getPassword(){return password;} public String getFullName(){return fullName;} public Role getRole(){return role;} public String getLibraryCode(){return libraryCode;} public boolean isEnabled(){return enabled;}
}
