/**
 * Name Entity
 * @author Suraj
 *
 */

package org.wipo.suite.modules.name.dal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.wipo.suite.modules.name.dal.util.CustomerDateAndTimeDeserialize;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Name {
	
	/*@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_NAME")
	private Long idName;*/
	
	@Id
	@Column(name="MAIN_ID")
	private String mainId;
	
	@Column(name="NAME")
    private String name; 
	@Column(name="FIRST_NAME")
    private String firstName;
	@Column(name="NAME_TYPE")
    private String nameType;
	
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@Column(name="CREATION_TIMESTAMP")
    private Date creationTimestamp;
	
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@Column(name="AMEDNMENT_TIMESTAMP")
    private Date amendmentTimestamp;
	
	
	
	@Column(name="USER_INSERT")
	private String userInsert;
	
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@Column(name="DATE_INSERT")
	private Date dateInsert;
	
	@Column(name="USER_UPDATE")
	private String userUpdate;
	
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	@Column(name="DATE_UPDATE")
	private Date dateUpdate;
	
	@Column(name="DELETED")
	private int deleted;
	@Column(name="DUMMY")
	private int dummy;
	
	/*@OneToMany(mappedBy = "name", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<RightOwnersName> rightOwnersName;*/
    
}
