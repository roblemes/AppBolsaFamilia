package com.example.appbolsafamilia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textResultado;
    EditText textEditCodigo;
    EditText textEditAno;
    List<BolsaFamilia> bolsas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bolsas = new ArrayList<>();
        setContentView(R.layout.activity_main);

        textResultado = findViewById(R.id.textResult);
        textEditCodigo = findViewById(R.id.textEditCodigo);
        textEditAno = findViewById(R.id.textEditAno);
    }

    public void solicitarDadoGoverno(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);

        String codigo = textEditCodigo.getText().toString();
        String ano = textEditAno.getText().toString();


        if (codigo.length() != 7 || ano.length() != 4) {
            textResultado.setText("Dados inválidos");
            return;
        } else {
            textResultado.setText("Executando..");
        }

        for (int i = 1; i <= 3; i++) {

           /* String mes = "";
            if (i < 10) {
                mes = "0" + i;
            }else{
                mes = "" + i;
            }*/
            String mes = "0";
            mes = "0" + i;

            String url = "http://www.transparencia.gov.br/api-de-dados/bolsa-familia-por-municipio?mesAno=" + ano + mes + "&codigoIbge=" + codigo + "&pagina=1";

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            try {

                                BolsaFamilia bolsaf = new BolsaFamilia();
                                JSONObject result = response.getJSONObject(0);
                                JSONObject municipio_obj = result.getJSONObject("municipio");

                                bolsaf.setNomeMunicipio(municipio_obj.getString("nomeIBGE"));
                                bolsaf.setEstado(municipio_obj.getJSONObject("uf").getString("sigla"));
                                //bolsaf.setEstadoNome(municipio_obj.getJSONObject("uf").getString("nome"));
                                bolsaf.setBeneficiarios(result.getInt("quantidadeBeneficiados"));
                                bolsaf.setTotalPago(result.getDouble("valor"));

                                bolsas.add(bolsaf);
                                textResultado.setText(bolsas.toString());

                            } catch (JSONException e) {
                                textResultado.setText(e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textResultado.setText(error.getMessage());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("chave-api-dados", "50fe0aef09277b29412ab25a804715a0");

                    return params;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(5000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);

        }

//////////////

    }
}