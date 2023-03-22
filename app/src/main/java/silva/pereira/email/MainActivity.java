package silva.pereira.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                EditText etTexto = (EditText) findViewById(R.id.etTexto);

                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO);

                i.setData(Uri.parse("mailto:"));
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}