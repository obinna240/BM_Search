package com.pcg.objects;

import java.util.List;

public class IndexObjectBeans
{


	private List<IndexObject> indexObject;
	private PaginatorBean paginatorBean;
	
	
	public PaginatorBean getPaginatorBean() {
		return paginatorBean;
	}

	public void setPaginatorBean(PaginatorBean paginatorBean) {
		this.paginatorBean = paginatorBean;
	}

	public List<IndexObject> getIndexObject() {
		return indexObject;
	}

	public void setIndexObject(List<IndexObject> indexObject) {
		this.indexObject = indexObject;
	}
	
}