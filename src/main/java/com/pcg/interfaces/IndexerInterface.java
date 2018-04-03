package com.pcg.interfaces;

/**
 * 
 * @author oonyimadu
 *
 */
public interface IndexerInterface {
	public void indexFromFile(String fileName);
	public Integer removeDocumentById(String id);
	public Integer clearIndex();
}
