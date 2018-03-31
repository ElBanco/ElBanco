package model.beans;

import java.util.Date;

public class Operacion {
	
	public int operacionID;
	public Date fecha;
	public Double cantidad;
	
	public int getOperacionID() {
		return operacionID;
	}
	public void setOperacionID(int operacionID) {
		this.operacionID = operacionID;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	

}
