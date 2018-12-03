package com.stp.auth.restService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stp.auth.model.Penerbangan;
import com.stp.auth.service.PenerbanganService;

@RestController
public class RestServiceApi {

	@Autowired
	private PenerbanganService penerbanganService;
	
	@RequestMapping("/kemasKiniUpdate")
	public String greeting(@RequestParam(value = "id") Long id) {
		
		List<Penerbangan> p = penerbanganService.findByPenerbanganId(id);
		
		ArrayList<String> pData = new ArrayList<String>();
		pData.add(p.get(0).getPenerbanganId().toString());
		pData.add(p.get(0).getDariLokasi().toString());
		pData.add(p.get(0).getJenisPesawat());
		pData.add(p.get(0).getNoPesawat());
		pData.add(p.get(0).getWaktuBerlepas());
		pData.add(p.get(0).getDestinasi());
		
		String json = new Gson().toJson(pData);
		
		return json;
	}

}