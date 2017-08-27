package com.ogoodo.wx.test.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {

	@Autowired
	private LoadProperty load;

	@RequestMapping("/wx/test/properties/user")
	public LoadProperty user() {
		

		return load;
	}
}
