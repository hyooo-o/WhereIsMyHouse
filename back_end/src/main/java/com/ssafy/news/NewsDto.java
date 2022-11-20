package com.ssafy.news;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsDto {

	private int news_id;
	private String news_title;
	private String news_url;

}
