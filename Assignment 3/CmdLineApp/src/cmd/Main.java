package cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    // http://localhost:8100/play-REST-server/webapi/project3webservices/gettext
    // http://localhost:8100/play-REST-server/webapi/project3webservices/getmaterials
    // http://localhost:8100/play-REST-server/webapi/project3webservices/getstudents?id=1

    //Testes a usar o curl
    //curl http://localhost:8100/play-REST-server/webapi/project3webservices/gettext

    private static final String USER_AGENT = "Mozilla/5.0";
    private final static String GET_URL = "http://localhost:8100/play-REST-server/webapi/project3webservices/";
    private static final String POST_URL = "http://localhost:8100/play-REST-server/webapi/project3webservices/";

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner( System.in );
        System.out.println("Menu");
        System.out.println("1. Inserir Item");
        System.out.println("2. Inserir Pais");
        System.out.println("3. Consultar paises");
        System.out.println("4. Consultar items");
        System.out.println("5. Consultar estatísticas dos items");


        while (true) {
            System.out.print("\nOperacao pretendida: ");
            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    Scanner scan = new Scanner(System.in);
                    String nome="";

                    nome+=scan.nextLine();
                    GET("insertitem?itemname=" + nome);
                    break;
                case 2:
                    System.out.print("Nome: ");
                    Scanner scania = new Scanner(System.in);
                    String name="";

                    name+=scania.nextLine();
                    GET("insertcountry?countryname=" + name);
                    break;
                case 3:
                    GET("getcountries");
                    break;
                case 4:
                    GET("getitems");
                    break;
                case 5:
                    System.out.println("\nEstatisticas dos Items");
                    item_stats();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao nao existente, tente de novo");
                    break;
            }
        }
    }

    private static void GET (String destination) throws IOException {
        URL destino = new URL(GET_URL + destination);
        HttpURLConnection connection = (HttpURLConnection) destino.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int codigo_resposta = connection.getResponseCode();
        System.out.println("\nCódigo: " + codigo_resposta);

        if (codigo_resposta == HttpURLConnection.HTTP_OK) { // success
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = input.readLine()) != null) {
                response.append(inputLine);
            }
            input.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET nao funfou");
        }

        connection.disconnect();
    }

    private static void POST() throws IOException {
        URL destino = new URL(POST_URL);
        HttpURLConnection connection = (HttpURLConnection) destino.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        connection.setDoOutput(true);
        //OutputStream os = con.getOutputStream();
        //os.write(POST_PARAMS.getBytes());
        //os.flush();
        //os.close();
        // For POST only - END

        int code = connection.getResponseCode();
        System.out.println("Code -> " + code);

        if (code == HttpURLConnection.HTTP_OK) { //success
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer resposta = new StringBuffer();

            while ((inputLine = input.readLine()) != null) {
                resposta.append(inputLine);
            }
            input.close();

            // print result
            System.out.println(resposta.toString());
        } else {
            System.out.println("POST nao funfou");
        }

        connection.disconnect(); //Termina a ligacao
    }

    private static void item_stats() throws IOException {
        System.out.println("1. Get Item Stats");
        System.out.println("2. Get Total Stats");
        System.out.println("3. Get Average Item");
        System.out.println("4. Get Average Total");
        System.out.println("5. Get the item with the highest profit of all");
        System.out.println("6. Get Windowed Stats");
        System.out.println("7. Get the name of the country with the highest sales per item");

        Scanner input = new Scanner( System.in );
        System.out.print("\nOpcao pretendida: ");
        int opcao = input.nextInt();

        switch(opcao){
            case 1:
                GET("getitemstats");
                break;
            case 2:
                GET("gettotalstats");
                break;
            case 3:
                GET("getavgitem");
                break;
            case 4:
                GET("getavgtotal");
                break;
            case 5:
                GET("gethighestprof");
                break;
            case 6:
                GET("gethourtopic");
                break;
            case 7:
                GET("getcountrysales");
                break;
            default:
                System.out.println("Opcao nao existente, tente de novo");
                break;
        }

        System.out.println("\n\n");
        System.out.println("Menu");
        System.out.println("1. Inserir Item");
        System.out.println("2. Inserir Pais");
        System.out.println("3. Consultar paises");
        System.out.println("4. Consultar items");
        System.out.println("5. Consultar estatísticas dos items");

    }


}
