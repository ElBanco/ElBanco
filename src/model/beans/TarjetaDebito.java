package model.beans;

import java.util.Date;

public class TarjetaDebito extends Tarjeta{
	
	public Date fechaCaducidad;
	public Double limiteDiario;
	public Double limiteSuperior;
	public String numeroCuenta;
	public String titular;
	
	
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public double getLimiteDiario() {
		return limiteDiario;
	}
	public void setLimiteDiario(double limiteDiario) {
		this.limiteDiario = limiteDiario;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public Double getLimiteSuperior() {
		return limiteSuperior;
	}
	public void setLimiteSuperior(Double limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
	public void setLimiteDiario(Double limiteDiario) {
		this.limiteDiario = limiteDiario;
	}
	
	
	

}
