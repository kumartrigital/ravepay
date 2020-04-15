package com.inview.revpay.model;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {


	private Date timestamp;
	private String details;
	private Integer status;
	private Map<String, Object> response;

}
