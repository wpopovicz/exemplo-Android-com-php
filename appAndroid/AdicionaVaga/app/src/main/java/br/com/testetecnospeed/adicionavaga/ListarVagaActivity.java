package br.com.testetecnospeed.adicionavaga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.testetecnospeed.adicionavaga.entidade.Vaga;
import br.com.testetecnospeed.adicionavaga.sicronizacao.Constants;
import br.com.testetecnospeed.adicionavaga.sicronizacao.RequestHandler;

public class ListarVagaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private EditText edtPesquisa;
    private ListView listavagas;
    private ArrayAdapter<Vaga> adpVagas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_vaga);

        initComponents();
        adicionaAdpter();

        listavagas.setOnItemClickListener(this);

        FiltraDados filtraDados = new FiltraDados(adpVagas);
        edtPesquisa.addTextChangedListener(filtraDados);
    }

    private void adicionaAdpter(){

        final List<Vaga> listaVags = new ArrayList<Vaga>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LISTAR_VAGA,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray vagas = obj.getJSONArray("vaga");
                            for(int i = 0;i < vagas.length(); i++){
                                JSONObject va = vagas.getJSONObject(i);
                                if(va.length() > 0  && !va.getBoolean("error")) {
                                    Vaga vaga = new Vaga();
                                    vaga.setId(Integer.parseInt(va.getString("id")));
                                    vaga.setTitulo(va.getString("titulo"));
                                    vaga.setSetor(va.getString("setor"));
                                    vaga.setCargo(va.getString("cargo"));
                                    vaga.setAtividade(va.getString("atividade"));
                                    vaga.setRequisito(va.getString("requisito"));
                                    vaga.setEscolaridade(va.getString("escolaridade"));
                                    listaVags.add(vaga);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(listaVags.size() > 0) {
                            adpVagas = new ArrayAdapter<Vaga>(ListarVagaActivity.this, android.R.layout.simple_list_item_1);
                            adpVagas.addAll(listaVags);
                            listavagas.setAdapter(adpVagas);
                        }else{
                            Toast.makeText(ListarVagaActivity.this, "Não possui nenhuma vaga para listar!", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                ListarVagaActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void initComponents(){
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        listavagas = (ListView) findViewById(R.id.lista_vaga);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        adicionaAdpter();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Vaga vaga = adpVagas.getItem(position);
        Intent intent = new Intent(ListarVagaActivity.this, ListarCandidatoActivity.class);
        intent.putExtra("VAGA", vaga);
        startActivityForResult(intent, 0);
    }

    private class FiltraDados implements TextWatcher {

        private ArrayAdapter<Vaga> arrayAdapter;

        private FiltraDados(ArrayAdapter<Vaga> arrayAdapter){

            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter = adpVagas;
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListarVagaActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
