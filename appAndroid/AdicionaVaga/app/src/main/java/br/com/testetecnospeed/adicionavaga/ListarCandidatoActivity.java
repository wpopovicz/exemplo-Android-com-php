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

import br.com.testetecnospeed.adicionavaga.entidade.Candidato;
import br.com.testetecnospeed.adicionavaga.entidade.Vaga;
import br.com.testetecnospeed.adicionavaga.sicronizacao.Constants;
import br.com.testetecnospeed.adicionavaga.sicronizacao.RequestHandler;

public class ListarCandidatoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private EditText edtPesquisa;
    private ListView listaCandidatos;
    private ArrayAdapter<Candidato> adpCandidatos;
    private Vaga vaga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_candidato);
        initComponents();

        vaga = new Vaga();

        Bundle bundle = getIntent().getExtras();
        if((bundle != null) && (bundle.containsKey("VAGA"))){
            vaga = (Vaga)bundle.getSerializable("VAGA");
        }

        adicionaAdpter(vaga.getId());

        listaCandidatos.setOnItemClickListener(this);

        ListarCandidatoActivity.FiltraDados filtraDados = new ListarCandidatoActivity.FiltraDados(adpCandidatos);
        edtPesquisa.addTextChangedListener(filtraDados);
    }

    private void adicionaAdpter(int idVaga){
        final String idVagaString = Integer.toString(idVaga);
        final List<Candidato> listaCands = new ArrayList<Candidato>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LISTAR_CANDIDATO,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray vagas = obj.getJSONArray("candidato");
                            for(int i = 0;i < vagas.length(); i++){
                                Candidato candi = new Candidato();
                                JSONObject can = vagas.getJSONObject(i);
                                if(can.length() > 0  && can.getBoolean("error")) {
                                    candi.setId(Integer.parseInt(can.getString("id")));
                                    candi.setIdVaga(Integer.parseInt(can.getString("id_vaga")));
                                    candi.setNome(can.getString("nome"));
                                    candi.setEmail(can.getString("email"));
                                    candi.setEndereco(can.getString("endereço"));
                                    candi.setExperienciaProfissional(can.getString("experiencia"));
                                    candi.setFormacaoAcademica(can.getString("formacao"));
                                    candi.setInformacaoAdicional(can.getString("informacao"));
                                    candi.setTelefone(can.getString("telefone"));
                                }
                                listaCands.add(candi);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(listaCands.size() > 0) {
                            adpCandidatos = new ArrayAdapter<Candidato>(ListarCandidatoActivity.this, android.R.layout.simple_list_item_1);
                            adpCandidatos.addAll(listaCands);
                            listaCandidatos.setAdapter(adpCandidatos);
                        }else{
                            Toast.makeText(ListarCandidatoActivity.this, "Não possui nenhum candidato para listar!", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                ListarCandidatoActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idVaga", idVagaString);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void initComponents(){
        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        listaCandidatos = (ListView) findViewById(R.id.lista_candidato);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Candidato candidato = adpCandidatos.getItem(position);
        Intent intent = new Intent(ListarCandidatoActivity.this, ListarVagaActivity.class);
        intent.putExtra("CANDIDATO", candidato);
        startActivityForResult(intent, 0);
    }

    private class FiltraDados implements TextWatcher {

        private ArrayAdapter<Candidato> arrayAdapter;

        private FiltraDados(ArrayAdapter<Candidato> arrayAdapter){

            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
