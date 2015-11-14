package Redes;

import java.util.List;

import org.json.JSONException;

import Entidades.Palavra;

import util.Traducao;

public class NetworkManagement {

	public static Palavra traduzir(Palavra p) throws JSONException {

		String json = Traducao.getData(p);

		List<String> palavrasTraduzidas = Traducao.parseJson(json);

		p.setPalavra2(palavrasTraduzidas.get(0));
		
		return p;
	

	}

}
