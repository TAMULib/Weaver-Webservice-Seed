/* 
 * UserController.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package edu.tamu.app.controller;

import static edu.tamu.framework.enums.ApiResponseType.SUCCESS;

import org.springframework.stereotype.Controller;

import edu.tamu.framework.aspect.annotation.ApiMapping;
import edu.tamu.framework.aspect.annotation.Auth;
import edu.tamu.framework.aspect.annotation.Shib;
import edu.tamu.framework.model.ApiResponse;
import edu.tamu.framework.model.Credentials;

/** 
 * User Controller
 * 
 * @author
 *
 */
@Controller
@ApiMapping("/user")
public class UserController {

	/**
	 * Websocket endpoint to request credentials.
	 * 
	 * @param 		shibObj			@Shib Object
	 * 
	 * @return		ApiResponse
	 * 
	 * @throws 		Exception
	 * 
	 */
	@ApiMapping("/credentials")
	@Auth
	public ApiResponse credentials(@Shib Object shibObj) throws Exception {
		return new ApiResponse(SUCCESS, (Credentials) shibObj);
	}
	
}
