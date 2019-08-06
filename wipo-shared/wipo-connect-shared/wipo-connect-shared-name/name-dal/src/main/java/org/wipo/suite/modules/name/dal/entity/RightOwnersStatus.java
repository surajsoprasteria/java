/**
 * RightOwnersStatus Entity
 * @author Suraj
 *
 */

package org.wipo.suite.modules.name.dal.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "INTERESTED_PARTY_STATUS")
public class RightOwnersStatus {
	@Id
	@Column(name = "ID_INTERESTED_PARTY_STATUS")
	private Long IdInterestedPartyStatus;
	@Column(name = "FK_VALUE")
	private String fkValue;
	@Column(name = "CODE")
	private String code;
	@Column(name = "SORT_ORDER")
	private String sortOrder;
	
	@OneToMany(mappedBy="rightOwnersStatus",cascade=CascadeType.ALL)	
	@JsonIgnore
	Set <RightOwners> rightowners;

}
