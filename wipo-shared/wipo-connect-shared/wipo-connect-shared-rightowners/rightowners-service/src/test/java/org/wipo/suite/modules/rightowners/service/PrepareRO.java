package org.wipo.suite.modules.rightowners.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wipo.suite.modules.rightowners.dal.dto.RightownerResponse;
import org.wipo.suite.modules.rightowners.dal.entity.Account;
import org.wipo.suite.modules.rightowners.dal.entity.Cmo;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwners;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwnersStatus;
import org.wipo.suite.modules.rightowners.dal.entity.Territory;
/**
 * 
 * @author Devdyuti
 *
 */
public class PrepareRO {
	
	private static List<RightOwners> list_rightowner;
	private static List<RightownerResponse> list_rightowner_response;
	
	public static List<RightOwners> getRighOwnerList(){
		
		list_rightowner=new ArrayList<RightOwners>();
		
		RightOwners rightOwners1=new RightOwners();		
		rightOwners1.setMainId( "1-P");
		rightOwners1.setType( "N");
		rightOwners1.setSex( "M");
		rightOwners1.setBirthDate(formatDate("2056-01-27"));
		rightOwners1.setDeathDate(formatDate("1991-12-05"));
		rightOwners1.setBirthPlace("Delhi");
		rightOwners1.setBirthState("DL");
		rightOwners1.setMaritalStatus( null);
		rightOwners1.setUserInsert( null);
		rightOwners1.setUserUpdate( null);
		rightOwners1.setDateUpdate(formatDate("2018-06-06"));
		rightOwners1.setCreationDate(formatDate("2018-04-17"));
		rightOwners1.setDoAffiliation( null);
		rightOwners1.setIsAffiliated( null);
		rightOwners1.setDeleted( 0);
		rightOwners1.setDummy( 0);
		rightOwners1.setDateInsert(formatDate("2018-04-17"));
		rightOwners1.setSyncVersion( "2");
		
		RightOwners rightOwners2=new RightOwners();		
		rightOwners2.setMainId( "2-P");
		rightOwners2.setType( "N");
		rightOwners2.setSex( "M");
		rightOwners2.setBirthDate(formatDate("1985-03-31"));
		rightOwners2.setDeathDate(formatDate("2050-07-28"));
		rightOwners2.setBirthPlace("Geneva");
		rightOwners2.setBirthState("SW");
		rightOwners2.setMaritalStatus( null);
		rightOwners2.setUserInsert( null);
		rightOwners2.setUserUpdate( null);
		rightOwners2.setDateUpdate(formatDate("2018-04-17"));
		rightOwners2.setCreationDate(formatDate("2018-04-17"));
		rightOwners2.setDoAffiliation( null);
		rightOwners2.setIsAffiliated( null);
		rightOwners2.setDeleted( 0);
		rightOwners2.setDummy( 0);		
		rightOwners2.setDateInsert(formatDate("2018-04-17"));
		rightOwners2.setSyncVersion( "1");
		
		list_rightowner.add(rightOwners1);
		list_rightowner.add(rightOwners2);
		
		return list_rightowner;
	}
	public static List<RightownerResponse> getRightownerResponseList(){
		list_rightowner_response=new ArrayList<RightownerResponse>();
		
		RightownerResponse rightownerResponse1=new RightownerResponse();	
		rightownerResponse1.setMainId( "1-P");
		rightownerResponse1.setType( "N");
		rightownerResponse1.setSex( "M");
		rightownerResponse1.setBirthDate(formatDate("2056-01-27"));
		rightownerResponse1.setDeathDate(formatDate("1991-12-05"));
		rightownerResponse1.setBirthPlace("Delhi");
		rightownerResponse1.setBirthState("DL");
		rightownerResponse1.setMaritalStatus( null);
		rightownerResponse1.setUserInsert( null);
		rightownerResponse1.setUserUpdate( null);
		rightownerResponse1.setDateUpdate(formatDate("2018-06-06"));
		rightownerResponse1.setCreationDate(formatDate("2018-04-17"));
		rightownerResponse1.setDoAffiliation( null);
		rightownerResponse1.setIsAffiliated( null);
		rightownerResponse1.setDeleted( 0);
		rightownerResponse1.setDummy( 0);
		rightownerResponse1.setDateInsert(formatDate("2018-04-17"));
		rightownerResponse1.setSyncVersion( "2");
		
		RightownerResponse rightownerResponse2=new RightownerResponse();		
		rightownerResponse2.setMainId( "2-P");
		rightownerResponse2.setType( "N");
		rightownerResponse2.setSex( "M");
		rightownerResponse2.setBirthDate(formatDate("1985-03-31"));
		rightownerResponse2.setDeathDate(formatDate("2050-07-28"));
		rightownerResponse2.setBirthPlace("Geneva");
		rightownerResponse2.setBirthState("SW");
		rightownerResponse2.setMaritalStatus( null);
		rightownerResponse2.setUserInsert( null);
		rightownerResponse2.setUserUpdate( null);
		rightownerResponse2.setDateUpdate(formatDate("2018-04-17"));
		rightownerResponse2.setCreationDate(formatDate("2018-04-17"));
		rightownerResponse2.setDoAffiliation( null);
		rightownerResponse2.setIsAffiliated( null);
		rightownerResponse2.setDeleted( 0);
		rightownerResponse2.setDummy( 0);		
		rightownerResponse2.setDateInsert(formatDate("2018-04-17"));
		rightownerResponse2.setSyncVersion( "1");
		
		list_rightowner_response.add(rightownerResponse1);
		list_rightowner_response.add(rightownerResponse2);
		
		return list_rightowner_response;
	}
	
	public static RightOwners getRightOwners() {
		RightOwners rightOwners=new RightOwners();
		rightOwners.setMainId( "P-1088665098");
		rightOwners.setType( "N");
		rightOwners.setSex( "M");
		rightOwners.setBirthDate(formatDate("2056-01-27"));
		rightOwners.setDeathDate(formatDate("1991-12-05"));
		rightOwners.setBirthPlace("Delhi");
		rightOwners.setBirthState("DL");
		rightOwners.setMaritalStatus( null);
		rightOwners.setUserInsert( null);
		rightOwners.setUserUpdate( null);
		rightOwners.setDateUpdate(formatDate("2018-06-06"));
		rightOwners.setCreationDate(formatDate("2018-04-17"));
		rightOwners.setDoAffiliation( null);
		rightOwners.setIsAffiliated( null);
		rightOwners.setDeleted( 0);
		rightOwners.setDummy( 0);
		rightOwners.setDateInsert(formatDate("2018-04-17"));
		rightOwners.setSyncVersion( "2");
		
		Territory territory=new Territory();
		territory.setIdTerritory(18L);
		territory.setCode( "0040");
		territory.setFkType("211");
		territory.setStartDate("2000-01-01");
		territory.setEndDate("1999-12-31");
		territory.setUserInsert( null);
		territory.setDateInsert( null);
		territory.setUserUpdate( null);
		territory.setDateUpdate( null);
		territory.setManagedByCmo( null);
		
		Account account=new Account();
		
		RightOwnersStatus rightOwnersStatus=new RightOwnersStatus();
		rightOwnersStatus.setFkValue("302");
		rightOwnersStatus.setCode("VALID");
		rightOwnersStatus.setSortOrder("3");
		rightOwnersStatus.setIdInterestedPartyStatus(3L);
		
		Cmo cmo=new Cmo();
		cmo.setIdCmo(151L);
		cmo.setName("WIPO Connect");
		cmo.setCode("700");
		cmo.setAcronym("CONNECT");
		cmo.setTerritory(null);
		cmo.setContact(null);
		cmo.setDescription(null);
		cmo.setInternationalName(null);
		cmo.setIsDataSource("1");
		cmo.setFkCmoType("13");
		
		rightOwners.setTerritory(territory);
		rightOwners.setAccount(account);
		rightOwners.setRightOwnersStatus(rightOwnersStatus);
		rightOwners.setCmo(cmo);
		return rightOwners;
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
