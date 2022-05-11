package jp.web_scraping.Classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class capturarDados {

	public static List<String> cLoading(int qPosition){	
		
		List<String> paulo = carregarSite(qPosition);
		
		return paulo;
		
    
	}
	
	
	public static List<String> carregarSite(int qPosition) {
		
		List<Element> atletas = new ArrayList<>();
		List<String> linkCapturado = new ArrayList<>();

		String url = "https://www.flamengo.com.br/elencos/elenco-profissional";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			System.out.println("Falha ao tentar acessar o site!");
		}

		Element cardGeral = doc.getElementsByClass("container py-5").first();;
		List<Element> rowGeral = cardGeral.getElementsByClass("row");
		
		
		if (qPosition == 1){
          atletas.addAll(cardGeral.getElementsByClass("elenco-atleta"));
		} else if (qPosition == 2) {
		  Element rowPosition = rowGeral.get(0);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		} else if (qPosition == 3) {
		  Element rowPosition = rowGeral.get(1);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		} else if (qPosition == 4) {
		  Element rowPosition = rowGeral.get(2);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		} else if (qPosition == 5) {
		  Element rowPosition = rowGeral.get(3);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		} else if (qPosition == 6) {
		  Element rowPosition = rowGeral.get(4);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		}else if (qPosition == 7) {
		  Element rowPosition = rowGeral.get(5);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		}else if (qPosition == 8) {
		  Element rowPosition = rowGeral.get(6);
		  atletas.addAll(rowPosition.getElementsByClass("elenco-atleta"));
		}
		
		linkCapturado = linksJogadores(atletas);


		// 2 - ACESSA CADA LINK E PEGA OS DADOS DO ATLETA
		List<String> atleta = new ArrayList<>();
		for (String linkJogador : linkCapturado) {
			try {
				doc = Jsoup.connect(linkJogador).get();
				atleta.addAll(dadosJogadores(doc));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return atleta;
	}
	

	// MÉTODO PARA PEGAR LINK DOS JOGADORES
	public static List<String> linksJogadores(List<Element> atletas) {
		List<String> linkAtleta = new ArrayList<>();
		for (Element atleta : atletas) {
			linkAtleta.add(atleta.getElementsByTag("a").attr("href"));
		}
		return linkAtleta;

	}

	// MÉTODO PARA PEGAR DADOS DOS JOGADORES
	public static List<String> dadosJogadores(Document links) {
		
		List<String> allDados = new ArrayList<>();
		
		Element dadosGeral = links.getElementsByClass("card-body pl-10 p-relative info-persona").first();
		String allApelido = dadosGeral.getElementsByTag("h2").text();
		String allNome = dadosGeral.getElementsByTag("p").text();
		String allNumero = dadosGeral.getElementsByClass("text-danger d-flex justify-content-center align-items-center font-weight-bold shirt-number").text();
		String allPosicao = dadosGeral.getElementsByTag("li").get(0).text().replace("Posição: ", "");
		String allPosicaoR = allPosicao.substring(0, allPosicao.length()-1);
		String allNascimento = dadosGeral.getElementsByTag("li").get(1).text().replace("Nascimento: ", "");;;
		String allCidade = dadosGeral.getElementsByTag("li").get(2).text().replace("Cidade: ", "");;
		String dadosJogadores = "\nApelido: " + allApelido + 
				                 "\nNome: " + allNome + 
				                 "\nNúmero: " + allNumero + 
				                 "\nPosição: " + allPosicaoR + 
				                 "\nNascimento: " + allNascimento + 
				                 "\nCidade: " + allCidade;


		allDados.add(dadosJogadores);
		 
		 

		return allDados;

	}


}
