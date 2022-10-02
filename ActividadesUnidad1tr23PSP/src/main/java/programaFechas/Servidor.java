package programaFechas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Servidor 
{

	// Creamos la variable del puerto al que accederá el cliente
	public static final int PORT = 2000;

	// El servidor iniciará la conexion para que un cliente acceda y pueda pasarle
	// dos fechas en formato String, que posteriormente
	// procesará el servidor, las convertirá a tipo Date y este a traves de un
	// metodo con equals after y before comprobará
	// cual es mas antigua que la otra, estos datos el servidor se los pasará al
	// cliente y este lo mostrará por pantalla
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
			
			out.writeUTF("Hola cliente, conexion recibida! Escribe dos Fechas (yyyy-MM-dd): ");
			String fechaF1 = in.readUTF();
			String fechaF2 = in.readUTF();
			
			out.writeUTF(calcularFechas(fechaF1, fechaF2));
			
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
	
	public static String calcularFechas(String fechaF1, String fechaF2) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fecha1 = null;
		Date fecha2 = null;
		
		String mensaje = "";
		
		try 
		{
			fecha1 = formato.parse(fechaF1);
		} 
		catch (ParseException parse) 
		{
			parse.printStackTrace();
		}
		try 
		{
			fecha2 = formato.parse(fechaF2);
		} 
		catch (ParseException parse) 
		{
			parse.printStackTrace();
		}
		
		if(fecha1.equals(fecha2))
		{
		    mensaje = "Fecha 1 es igual a fecha 2";
		 }
		else if(fecha1.after(fecha2))
		{
		    mensaje = "Fecha 1 es mayor a fecha 2";
		 }
		else if(fecha1.before(fecha2))
		{
		    mensaje = "Fecha 1 es menor a fecha 2";
		}
		
		return mensaje;
		
	}

}