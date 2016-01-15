package edu.tamu.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.app.model.CoreTheme;
import edu.tamu.app.model.ThemePropertyName;
import edu.tamu.app.model.repo.ThemePropertyNameRepo;
import edu.tamu.app.model.repo.ThemePropertyRepo;
import edu.tamu.app.model.repo.impl.CoreThemeRepoImpl;

@Service
public class ThemeManagerService {
	@Autowired
	private CoreThemeRepoImpl coreThemeRepo;
	
	@Autowired
	private ThemePropertyNameRepo themePropertyNameRepo;

	@Autowired
	private ThemePropertyRepo themePropertyRepo;

	private CoreTheme currentTheme;
	
	public ThemeManagerService() {

		//TODO Make the defaults configurable and initially loaded in a better way
		if (coreThemeRepo.count() < 1) {
			CoreTheme defaultTheme = coreThemeRepo.create("Default");
			if (themePropertyNameRepo.count() < 1) {
				Map<ThemePropertyName,String> newProperties = new HashMap<ThemePropertyName,String>();
				newProperties.put(themePropertyNameRepo.create("primary"), "#500000");
				newProperties.put(themePropertyNameRepo.create("secondary"), "#dfdfdf");
				newProperties.put(themePropertyNameRepo.create("baseFontSize"), "14pt");
				newProperties.put(themePropertyNameRepo.create("linkColor"), "#337ab7");

				newProperties.forEach((propertyName,defaultValue) -> {
					defaultTheme.addProperty(themePropertyRepo.create(propertyName,defaultValue));
				});
			}
			defaultTheme.setActive();
			coreThemeRepo.update(defaultTheme);
			currentTheme = defaultTheme;
		}
	}
	
	public CoreTheme getCurrentTheme() {
		return currentTheme;
	}
	
	public void setCurrentTheme(CoreTheme theme) {
		coreThemeRepo.updateActiveTheme(theme);
		this.currentTheme = theme;
	}

}
