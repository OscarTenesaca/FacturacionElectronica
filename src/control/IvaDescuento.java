package control;

public class IvaDescuento {
	double iva=0.12;
	double subtotalUno=0;
	double desc=0;
	double total=0;
	
	public  double calculoIva(double subtotal) {
		subtotalUno = subtotal*iva;
		total = subtotalUno + subtotal;

		return subtotalUno;
	}
	public double calculoDescuento(double subtotalTres,double descuento) {
		descuento = descuento/100;
		desc = subtotalTres * descuento;
		
		return desc;
		
	}
	
}
