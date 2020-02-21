package com.example.restfulwebservices.filter;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", SimpleBeanPropertyFilter
						.filterOutAllExcept("field1", "field2"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeBeans() {
		List<SomeBean> list = Arrays.asList(
				new SomeBean("values1", "value2", "value3"),
				new SomeBean("values21", "value22", "value23")
			);
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", SimpleBeanPropertyFilter
						.filterOutAllExcept("field2", "field3"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		
		return mapping;
	}
	
}
