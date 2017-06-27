package com.pb.services.userRelated;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Institution;
import com.pb.services.common.CommService;

@Service(value = "institutionService")
public class InstitutionService extends CommService {

	
	public List<Institution> findAll() {
		List<Institution> list = baseDAO.findByHQL("from Institution");
		return list;
	}
	
}
