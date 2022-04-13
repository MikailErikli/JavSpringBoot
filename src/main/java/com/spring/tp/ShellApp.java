package com.spring.tp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepository;
import com.spring.tp.repository.RayonRepositoryInterface;
import com.spring.tp.service.OuvrageServiceInterface;
import com.spring.tp.service.RayonService;
import com.spring.tp.service.RayonServiceInterface;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellOption;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@ShellComponent
public class ShellApp {

    private static boolean connected = false;
    private String object;
    @Value("${shell.password}")
    private String appPwd;

    @Autowired
    RayonServiceInterface rayonService;

    @Autowired
    OuvrageServiceInterface ouvrageService;

    @ShellMethod("Connect to the application.")
    public String connect(@ShellOption String password) {
        String value = "";
        if(Objects.equals(password, appPwd)){
            connected = true;
            value = "Connected! Now tap 'rayon' or 'ouvrage' on your keyboard to chose what to add/delete/create/edit";
        }else{
            value = "Connection error: Wrong password";
        }

        return value;
    }

    @ShellMethod("Select 'rayon' table.")
    public String rayon(){
        if(!connected){
            return "Pleease connect before any action on the tables";
        }
        object = "rayon";
        return "You are editing 'rayon' table";
    }

    @ShellMethod("Select 'ouvrage' table.")
    public String ouvrage(){
        if(!connected){
            return "Pleease connect before any action on the tables";
        }
        object = "ouvrage";
        return "You are editing 'ouvrage' table";
    }

    @ShellMethod("Add to table 'ouvrage'.")
    public String addouvrage(
            @ShellOption String title,
            @ShellOption String author,
            @ShellOption String isbn,
            @ShellOption Float price,
            @ShellOption Integer stock,
            @ShellOption String theme) throws IOException {

        if(!connected){
            return "Please connect before any action on the tables";
        }
        if(!Objects.equals(object, "ouvrage")){
            return "'ouvrage' non selected: Use command 'ouvrage' to edit its table";
        }

        String value = "";

        Ouvrage ouvrage = new Ouvrage();
        ouvrage.setTitle(title);
        ouvrage.setAuthor(author);
        ouvrage.setIsbn(isbn);
        ouvrage.setPrice(price);
        ouvrage.setStock(stock);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(ouvrage);

        URL url = new URL("http://localhost:80/api/ouvrage");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }catch(Exception e){
            value = "Add failed";
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
//            System.out.println(response.toString());
            value = "Ouvrage added to the table";
        }

        return value;
    }

    @ShellMethod("Add to table 'rayon'.")
    public String addrayon(@ShellOption String theme) throws IOException {
        if(!connected){
            return "Please connect before any action on the tables";
        }
        if(!Objects.equals(object, "rayon")){
            return "'rayon' non selected: Use command 'rayon' to edit its table";
        }

        String value = "";

        Rayon rayon = new Rayon();
        rayon.setTheme(theme);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(rayon);

        URL url = new URL("http://localhost:80/api/rayon");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }catch(Exception e){
            value = "Add failed";
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            value = "Rayon added to the table";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    @ShellMethod("List table 'rayon'.")
    public String listrayons() throws IOException {
        if(!connected){
            return "Please connect before any action on the tables";
        }
        if(!Objects.equals(object, "rayon")){
            return "'rayon' non selected: Use command 'rayon' to edit its table";
        }

        String value = "";


        URL url = new URL("http://localhost:80/api/rayon");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            final ObjectMapper objectMapper = new ObjectMapper();
            Rayon[] rayons = objectMapper.readValue(response.toString(), Rayon[].class);

            for(Rayon r: rayons){
                System.out.println("Rayon: "+ r.getId());
                System.out.println("Thème: " + r.getTheme());
            }

            value = "Fin";
        } catch (Exception e) {
            value = "Failed to get the data";
            e.printStackTrace();
        }

        return value;
    }

    @ShellMethod("List table 'ouvrage'.")
    public String listouvrages() throws IOException {
        if(!connected){
            return "Please connect before any action on the tables";
        }
        if(!Objects.equals(object, "ouvrage")){
            return "'ouvrage' non selected: Use command 'ouvrage' to edit its table";
        }

        String value = "";

        URL url = new URL("http://localhost:80/api/ouvrage");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            final ObjectMapper objectMapper = new ObjectMapper();
            Ouvrage[] ouvrages = objectMapper.readValue(response.toString(), Ouvrage[].class);

            for(Ouvrage o: ouvrages){
                System.out.println("Ouvrage: "+ o.getId());
                if(o.getTitle() != null){
                    System.out.println("Titre: " + o.getTitle());
                }
                if(o.getAuthor() != null){
                    System.out.println("Auteur: " + o.getAuthor());
                }
                if(o.getIsbn() != null){
                    System.out.println("ISBN: " + o.getIsbn());
                }
                if(o.getPrice() != null){
                    System.out.println("Prix: " + o.getPrice());
                }
                if(o.getStock() != null){
                    System.out.println("Stock: " + o.getStock());
                }
                if(o.getRayon() != null){
                    System.out.println("Thème: " + o.getRayon().getTheme());
                }
            }

            value = "Fin";
        } catch (Exception e) {
            value = "Failed to get the data";
            e.printStackTrace();
        }

        return value;
    }


}