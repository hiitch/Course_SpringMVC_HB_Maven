package com.webcustomer.utils;

import java.util.HashMap;
import java.util.Map;

public enum SortField {
	FIRST_NAME(1, "firstName"), LAST_NAME(2, "lastName"), EMAIL(3, "email");
	
	private static final Map<Integer, String> BY_SORT_INDEX = new HashMap<>();
	
	static {
		for (SortField sf : values()) {
			BY_SORT_INDEX.put(sf.sortIndex, sf.sortLabel);
		}
	}
	
	public final int sortIndex;
	public final String sortLabel;
	
	private SortField(int index, String label) {
		this.sortIndex = index;
		this.sortLabel = label;
	}
	
	public static String labelOfSortFieldByIndex(int index) {
		return BY_SORT_INDEX.get(index);
	}
}
