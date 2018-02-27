package br.com.testetecnospeed.adicionavaga;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.testetecnospeed.adicionavaga.sicronizacao.Constants;
import br.com.testetecnospeed.adicionavaga.sicronizacao.RequestHandler;

public class RegistraVaga extends AppCompatActivity {

    private EditText tituloEditText;
    private EditText setorEditText;
    private EditText cargoEditText;
    private EditText atividadeEditText;
    private EditText requisitoEditText;
    private EditText escolaridadeEditText;

    private Button salvarButton;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_vaga);

        initComponent();
        iniciaBotoes();

    }

    private void initComponent() {
        tituloEditText = (EditText) findViewById(R.id.editText_titulo);
        setorEditText = (EditText) findViewById(R.id.editText_setor);
        cargoEditText = (EditText) findViewById(R.id.editText_cargo);
        atividadeEditText = (EditText) findViewById(R.id.editText_atividade);
        requisitoEditText = (EditText) findViewById(R.id.editText_requisito);
        escolaridadeEditText = (EditText) findViewById(R.id.editText_escolaridade);

        salvarButton = (Button) findViewById(R.id.cadastrar_salvar);

        progressDialog = new ProgressDialog(this);
    }

    private void iniciaBotoes() {
        salvarButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(validacao()) {
                    inserir();
                }

            }
        });
    }

    private void inserir(){

        final String titulo = tituloEditText.getText().toString().trim();
        final String setor = setorEditText.getText().toString().trim();
        final String cargo = cargoEditText.getText().toString().trim();
        final String atividade = atividadeEditText.getText().toString().trim();
        final String requisito = requisitoEditText.getText().toString().trim();
        final String escolaridade = escolaridadeEditText.getText().toString().trim();

        progressDialog.setMessage("Registrando uma nova vaga de emprego...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER_VAGA,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            System.out.println(obj.toString());
                            if(!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), " Vaga registrada com sucesso!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistraVaga.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("titulo", titulo);
                params.put("setor", setor);
                params.put("cargo", cargo);
                params.put("atividade", atividade);
                params.put("requisito", requisito);
                params.put("escolaridade", escolaridade);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean validacao(){

        if (tituloEditText.getText().length() == 0) {
            tituloEditText.setError("Preencha o titulo!");
            tituloEditText.requestFocus();

            return false;

        }else if(atividadeEditText.getText().length() == 0) {

            atividadeEditText.setError("Preencha a atividade!");
            atividadeEditText.requestFocus();
            return false;

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistraVaga.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
