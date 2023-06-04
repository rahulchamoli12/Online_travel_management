package com.masai.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
	private Integer feedbackId;
	private Integer packageId;
	private String customerName;
	private String feedbackDesciption;
	private Integer rating;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime submitTime;

}
