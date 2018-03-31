package model.beans;

public class Transferencia extends Operacion{

	public int numeroCuentaOrigen;
	public int numeroCuentaDestino;
	
	
	public int getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}
	public void setNumeroCuentaOrigen(int numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}
	public int getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}
	public void setNumeroCuentaDestino(int numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	
}
