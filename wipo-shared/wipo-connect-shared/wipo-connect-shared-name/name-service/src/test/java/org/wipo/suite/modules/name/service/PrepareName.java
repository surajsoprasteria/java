package org.wipo.suite.modules.name.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wipo.suite.modules.name.dal.entity.Cmo;
import org.wipo.suite.modules.name.dal.entity.Name;
import org.wipo.suite.modules.name.dal.entity.RightOwners;
import org.wipo.suite.modules.name.dal.entity.RightOwnersName;
import org.wipo.suite.modules.name.dal.entity.RightOwnersStatus;
import org.wipo.suite.modules.name.dal.entity.Territory;

public class PrepareName {
	
	public static List<Name> getNamesList(){
		List<Name> list_names=new ArrayList<Name>();
		Name name1=new Name();
		//name1.setIdName(1L);
		name1.setName("mozart");
		name1.setFirstName("wolfgang amadeusxx");
		name1.setNameType("PA");
		name1.setCreationTimestamp(null);
		name1.setAmendmentTimestamp(null);
		name1.setMainId("I-00021693006");
		name1.setUserInsert(null);
		name1.setDateInsert(formatDate("2018-04-17"));
		name1.setUserUpdate(null);
		name1.setDateUpdate(formatDate("2018-06-06"));
		name1.setDeleted(0);
		name1.setDummy(0);

		Name name2=new Name();
		//name2.setIdName(3L);
		name2.setName("mo");
		name2.setFirstName("zha te");
		name2.setNameType("PP");
		name2.setCreationTimestamp(null);
		name2.setAmendmentTimestamp(null);
		name2.setMainId("I-00502699253");
		name2.setUserInsert(null);
		name2.setDateInsert(formatDate("2018-04-17"));
		name2.setUserUpdate(null);
		name2.setDateUpdate(formatDate("2018-06-06"));
		name2.setDeleted(0);
		name2.setDummy(0);
		
		list_names.add(name1);
		list_names.add(name2);
		return list_names;
	}
	
	public static Name getNames() {
		Name name=new Name();
		//name.setIdName(31307L);
		name.setName("test");
		name.setFirstName("test fname");
		name.setNameType("PP");
		name.setCreationTimestamp(null);
		name.setAmendmentTimestamp(null);
		name.setMainId("N-1031307");
		name.setUserInsert(null);
		name.setDateInsert(formatDate("2018-04-17"));
		name.setUserUpdate(null);
		name.setDateUpdate(formatDate("2018-06-06"));
		name.setDeleted(0);
		name.setDummy(0);
		return name;
	}
	public static String[] getMessage() {
		String[] msg= {"Saved Successfully","Please provide unique main id to insert"};
		return msg;
	}
	
	public static List<RightOwnersName> getRightOwnerName() {
		List<RightOwnersName> list_righOwnersName=new ArrayList<RightOwnersName>();
		
		RightOwnersName rightOwnersName=new RightOwnersName();
		rightOwnersName.setIdInterestedPartyName(1L);
		rightOwnersName.setUserInsert(null);
		rightOwnersName.setDateInsert(null);
		rightOwnersName.setUserUpdate(null);
		rightOwnersName.setDateUpdate(null);
		
		RightOwners rightOwners=new RightOwners();
		rightOwners.setIdInterestedParty( 1L);
		rightOwners.setMainId( "1-P");
		rightOwners.setType( "P");
		rightOwners.setSex( "F");
		rightOwners.setBirthDate(formatDate("2056-01-27"));
		rightOwners.setDeathDate(formatDate("1991-12-05"));
		rightOwners.setBirthPlace( null);
		rightOwners.setBirthState( null);
		rightOwners.setAmendmentTimestamp( null);
		rightOwners.setAccount( null);
		rightOwners.setMaritalStatus( null);
		rightOwners.setUserInsert( null);
		rightOwners.setUserUpdate( null);
		rightOwners.setDateUpdate(formatDate("2018-06-06"));
		rightOwners.setCreationDate(formatDate("2018-04-17"));
		rightOwners.setDoAffiliation( null);
		rightOwners.setIsAffiliated( null);
		rightOwners.setDeleted( 0);
		rightOwners.setDummy( 0);
		rightOwners.setDateInsert( "2018-04-17");
		rightOwners.setSyncVersion( "2");
		
		Territory territory=new Territory();
		territory.setIdTerritory( 18L);
		territory.setCode( "0040");
		territory.setFkType( "211");
		territory.setStartDate( "2000-01-01");
		territory.setEndDate( "1999-12-31");
		territory.setUserInsert( null);
		territory.setDateInsert( null);
		territory.setUserUpdate( null);
		territory.setDateUpdate( null);
		territory.setManagedByCmo( null);
		
		RightOwnersStatus rightOwnersStatus=new RightOwnersStatus();
		rightOwnersStatus.setFkValue( "302");
		rightOwnersStatus.setCode( "VALID");
		rightOwnersStatus.setSortOrder( "3");
		rightOwnersStatus.setIdInterestedPartyStatus( 3L);
		
		Cmo cmo=new Cmo();
		cmo.setIdCmo( 151L);
		cmo.setName( "WIPO Connect");
		cmo.setCode( "700");
		cmo.setAcronym( "CONNECT");
		cmo.setTerritory( null);
		cmo.setContact( null);
		cmo.setDescription( null);
		cmo.setInternationalName( null);
		cmo.setIsDataSource( "1");
		cmo.setFkCmoType( "13");
		
		rightOwners.setTerritory(territory);
		rightOwners.setRightOwnersStatus(rightOwnersStatus);
		rightOwners.setCmo(cmo);
		
		rightOwnersName.setRightOwners(rightOwners);
		
		
		list_righOwnersName.add(rightOwnersName);
		
		return list_righOwnersName;
	}
	
	public static Date formatDate(String date_str) {
		try {
			SimpleDateFormat formater=new SimpleDateFormat("YYYY-MM-DD");
			Date date=formater.parse(date_str);
			return date;
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
