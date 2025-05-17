package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=97e5b0ba");
		var conversor = new ConverteDados();

		var dadosSerie = conversor.getData(json, DadosSerie.class);
		System.out.println(dadosSerie);

		var dadosS = new DadosSerie(dadosSerie.titulo(), dadosSerie.totalTemporadas(), dadosSerie.avaliacao());

		var convert = conversor.saveData(dadosS);

		System.out.println(convert);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=97e5b0ba");

		var dadosEpisodio = conversor.getData(json, DadosEpisodio.class);

		System.out.println(dadosEpisodio);

	}
}
