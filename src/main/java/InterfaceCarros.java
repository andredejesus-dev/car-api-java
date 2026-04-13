import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InterfaceCarros extends JFrame {

    private JComboBox<String> comboMarca;
    private JComboBox<String> comboModelo;
    private JComboBox<String> comboAno;
    private JTextArea resultado;

    private HttpClient client = HttpClient.newHttpClient();

    public InterfaceCarros() {
        setTitle("Consulta FIPE");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        comboMarca = new JComboBox<>();
        comboModelo = new JComboBox<>();
        comboAno = new JComboBox<>();
        resultado = new JTextArea();

        resultado.setFont(new Font("Arial", Font.BOLD, 14));
        resultado.setLineWrap(true);
        resultado.setWrapStyleWord(true);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Marca:"), gbc);

        gbc.gridx = 1;
        painel.add(comboMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painel.add(new JLabel("Modelo:"), gbc);

        gbc.gridx = 1;
        painel.add(comboModelo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        painel.add(new JLabel("Ano:"), gbc);

        gbc.gridx = 1;
        painel.add(comboAno, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JButton btnBuscar = new JButton("Buscar Preço");
        painel.add(btnBuscar, gbc);

        add(painel, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);

        carregarMarcas();
        comboMarca.setSelectedIndex(-1);

        comboMarca.addActionListener(e -> carregarModelos());
        comboModelo.addActionListener(e -> carregarAnos());
        btnBuscar.addActionListener(e -> buscarPreco());

        setVisible(true);
    }

    private String get(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) return null;

            return response.body();

        } catch (Exception e) {
            return null;
        }
    }

    private void carregarMarcas() {
        try {
            String json = get("https://parallelum.com.br/fipe/api/v1/carros/marcas");
            if (json == null) return;

            JSONArray array = new JSONArray(json);

            comboMarca.removeAllItems();

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String codigo = obj.optString("codigo");
                String nome = obj.optString("nome");

                if (!codigo.isEmpty() && !nome.isEmpty()) {
                    comboMarca.addItem(codigo + " - " + nome);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarModelos() {
        try {
            if (comboMarca.getSelectedItem() == null) return;

            String selecionado = comboMarca.getSelectedItem().toString();
            if (!selecionado.contains(" - ")) return;

            String codigoMarca = selecionado.split(" - ")[0];

            String json = get("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + codigoMarca + "/modelos");
            if (json == null) return;

            JSONObject obj = new JSONObject(json);
            JSONArray modelos = obj.getJSONArray("modelos");

            comboModelo.removeAllItems();
            comboAno.removeAllItems();

            for (int i = 0; i < modelos.length(); i++) {
                JSONObject m = modelos.getJSONObject(i);

                String codigo = m.optString("codigo");
                String nome = m.optString("nome");

                if (!codigo.isEmpty() && !nome.isEmpty()) {
                    comboModelo.addItem(codigo + " - " + nome);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarAnos() {
        try {
            if (comboMarca.getSelectedItem() == null || comboModelo.getSelectedItem() == null) return;

            String codigoMarca = comboMarca.getSelectedItem().toString().split(" - ")[0];
            String codigoModelo = comboModelo.getSelectedItem().toString().split(" - ")[0];

            String json = get("https://parallelum.com.br/fipe/api/v1/carros/marcas/"
                    + codigoMarca + "/modelos/" + codigoModelo + "/anos");

            if (json == null) return;

            JSONArray array = new JSONArray(json);

            comboAno.removeAllItems();

            for (int i = 0; i < array.length(); i++) {
                JSONObject a = array.getJSONObject(i);

                String codigo = a.optString("codigo");
                String nome = a.optString("nome");

                if (!codigo.isEmpty() && !nome.isEmpty()) {
                    comboAno.addItem(codigo + " - " + nome);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarPreco() {
        try {
            if (comboMarca.getSelectedItem() == null ||
                    comboModelo.getSelectedItem() == null ||
                    comboAno.getSelectedItem() == null) return;

            String codigoMarca = comboMarca.getSelectedItem().toString().split(" - ")[0];
            String codigoModelo = comboModelo.getSelectedItem().toString().split(" - ")[0];
            String codigoAno = comboAno.getSelectedItem().toString().split(" - ")[0];

            String url = "https://parallelum.com.br/fipe/api/v1/carros/marcas/"
                    + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno;

            String json = get(url);
            if (json == null) return;

            JSONObject obj = new JSONObject(json);

            resultado.setText(
                    "Marca: " + obj.optString("Marca") +
                            "\nModelo: " + obj.optString("Modelo") +
                            "\nAno: " + obj.optString("AnoModelo") +
                            "\nPreço: " + obj.optString("Valor")
            );

        } catch (Exception e) {
            resultado.setText("Erro ao buscar dados.");
            e.printStackTrace();
        }
    }
}