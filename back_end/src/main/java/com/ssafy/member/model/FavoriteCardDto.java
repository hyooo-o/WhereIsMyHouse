package com.ssafy.member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteCardDto {
	String aptCode;
	String apartmentName;
	int maxPrice;
	int minPrice;
}
