package edu.tamu.app.controller;

import static edu.tamu.framework.enums.ApiResponseType.SUCCESS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.framework.aspect.annotation.ApiMapping;
import edu.tamu.framework.aspect.annotation.Data;
import edu.tamu.framework.aspect.annotation.Auth;
import edu.tamu.framework.model.ApiResponse;
import edu.tamu.framework.model.CoreTheme;
import edu.tamu.framework.model.repo.CoreThemeRepo;
import edu.tamu.framework.service.ThemeManagerService;
import edu.tamu.framework.util.HttpUtility;

@Controller
@ApiMapping("/theme")
public class ThemeController {
	@Autowired
	private HttpUtility httpUtility;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired 
	CoreThemeRepo coreThemeRepo;
	
	@Autowired
	ThemeManagerService themeManagerService;
	
	@ApiMapping("/all")
	@Auth(role="ROLE_ADMIN")
	public ApiResponse getAll() {
		Map<String,List<CoreTheme>> coreThemes = new HashMap<String,List<CoreTheme>>();
		coreThemes.put("list", coreThemeRepo.findAll());
		return new ApiResponse(SUCCESS,coreThemes);
	}
	
	@ApiMapping("/update-property")
	@Auth(role="ROLE_ADMIN")
	public ApiResponse updateProperty(@Data String data) throws IOException {
		Long themeId = objectMapper.readTree(data).get("themeId").asLong();
		Long propertyId = objectMapper.readTree(data).get("propertyId").asLong();
		String value = objectMapper.readTree(data).get("value").asText();
		themeManagerService.updateThemeProperty(themeId,propertyId,value);
		
		return new ApiResponse(SUCCESS,"Theme updated",themeManagerService.getCurrentTheme());
	}
	
	@ApiMapping("/add-theme")
	@Auth(role="ROLE_ADMIN")
	public ApiResponse addTheme(@Data String data) throws IOException {
		String themeName = objectMapper.readTree(data).get("newTheme").get("name").asText();
		CoreTheme newTheme = coreThemeRepo.create(themeName);
		return new ApiResponse(SUCCESS,"Theme added",newTheme);
	}
	
	@ApiMapping("/activate-theme")
	@Auth(role="ROLE_ADMIN")
	public ApiResponse activateTheme(@Data String data) throws IOException {
		Long themeId = objectMapper.readTree(data).get("themeId").asLong();
		themeManagerService.setCurrentTheme(coreThemeRepo.getById(themeId));
		return new ApiResponse(SUCCESS,"Theme activated");
	}
}
