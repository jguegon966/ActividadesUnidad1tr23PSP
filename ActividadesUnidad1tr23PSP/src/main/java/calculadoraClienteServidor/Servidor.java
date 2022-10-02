package calculadoraClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	// Creamos la variable del puerto al que accederá el cliente
	public static final int PORT = 2000;

	// El Servidor abre su socket para que el cliente se conecte, una vez abierto el
	// servidor el cliente accede y le enviará
	// los numeros de los que desea realizar una operacion, una vez el cliente le
	// pasa los numeros, el servidor tendrá un metodo que realizará
	// la cuenta indicada de dichos numeros y le devolverá un double, este se le
	// pasará al cliente y lo mostrará por pantalla el propio cliente
	public static void main(String[] args) 
	{
		
		ServerSocket socketServidor = null;
		Socket socketCliente = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		
		try 
		{
			socketServidor = new ServerSocket(PORT);
			socketCliente = socketServidor.accept();
			out = new DataOutputStream(socketCliente.getOutputStream());
			in = new DataInputStream(socketCliente.getInputStream());
			
			out.writeUTF("Hola cliente, conexion recibida! Escribe dos numeros y un caracter");
			double num1 = in.readDouble();
			double num2 = in.readDouble();
			
			char op = in.readChar();
			
			out.writeDouble(Calculadora(num1, num2, op));
			
			socketCliente.close();
			socketServidor.close();
			
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		}
		finally
		{
			if(in != null) 
			{
				try 
				{
					in.close();
				} 
				catch (IOException ioException) 
				{
					ioException.printStackTrace();
				}
			}
			if(out != null)
			{
				try 
				{
					out.close();
				} 
				catch (IOException ioException) 
				{
					ioException.printStackTrace();
				}
			}
			if(socketCliente != null)
			{
				try 
				{
					socketCliente.close();
				} 
				catch (IOException ioException) 
				{
					ioException.printStackTrace();
				}
			}
			if(socketServidor != null)
			{
				try 
				{
					socketServidor.close();
				} 
				catch (IOException ioException) 
				{
					ioException.printStackTrace();
				}
			}
		}
		
	}
	
	//Metodo Calculadora, que procesará los datos que se le pasan en funcion si desea una suma, una resta, una multiplicacion o division
	public static double Calculadora(double num1, double num2, char op) {

		// Declaramos las variables necesarias
		boolean ok = true;
		double result = 0;

		// con el switch realizamos la operacion deseada pero sin mostrarla
		switch (op) {

		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num1 / num2;
			break;

		default:
			ok = false;// en caso de error ok = false

		}

		// mostramos la operacion a continuacion con un boolean para que el programa sea
		// mas eficiente
		if (ok) 
		{
			return result;
		} 
		else 
		{
			return result = 0;
		}

	}

}