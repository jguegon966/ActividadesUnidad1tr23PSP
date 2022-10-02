package programaFechas;

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

	// El cliente accederá al servidor y le pasará dos fechas en formato String, el
	// servidor internamente pasará estas fechas en String a Dateç
	// y con un simpledateFormat comprobará su formato si es correcto comprobará
	// cual es mas antigua y le pasará dichos datos al cliente
	// que será el que lo mostrará por pantalla
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		Socket socketServidor = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		String fecha1 = "";
		String fecha2 = "";
		
		try 
		{
			socketServidor = new Socket(HOST, Servidor.PORT);
			
			in = new DataInputStream(socketServidor.getInputStream());
			out = new DataOutputStream(socketServidor.getOutputStream());
			
			
			String mensaje = in.readUTF();
			System.out.println(mensaje);
			
			System.out.println("Fecha 1: ");
			fecha1 = sc.nextLine();
			System.out.println("Fecha 2: ");
			fecha2 = sc.nextLine();
			
			out.writeUTF(fecha1);
			out.writeUTF(fecha2);
			
			String mensaje2 = in.readUTF();
			System.out.println(mensaje2);
			
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