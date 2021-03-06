package com.spring.tp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellOption;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@ShellComponent
public class ShellApp {

    private static boolean connected = false;
    private String object;
    @Value("${shell.password}")
    private String appPwd;

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
        ouvrage.setRayon(RayonByTheme(theme));

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
                if(r.getTheme() != null) {
                    System.out.println("Th??me: " + r.getTheme());
                }
            }

            if(rayons.length == 0){
                System.out.println("No data from the table");
            }

            value = "Fin";
        } catch (Exception e) {
            value = "Failed to get the data";
            e.printStackTrace();
        }

        return value;
    }

    public Rayon RayonByTheme(String theme) throws IOException {

        String value = "";
        Rayon rayon = new Rayon();

        URL url = new URL("http://localhost:80/api/rayon/findbytheme?theme=" + theme);
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

            rayon = rayons[0];
        } catch (Exception e) {
            System.out.println("Failed to get the data");
            e.printStackTrace();
        }

        return rayon;
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
                    System.out.println("Th??me: " + o.getRayon().getTheme());
                }
            }

            if(ouvrages.length == 0){
                System.out.println("No data from the table");
            }

            value = "Fin";
        } catch (Exception e) {
            value = "Failed to get the data";
            e.printStackTrace();
        }

        return value;
    }

    @ShellMethod("Delete from one table.")
    public String delete(int id) throws IOException {
        if(!connected){
            return "Please connect before any action on the tables";
        }
        if(!(Objects.equals(object, "ouvrage") || Objects.equals(object, "rayon"))){
            return "No table selected: enter 'ouvrage' or 'rayon'";
        }

        String value = "";

        URL url = new URL("http://localhost:80/api/"+object+"/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);

            value = "Fin";
        } catch (Exception e) {
            value = "Failed to get the data";
            e.printStackTrace();
        }

        return value;
    }
}