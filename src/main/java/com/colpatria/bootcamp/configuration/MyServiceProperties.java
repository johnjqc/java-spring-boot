package com.colpatria.bootcamp.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@ToString
public class MyServiceProperties {

	@NotBlank
	private String url;

}
