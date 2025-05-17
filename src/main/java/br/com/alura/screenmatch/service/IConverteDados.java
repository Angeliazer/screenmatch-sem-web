package br.com.alura.screenmatch.service;

public interface IConverteDados {
    <T> T getData(String json, Class<T> classed);
    <T> String saveData(T t);
}
