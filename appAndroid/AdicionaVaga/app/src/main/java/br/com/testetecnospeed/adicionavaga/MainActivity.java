package br.com.testetecnospeed.adicionavaga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button cadastrarVagaButton;
    private Button listarVagaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        iniciaBotoes();
    }

    private void initComponent() {
        cadastrarVagaButton = (Button) findViewById(R.id.cadastrar_vaga);
        listarVagaButton = (Button) findViewById(R.id.listar_vaga);
    }

    private void iniciaBotoes() {
        cadastrarVagaButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistraVaga.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
