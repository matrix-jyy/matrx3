package com.zkingsoft.services.bus;

import java.util.List;

import com.zkingsoft.constraint.BaseServices;
import com.zkingsoft.model.bus.BusArea;

public interface BusAreaService extends BaseServices<BusArea> {

	public List<BusArea> findChildArea(Long parentId);

	int batchAdd(List<BusArea> obje);

}