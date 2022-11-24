package com.ssafy.apt.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apt.AptDto;
import com.ssafy.apt.AptSearchDto;
import com.ssafy.apt.DealChartDto;
import com.ssafy.apt.DealDto;
import com.ssafy.apt.TradeDto;
import com.ssafy.apt.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {
	
	@Autowired
	private AptMapper aptMapper;

	@Override
	public void regist(AptDto aptDto) throws Exception {
		aptMapper.regist(aptDto);
	}

	@Override
	public ArrayList<AptSearchDto> getList(Map<String, Double> loc) throws Exception {
		return aptMapper.getList(loc);
	}

	private void mergeSort(List<AptSearchDto> list, int left, int right) {
		int mid;
		// Divide and Conquer
		if (left < right) {
			mid = (left + right) / 2;
			mergeSort(list, left, mid);
			mergeSort(list, mid + 1, right);
			merge(list, left, mid, right);
		}
	}

	private void merge(List<AptSearchDto> list, int left, int mid, int right) {
		// 정렬된 정보를 담는 배열
		AptSearchDto[] sorted = new AptSearchDto[list.size()];
		int l = left;
		int r = mid + 1;
		int k = left;

		while (l <= mid && r <= right) {
			// 리스트 왼쪽 인덱스의 가격 정보 받아옴
			int la = Integer.parseInt(list.get(l).getDealAmount().trim().replaceAll(",", ""));
			// 리스트 오른쪽 인덱스의 가격 정보 받아옴
			int ra = Integer.parseInt(list.get(r).getDealAmount().trim().replaceAll(",", ""));
			// 왼 <= 오 이면, 배열에 왼쪽 값을 넣음
			if (la <= ra) {
				sorted[k++] = list.get(l++);
			} else if (la > ra) {
				sorted[k++] = list.get(r++);
			}

		}
		// 왼쪽 정보를 다 담았다면, 남은 오른쪽 정보를 sorted 배열에 다 넣어줌
		if (l > mid) {
			for (int i = r; i <= right; i++) {
				sorted[k++] = list.get(i);

			}
		}
		// 오른쪽 정보를 다 담았다면, 남은 왼쪽 정보를 sorted 배열에 다 넣어줌
		if (r > right) {
			for (int i = l; i <= mid; i++) {
				sorted[k++] = list.get(i);
			}
		}
		// list에 정렬된 배열 정보 넣어줌
		for (int i = left; i <= right; i++) {
			list.set(i, sorted[i]);
		}
	}

	@Override
	public void deleteTrade(int id) throws Exception {
		aptMapper.deleteTrade(id);
	}

	@Override
	public void updateTrade(TradeDto tradeDto) throws Exception {
		aptMapper.updateTrade(tradeDto);
	}

	@Override
	public List<DealChartDto> aptTradePrice(String aptCode) throws Exception {
		return aptMapper.aptTradePrice(aptCode);
	}

	@Override
	public List<DealDto> aptDealList(String aptCode) throws Exception {
		return aptMapper.aptDealList(aptCode);
	}

	@Override
	public List<AptDto> dongSearch(String dong) throws Exception {
		return aptMapper.dongSearch(dong);
	}

	@Override
	public List<AptDto> aptSearch(String apartmentName) throws Exception {
		return aptMapper.aptSearch(apartmentName);
	}

	@Override
	public AptDto getApt(String aptCode) throws Exception {
		return aptMapper.getApt(aptCode);
	}

}
