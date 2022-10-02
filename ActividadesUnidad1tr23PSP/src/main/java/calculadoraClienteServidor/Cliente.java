package calculadoraClienteServidor;

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

	// El cliente se conecta al servidor y le pasa a este los numeros y el caracter
	// de la operacion que desea realizar,
	// una vez pasados los datos el servidor los procesa realizando las operaciones
	// necesarias y el cliente mostrará el resultado
	// de la operacion realizada
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		Socket socketServidor = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		double num1 = 0;
		double num2 = 0;
		char op = 0;
		
		try 
		{
			socketServidor = new Socket(HOST, Servidor.PORT);
			
			in = new DataInputStream(socketServidor.getInputStream());
			out = new DataOutputStream(socketServidor.getOutputStream());
			
			
			String mensaje = in.readUTF();
			System.out.println(mensaje);
			
			System.out.println("num1: ");
			num1 = sc.nextDouble();
			System.out.println("num2: ");
			num2 = sc.nextDouble();
			System.out.println("Caracter + - * /: ");
			op = sc.next().charAt(0);
			
			out.writeDouble(num1);
			out.writeDouble(num2);
			out.writeChar(op);
			
			System.out.println("Su cuenta es " +op+ " : ");
			double cuenta = in.readDouble();
			System.out.println(cuenta);
			
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
			catch (IOException ioException) 
			{
				ioException.printStackTrace();
			}
			try 
			{
				in.close();
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