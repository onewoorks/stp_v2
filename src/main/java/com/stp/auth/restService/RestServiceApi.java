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
		
		List<Penerbangan> p = penerbanganService.getAll();
		ArrayList<PenerbanganPOJO> pData = new ArrayList<PenerbanganPOJO>();
		
		for (int i = 0; i < p.size(); i++) {
			if(p.get(i).getPermohonan().getId() == id) {
				
				PenerbanganPOJO pojo = new PenerbanganPOJO();
				pojo.setPenerbanganId(p.get(i).getPenerbanganId());
				pojo.setPenerbangan(p.get(i).getPenerbangan());
				pojo.setTarikhPergi(p.get(i).getTarikhPergi());
				pojo.setWaktuBerlepas(p.get(i).getWaktuBerlepas());
				pojo.setWaktuTiba(p.get(i).getWaktuTiba());
				pojo.setJenisPesawat(p.get(i).getJenisPesawat());
				pojo.setNoPesawat(p.get(i).getNoPesawat());
				pojo.setDariLokasi(p.get(i).getDariLokasi());
				pojo.setDestinasi(p.get(i).getDestinasi());
				pojo.setNoPesawat(p.get(i).getNoPesawat());
				
				pData.add(pojo);
			}
		}
		

		
		String json = new Gson().toJson(pData);
		
		return json;
	}

}