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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.app.model.AppUser;
import edu.tamu.app.model.repo.AppUserRepo;
import edu.tamu.framework.aspect.annotation.ApiCredentials;
import edu.tamu.framework.aspect.annotation.ApiData;
import edu.tamu.framework.aspect.annotation.ApiMapping;
import edu.tamu.framework.aspect.annotation.Auth;
import edu.tamu.framework.enums.CoreRole;
import edu.tamu.framework.model.ApiResponse;
import edu.tamu.framework.model.Credentials;

/**
 * User Controller
 * 
 */
@Controller
@ApiMapping("/user")
public class UserController {

    @Autowired
    private AppUserRepo userRepo;
    
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Websocket endpoint to request credentials.
     * 
     * @param shibObj
     *            Object
     * 
     * @return ApiResponse
     * 
     * @throws Exception
     * 
     */
    @ApiMapping("/credentials")
    @Auth(role = "ROLE_USER")
    public ApiResponse credentials(@ApiCredentials Credentials credentials) throws Exception {
        return new ApiResponse(SUCCESS, credentials);
    }

    /**
     * Returns all users.
     * 
     * @return
     * @throws Exception
     */
    @ApiMapping("/all")
    @Auth(role = "ROLE_MANAGER")
    public ApiResponse allUsers() throws Exception {
        Map<String, List<AppUser>> map = new HashMap<String, List<AppUser>>();
        map.put("list", userRepo.findAll());
        return new ApiResponse(SUCCESS, map);
    }
    
    /**
     * Returns all users.
     * 
     * @return
     * @throws Exception
     */
    @ApiMapping("/update-role")
    @Auth(role = "ROLE_MANAGER")
    public ApiResponse updateUserRole(@ApiData String data) throws Exception {
        Long uin = objectMapper.readTree(data).get("uin").asLong();
        String role = objectMapper.readTree(data).get("role").asText();
        AppUser user = userRepo.findByUin(uin);
        user.setRole(CoreRole.valueOf(role));
        return new ApiResponse(SUCCESS, userRepo.save(user));
    }

}
