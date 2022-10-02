package programaCuadrado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente 
{
	// Variable con la ip desde la que accederá el cliente
	private static final String HOST = "localHost";

	// El cliente le pasa al servidor el numero al que desea realizar la raiz
	// cuadrada
	// el servidor procesa dicho numero y con un metodo realiza su raiz y le
	// devuelve el resultado al cliente y este lo muestra por pantalla
	// (apartado a: en caso de que queramos que el servidor muestre el resultado en
	// vez del cliente, deberemos cambiar el metodo que realiza la raiz y haciendo
	// que muestre un String con un input directamente desde el servidor)
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		Socket socketServidor = null;
		DataInputStream flujoEntrada = null;
		DataOutputStream out = null;
		double numero = 0;
		
		try 
		{
			socketServidor = new Socket(HOST, Servidor.PORT);
			
			flujoEntrada = new DataInputStream(socketServidor.getInputStream());
			out = new DataOutputStream(socketServidor.getOutputStream());
			
			
			String mensaje = flujoEntrada.readUTF();
			System.out.println(mensaje);
			
			numero = sc.nextDouble();
			out.writeDouble(numero);
			
			System.out.println("Su raiz Cuadrada es: ");
			float cuadrado = flujoEntrada.readFloat();
			System.out.println(cuadrado);
			
		} 
		catch (UnknownHostException unknownHostException) 
		{
			unknownHostException.printStackTrace();
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		}
		finally
		{
			
			try 
			{
				out.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				flujoEntrada.close();
			} 
			catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
			
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