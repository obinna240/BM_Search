package com.pcg.factory;

import com.pcg.indexObjects.BirminghamIndexer;
import com.pcg.interfaces.IndexerInterface;

public class FactoryIndexer {

	public static IndexerInterface getIndexer(String indexerType) throws Exception
	{
		if(indexerType.equals("BM_Events")||indexerType.equals("Birmingham_Events"))
		{
			return new BirminghamIndexer();
		}
		throw new Exception("Not a valid Event or Product");
	}
}
