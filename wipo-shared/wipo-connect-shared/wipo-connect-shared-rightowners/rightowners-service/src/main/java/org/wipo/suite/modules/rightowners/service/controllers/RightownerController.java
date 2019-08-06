package org.wipo.suite.modules.rightowners.service.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerDTO;
import org.wipo.suite.modules.rightowners.dal.dto.RightownerResponse;
import org.wipo.suite.modules.rightowners.dal.entity.RightOwners;
import org.wipo.suite.modules.rightowners.service.RightownerSevice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "rightowners" })
@RestController
public class RightownerController {

	@Autowired
	RightownerSevice rightownerSevice;

	@Autowired
	RightownerDTO rightownerDTO;

	@ApiOperation(value = "return the service Availability message")
	@RequestMapping(value = "/checkStatus", method = RequestMethod.HEAD)
	public void check(HttpServletResponse response) throws IOException  {
		response.setHeader("Custom-Header", "Rightowner Sevice");	    
	    response.setHeader("Status", "Ok");
	    response.getWriter().println("Rightowner Sevice is Up and Running!");
		
	}

	@ApiOperation(value = "returns the list of available rightowners")
	@GetMapping(value = "/rightowners")
	public List<RightownerResponse> getRightowners() {
		return rightownerSevice.listRightowners();
	}
	
	
	@ApiOperation(value = "returns the list of available rightowners")
	@GetMapping(value = "/rightownersHirarchy")
	public Page<RightOwners> listRightownersWithHirarchy() {
		return rightownerSevice.listRightownersWithHirarchy();
	}
		
	@ApiOperation(value = "returns the detail of the rightowners")
	@GetMapping(value = "/rightowners/{mainid}")
	@ResponseBody
	public RightownerResponse getRightownerByMainId(@PathVariable String mainid) {
		rightownerDTO.setMain_id(mainid);
		return rightownerSevice.searchByMainId(rightownerDTO);
	}

	@ApiOperation(value = "update a rightowner")
	@PutMapping(value = "/rightowners/{mainid}")	
	public RightOwners updateRightOwners(@RequestBody RightOwners rightOwners, @PathVariable String mainid) {				
		return rightownerSevice.updateRightOwners(rightOwners,mainid);
	}

	@ApiOperation(value = "Insert a rightowners")
	@PostMapping(value = "/rightowners/{mainid}")
	public RightOwners insertRightowner(@RequestBody RightOwners rightOwners,@PathVariable String mainid) {
		return rightownerSevice.insert(rightOwners,mainid);
	}

	@ApiOperation("Delete a rightowners")	
	@DeleteMapping(value = "/rightowners/{mainid}")
	public void deleteRightowner(@RequestBody RightOwners rightOwners, @PathVariable String mainid) {		
		rightownerSevice.deleteByMainId(rightOwners, mainid);
	}
	/*// Create a couple of Rightowner and account
   accountrepository.save(new Account("account 1",new RightOwner("a rightowner 1"),new RightOwner("b rightowner 2")));
   */
}
