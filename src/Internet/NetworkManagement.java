package Internet;


import java.util.List;

import org.json.JSONException;

import Entidades.Palavra;

public class NetworkManagement {

	public Palavra traduzir(Palavra p) throws JSONException, Exception {

		String json = Traducao.getData(p);

		List<String> palavrasTraduzidas = Traducao.parseJson(json);

		p.setPalavra2(palavrasTraduzidas.get(0));
		
		return p;
	

	}

}
