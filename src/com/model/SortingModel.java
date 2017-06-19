package com.model;

/**
 * 
 * @author 582571
 * Create SortingModel class to create getter and setter methods for sorting object
 */
public class SortingModel {
	private String sortingList;
	private String originalList;
    private long timing;
    private long positionChangeCount;
    
    public String getSortingList() {
        return sortingList;
}

public void setSortingList(String sortingList) {
        this.sortingList = sortingList;
}

public String getOriginalList() {
    return originalList;
}

public void setOriginalList(String originalList) {
    this.originalList = originalList;
}

public long getTiming() {
        return timing;
}

public void setTiming(long timing) {
        this.timing = timing;
}
    
public long getPositionChangeCount() {
    return positionChangeCount;
}

public void setPositionChangeCount(long positionChangeCount) {
    this.positionChangeCount = positionChangeCount;
}
    }

