import javax.swing.SwingUtilities;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Principal {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new InterfaceCarros();
        });

    }
}




