package programaCuadrado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{

	// Creamos la variable del puerto al que accederá el cliente
	public static final int PORT = 2000;

	// El Servidor abre su socket para que el cliente se conecte, una vez abierto el
	// servidor el cliente accede y le enviará
	// el numero que quiere que se le haga la raiz cuadrada, una vez el cliente le
	// pasa el numero, el servidor tendrá un metodo que realizará
	// la raiz cuadrada de dicho numero y le devolverá un double, este se le pasará
	// al cliente y lo mostrará por pantalla el propio cliente
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
			
			out.writeUTF("Hola cliente, conexion recibida! Escribe un numero para hacer su raiz: ");
			double numero = in.readDouble();
			
			out.writeFloat((float) Cuadrado(numero));
			
			socketCliente.close();
			socketServidor.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(in != null) 
			{
				try 
				{
					in.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
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
	
	public static double Cuadrado(double numero) {
		
		double resultado = Math.sqrt(numero);
		
		return resultado;
		
	}
	
}
