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
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar); // Obtendo o botão
        btnEnviar.setOnClickListener(new View.OnClickListener() { // Capturando vento de Click
            @Override
            public void onClick(View view) {
                EditText etEmail = (EditText) findViewById(R.id.etEmail); // Obtendo o texto digitado do campo etEmail
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto); // Obtendo o texto digitado do campo etAssunto
                EditText etTexto = (EditText) findViewById(R.id.etTexto); // Obtendo o texto digitado do campo etTexto

                String email = etEmail.getText().toString(); // Armazenando esses texto em uma varaável
                String assunto = etAssunto.getText().toString(); // Armazenando esses texto em uma varaável
                String texto = etTexto.getText().toString(); // Armazenando esses texto em uma varaável

                Intent i = new Intent(Intent.ACTION_SENDTO); // Adicionando uma intent indicando a ação "ACTION_SENDTO" para procurar os apps no sistema

                i.setData(Uri.parse("mailto:")); // Definindo interesse apenas em apps com URI que tenham “mailto:”, ou seja, apps que trabalham com envio e recebimento de e-mail
                String[] emails = new String[]{email}; // Preenchendo o intent com os dados que vamos enviar para o app externo
                i.putExtra(Intent.EXTRA_EMAIL, emails); // "EXTRA-EMAIL" corresponde a uma lista de e-mail
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); // "EXTRA_SUBJECT" corresponde ao campo de assunto
                i.putExtra(Intent.EXTRA_TEXT, texto); // "EXTRA_TEXT" corresponde ao corpo do texto

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP")); // Intent chamada detro de try e o método "Intent.createChooser" que faz com que o app escolha um app
                }
                catch (ActivityNotFoundException e) { // Caso o app escolhido não seja achado ou não esteja instalado, uma mensagem de erro aparecerá
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}