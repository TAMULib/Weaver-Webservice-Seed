package edu.tamu.app.controller;

import static edu.tamu.framework.enums.ApiResponseType.ERROR;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.framework.aspect.annotation.ApiMapping;
import edu.tamu.framework.aspect.annotation.Data;
import edu.tamu.framework.model.ApiResponse;
import edu.tamu.framework.util.HttpUtility;

@Controller
@ApiMapping("/theme")
public class ThemeController {
	@Autowired
	private HttpUtility httpUtility;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@ApiMapping("/update")
	public ApiResponse update(@Data String data) throws IOException {
/* TODO Implement repo driven update that can update the content the custom wro filter will use to prep the SASS for transpiling
		JsonNode themeValues = objectMapper.readTree(data).get("themeValues");
		Path path = Paths.get("src/main/resources/static/theming.scss.template");
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path),charset);

		Iterator<Entry<String, JsonNode>> values = themeValues.fields();

		while(values.hasNext()) {
			Entry<String, JsonNode> currentRow = values.next();
			String field = currentRow.getKey();
			String value = currentRow.getValue().asText();
			String fieldValue = value != "" ? value:"inherit";
			content = content.replaceAll("\\{"+field+"\\}", fieldValue);
		}
		
		try {
			File file = new File("src/main/resources/static/theming.scss");

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("\n\nTheme SCSS updated\n\n");
			return new ApiResponse(SUCCESS,"Theme updated");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		return new ApiResponse(ERROR,"Failed to update theme");
	}

}
