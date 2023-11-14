package br.com.fiap.Model;

import java.time.LocalDateTime;
import java.util.Date;

import br.com.fiap.Model.JsonProperty;

public class Apolice {
	@JsonProperty("cd_apolice")
	private int codigo;
	@JsonProperty("nr_numero")
	private int numero;
	@JsonProperty("nm_seguradora")
	private String seguradora;
	@JsonProperty("nr_valor")
	private double valor;
	@JsonProperty("dt_inicio")
	private LocalDateTime dataInicio;
	@JsonProperty("dt_fim")
	private LocalDateTime dataFim;

	public Apolice() {

	}

	public Apolice(int numero, String seguradora, LocalDateTime dataInicio, LocalDateTime dataFim, double valor) {
		this.numero = numero;

		this.seguradora = seguradora;

		this.dataInicio = dataInicio;

		this.dataFim = dataFim;

		this.valor = valor;
	}

	public Apolice(int id, int numero, String seguradora, double valor, Object dataInicio, Object dataFim) {
		this.numero = numero;

		this.seguradora = seguradora;

		this.dataInicio = (LocalDateTime) dataInicio;

		this.dataFim = (LocalDateTime) dataFim;

		this.valor = valor;
	}

	public Apolice(int numero, String seguradora, Date dataInicio, Date dataFim, double valor) {
		this.numero = numero;

		this.seguradora = seguradora;

		this.valor = valor;

	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(String seguradora) {
		this.seguradora = seguradora;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Apolice [Número=" + numero + ", Seguradora=" + seguradora + ", Data de Início=" + dataInicio
				+ ", Data de Fim=" + dataFim + ", Valor=" + valor + "]";
	}
}
