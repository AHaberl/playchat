package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	private String userName;
	private String status;
	
	private String password;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
	private List<Message> messages = new ArrayList<Message>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
}