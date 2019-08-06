package org.wipo.suite.modules.name.service.controllers;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.suite.modules.name.dal.entity.Name;
import org.wipo.suite.modules.name.dal.entity.RightOwnersName;
import org.wipo.suite.modules.name.dto.NameDTO;
import org.wipo.suite.modules.name.service.NameSevice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(tags= {"names"})
@RestController
public class NameController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NameController.class);
	@Autowired
	NameSevice nameService;
	
	@Autowired
	NameDTO nameDto;
	
	
	@ApiOperation(value="returns the availability of the service")
	@RequestMapping(value="/names", method=RequestMethod.HEAD)
	public void testAvailabilityOfTheService(HttpServletResponse response) throws Exception{
		response.setHeader("Custom-Header", "Rightowner Sevice");	    
	    response.setHeader("Status", "Ok");
	    response.getWriter().println("Rightowner Sevice is Up and Running!");
	}
	@ApiOperation(value="returns the list of available names")
	@GetMapping(value="/names")
	public Page<Name> getAllNames( @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "25") Integer pageSize,
            @RequestParam(defaultValue = "mainId") String sortBy) throws Exception{
		try {
			return nameService.listNames(pageNo, pageSize, sortBy);	
		}catch (Exception e) {
			LOGGER.error("error in calling getAllNames : {} ", e.getMessage());			
			throw new RuntimeException(e.getMessage());
		}		
	}
    @ApiOperation(value="returns the detail of the name")
    @ApiImplicitParams(@ApiImplicitParam(paramType="mainId",name="mainId", value="Name's main Id", required=true, dataType="String"))
    @GetMapping(value="/names/{mainId}")
    public Name getNameByMainId(@PathVariable String mainId) throws Exception{
    	try {
        	nameDto.setMainId(mainId);
        	return  nameService.searchByMainId(nameDto);
    	}catch (Exception e) {
    		LOGGER.error("error in calling getNameByMainId : {} ", e.getMessage());	
			throw new RuntimeException(e.getMessage());
		}    	
    }
    @ApiOperation(value="update a existing name")
    @ApiImplicitParams({@ApiImplicitParam(paramType="mainId",name="mainId", value="Name's main Id", required=true, dataType="String"),
    @ApiImplicitParam(paramType="body",name="inputName", value="JSON Example: {idName: param1, value: 1, name: param2, value: val2, firstName: param3, value: val3}" ,required=true, allowMultiple=false)})
    @RequestMapping(value="/names/{mainId}", method=RequestMethod.PUT)
    public Name updateName(@RequestBody Name name, @PathVariable String mainId){
    	try {
    	   	name.setMainId(mainId); 
        	return nameService.update(name);
    	}catch (Exception e) {
    		LOGGER.error("error in calling updateName : {} ", e.getMessage());	
    		throw new RuntimeException(e.getMessage());
		}    	
    }
    @ApiOperation(value="Insert a new name")
    @ApiImplicitParams(@ApiImplicitParam(paramType="body",name="inputName", 
    value="JSON Example: {idName: param1, value: 1, name: param2, value: val2, firstName: param3, value: val3}", required=true, allowMultiple=false))
    @PostMapping(value="/names")
    public String insertName(@RequestBody Name name) {
    	try {
    		return nameService.insert(name);	
    	}catch (Exception e) {
    		LOGGER.error("error in calling insertName : {} ", e.getMessage());	
    		throw new RuntimeException(e.getMessage());
		} 	
    }
   
    @ApiOperation("Delete a existing name")
    @DeleteMapping(value="/names/{mainId}")
    public Boolean deleteName(@PathVariable String mainId) throws Exception{
    	try {
        	nameDto.setMainId(mainId);
        	return nameService.deleteByMainId(nameDto);	
    	}catch (Exception e) {
    		LOGGER.error("error in calling deleteName : {} ", e.getMessage());	
    		throw new RuntimeException(e.getMessage());
		}
    }
    
    //get all interested party name
    
    @ApiOperation(value="returns the list of interested party name")
	@GetMapping(value="/rightOwnerNames")
	public Page<RightOwnersName> getAllRightOwnerNames() throws Exception{
		try {
			return nameService.listRightOwnerNames();	
		}catch (Exception e) {
			LOGGER.error("error in calling getAllRightOwnerNames : {} ", e.getMessage());	
			throw new RuntimeException(e.getMessage());
		}		
	}
	
}
