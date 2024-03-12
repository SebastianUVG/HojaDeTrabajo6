package uvg.edu.gt;

/**
 * @author Sebastian Garcia Bustamante
 *
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import static java.util.stream.Collectors.*;


public class App 
{
    public static String[] textReader() throws Exception {
		
		/**
		 * Ahi se recuerda de cambiar la ruta del archivo, dependiendo donde vaya a correr el programa.
		 */
		
		String rutaArchivo = "C:\\Users\\sebas\\OneDrive\\Escritorio\\Emergencia\\hoja6\\src\\main\\java\\uvg\\edu\\gt\\cards_desc.txt";

		final BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String[] lineList = new String[8861];
		String line = "";
		int cont = 0;
		while ((line = br.readLine()) != null) {
			lineList[cont] = line;
			cont++;
		}

		br.close();
		return lineList;
	}

/*Contador que iran registrando la cantidad de cartas segun su tipo */
    static int countMon = 0;
	static int countTram = 0;
	static int countHech = 0;

    public static void main(final String[] args ) throws Exception
    {
       Scanner scan = new Scanner(System.in);
		boolean confirmacion = true;
		int mapType = 0;
		while (confirmacion) {
			System.out.println("Eliga el tipo de Map que quiere utlizar:");
			System.out.println("1. HashMap");
			System.out.println("2. TreeMap");
			System.out.println("3. LinkedHashMap");
			String mapTypeSt = scan.nextLine();
			try {
				mapType = Integer.parseInt(mapTypeSt);
				if (mapType > 0 && mapType <= 3) {
					confirmacion = false;
				}
			} catch (Exception e) {
				System.out.println("Y eso que???");
			}
		}

		/**
		 * Se lee el archivo de texto
		 */
		final String[] list = textReader();
		/**
		 * Creamos el objeto mapFactory para crear dos mapas en los cuales se le asignan los parametros correspondientes
		 * Segun lo que el usuario elijio.
		 */

		MapFactory<String, String> mapFactory = new MapFactory<String, String>();
		uvg.edu.gt.Map<String, String> map =  mapFactory.getMap(mapType);
		uvg.edu.gt.Map<String, String> userMap = mapFactory.getMap(mapType);

		/**
		 * Comienza la lectura de los elementos del archivo en la lista para ingresarlos
		 * al Map.
		 */
		String[] keyAndValue = new String[2];
		long Tinicio = System.currentTimeMillis();
		for (String cardInList : list) {
			keyAndValue[0] = cardInList.split("\\|")[0];
			keyAndValue[1] = cardInList.split("\\|")[1];
			map.put(keyAndValue[0], keyAndValue[1]);
		}
		long Tfinal = System.currentTimeMillis();
		long tTotal = Tfinal - Tinicio;
		System.out.println("Tiempo de ejecucion: " + tTotal+" ms");
		/**
		 * Teniendo todas las cartas guardadas en el Map, se muestra al usuario el menu
		 * de opciones que puede realizar
		 */
		int userOption = 0;
		boolean bandera = true;
		while (bandera) {
			System.out.println("-----------------------------------MENU---------------------------------------------------");
			System.out.println("1. Agregar una carta a la coleccion. (Nombre de la carta).");
			System.out.println("2. Mostrar el tipo de una carta especifica.(Nombre de la carta).");
			System.out.println("3. Mostrar el nombre, tipo y cantidad de cada carta en su coleccion.");
			System.out.println("4. Mostrar el nombre, tipo y cantidad de cada carta en su coleccion, ordenadas por tipo.");
			System.out.println("5. Mostrar el nombre y tipo de todas las cartas existentes.");
			System.out.println("6. Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo.");
			System.out.println("7. Salir");
			String userOptionSt = scan.nextLine();
			try {
				userOption = Integer.parseInt(userOptionSt);

				/**
				 * Se realizan las opciones segun la eleccion del usuario.
				 */
				long inicio;
				long finalT; 
				long Ttotal;
				String key = "";
				String value = "";
				java.util.Map<String, String> sorted = new LinkedHashMap<String, String>();
				try {
					switch (userOption) {
						case 1: // Agregar una carta
							System.out.println("Ingrese el nombre de la carta:");
							key = scan.nextLine();
							inicio = System.currentTimeMillis();
							if (map.containsKey(key)) {
								value = map.get(key);
								userMap.put(key, value);
								System.out.println("Se ha ingresado la carta: " + key);
								 finalT = System.currentTimeMillis();
								 Ttotal = finalT - inicio;
								System.out.println("Se ha tomado: " + Ttotal + " milisegundos");
							} else {
								System.out.println("No encuentro esta carta, es inventada?? >:(");
							}
							break;
						case 2: // Mostrar el tipo de una carta especifica
							System.out.println("Ingrese el nombre de la carta:");
							key = scan.nextLine();
							inicio = System.currentTimeMillis();
							if (map.containsKey(key)) {
								value = map.get(key);
								System.out.println("Esta carta es de tipo " + value);
								finalT = System.currentTimeMillis();
								Ttotal = finalT - inicio;
								System.out.println("Se ha tomado: " + Ttotal + " milisegundos");
							} else {
								System.out.println("No encuentro esta carta, es inventada?? >:(");
							}
							break;
						case 3: // Mostrar el nombre, tipo y cantidad de cada carta en su coleccion
							countMon = 0;
							countTram = 0;
							countHech = 0;
							inicio = System.currentTimeMillis();
							userMap.forEach((k, v) -> {
								if (v.equals("Monstruo")) {
									countMon++;
								} else if (v.equals("Trampa")) {
									countTram++;

								} else if (v.equals("Hechizo")) {
									countHech++;
								}
								System.out.println(k + " - " + v);
							});
							Tfinal = System.currentTimeMillis();
							
							System.out.println("Cartas de tipo Monstruo: " + countMon);
							System.out.println("Cartas de tipo Trampa: " + countTram);
							System.out.println("Cartas de tipo Hechizo: " + countHech);
							System.out.println("Se ha tomado: " + (Tfinal - inicio) + " milisegundos");
							break;
						case 4: // Mostrar el nombre, tipo y cantidad de cada carta en su coleccion, ordenadas por tipo
							countMon = 0;
							countTram = 0;
							countHech = 0;
							inicio = System.currentTimeMillis();
							sorted = userMap.entrySet().stream().sorted(java.util.Map.Entry.comparingByValue()).collect(
									toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
							sorted.forEach((k, v) -> {
								if (v.equals("Monstruo")) {
									countMon++;
								} else if (v.equals("Trampa")) {
									countTram++;

								} else if (v.equals("Hechizo")) {
									countHech++;
								}
								System.out.println(k + " - " + v);
							});
							Tfinal = System.currentTimeMillis();
							System.out.println("Cartas de tipo Monstruo: " + countMon);
							System.out.println("Cartas de tipo Trampa: " + countTram);
							System.out.println("Cartas de tipo Hechizo: " + countHech);
							System.out.println("Se ha tomado: " + (Tfinal - inicio) + " milisegundos");
							break;
						case 5: // Mostrar el nombre y tipo de todas las cartas existentes
							inicio = System.currentTimeMillis();
							map.forEach((k, v) -> {
								System.out.println(k + " - " + v);
							});
							Tfinal = System.currentTimeMillis();
							System.out.println("Se ha tomado: " + (Tfinal - inicio) + " milisegundos");
							break;
						case 6:
							inicio = System.currentTimeMillis();
							sorted = map.entrySet().stream().sorted(java.util.Map.Entry.comparingByValue()).collect(
									toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

							sorted.forEach((k, v) -> {
								System.out.println(k + " - " + v);
							});
							Tfinal = System.currentTimeMillis();
							System.out.println("Se ha tomado: " + (Tfinal - inicio) + " milisegundos");
							break;
						case 7: // Salir
							System.out.println("Hasta luego!");
							return;
						default:
							System.out.println("Esa opcion va mas haya de los limites de compresion de rango dado	.....");
							break;
					}
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("Si bueno, quien tiene hambre...."); //por si surge un error
				}
			} catch (Exception e) {
				System.out.println("Esa opcion no estaba en los planes.....");
			}
		}
		scan.close();
	}
}
