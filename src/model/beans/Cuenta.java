package model.beans;

import java.util.Date;

public class Cuenta {
	
	public int numeroCuenta;
	public String nombreUsuario;
	public Double saldo;
	public Double limiteDiario;
	public Double limiteInferior;
	public Date fechaCreacion;
	public Date fechaModificacion;
	public Date fechaBaja;
	
	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(Double limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public Double getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(Double limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	
	  
}
