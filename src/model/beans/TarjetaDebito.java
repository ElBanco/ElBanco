package model.beans;

import java.util.Date;

public class TarjetaDebito extends Tarjeta{
	
	public Date fechaCaducidad;
	public double limiteDiario;
	public double limiteSuperior;
	public int numeroCuenta;
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
	
	public double getLimiteSuperior() {
		return limiteSuperior;
	}
	public void setLimiteSuperior(double limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
		
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	
	

}
