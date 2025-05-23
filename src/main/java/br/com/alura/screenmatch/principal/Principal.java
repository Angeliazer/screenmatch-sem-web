package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=97e5b0ba";
    private ConsumoApi consumoApi = new ConsumoApi();
    private  ConverteDados conversor = new ConverteDados();


    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");

        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        var dadosSerie = conversor.getData(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++){
			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			var dadosTemporada = conversor.getData(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(tempo -> System.out.println(tempo));

    }
}
