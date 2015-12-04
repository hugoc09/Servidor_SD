package Internet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entidades.Palavra;

import java.util.ArrayList;
import java.util.List;
public class Traducao {

	public static final String URL_Palavra = "https://www.googleapis.com/language/translate/v2?key=AIzaSyC2-Ruvk61nvaGbb0428TJl85kBFwAN510&q=";
	public static final String URL_Language1 = "&source=";
	public static final String URL_Language2 = "&target=";
	/**
	 * 
	 * Método que extraí de uma string JSON a palavra traduzida
	 * 
	 * @param String
	 *            json
	 * @return A palavra traduzida
	 * @throws JSONException
	 */
	public static List<String> parseJson(String json) throws JSONException {
		List<String> traducoes = new ArrayList<>();
		JSONObject j = new JSONObject(json);

		JSONObject aux = new JSONObject(j.getJSONObject("data").toString());
		JSONArray aux2 = aux.getJSONArray("translations");

		for(int i=0;i<aux2.length();i++){
			
			JSONObject aux3 = aux2.getJSONObject(i);
			traducoes.add(aux3.getString("translatedText"));
		}
		
		return traducoes;
	}

	/**
	 * 
	 * Método que utiliza de uma requisição GET no serviço web para a tradução
	 * da palavra
	 * 
	 * @param palavra
	 *            a ser traduzida
	 * @return uma String JSON
	 */

	public static String getData(Palavra p) {

		String urlString = URL_Palavra +p.getPalavra1()+ URL_Language1+ p.getLinguagem1() + URL_Language2 + p.getLinguagem2();
		String r = "";
		try {

			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			conn.setDoInput(true);
			try {
				conn.connect();	
			} catch (Exception e) {
				//throw  new ConnectException();
			}
			BufferedReader lines = null;
			InputStream is = null;
			
			try {
				 is = conn.getInputStream();
				 lines = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			} catch (Exception e) {
				//throw  new ConnectException();
				//System.out.println("Aqui 3");
			}
			
			String s;
			while (true) {

				String line = lines.readLine();
				if (line != null) {
					s = r;
					r = s + line;
				} else {
					break;
				}

			}

			System.out.println(r);

			lines.close();
			is.close();
			conn.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;
	}

}