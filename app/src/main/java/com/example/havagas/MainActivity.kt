package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var amb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(amb.root)

        amb.formacaoSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    in 0..1 -> {
                        amb.FormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.GONE
                        amb.tituloEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    in 2..3 -> {
                        amb.FormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.tituloEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    else -> {
                        amb.FormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.tituloEt.visibility = View.VISIBLE
                        amb.orientadorEt.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Não se aplica
            }
        }

        amb.celularRb.setOnClickListener {
            if (amb.celularEt.visibility == View.GONE) {
                amb.celularEt.visibility = View.VISIBLE
            } else {
                amb.celularEt.visibility = View.GONE
            }
        }

        amb.saveBt.setOnClickListener {
            val nome = amb.nomeInteiroEt.text.toString()
            val email = amb.emailEt.text.toString()
            val desejaReceberEmail = if (amb.emailRb.isChecked) "Sim" else "Não"
            val telefone = amb.telefoneEt.text.toString()
            val telefoneTipo = if (amb.comercialRb.isChecked) "Comercial" else "Residencial"
            val telefoneCelular = amb.celularEt.text.toString()
            val sexo = if (amb.masculinoRb.isChecked) "Masculino" else "Feminino"
            val data = amb.nascimentoTv.text.toString()
            val formacao = amb.formacaoSp.selectedItem.toString()
            val anoFormacao = amb.FormacaoEt.text.toString()
            val instituicao = amb.instituicaoEt.text.toString()
            val tituloMonografia = amb.tituloEt.text.toString()
            val orientador = amb.orientadorEt.text.toString()
            val vagasInteresse = amb.vagasEt.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty()
                && telefone.isNotEmpty() && telefoneTipo.isNotEmpty()
                && sexo.isNotEmpty() && data.isNotEmpty() && formacao.isNotEmpty()
                && anoFormacao.isNotEmpty() && vagasInteresse.isNotEmpty()) {

                val mensagem = when (formacao) {
                    "Graduação", "Especialização" -> {
                        if (instituicao.isNotEmpty()) {
                            " Name: $nome \n E-mail: $email \n" +
                                    "Phone: $telefone \n Phone type: $telefoneTipo \n  Smartphone: $telefoneCelular \n " +
                                    "Sex: $sexo \n Birth: $data \n Education: $formacao \n Year Education: $anoFormacao \n " +
                                    "Instituation: $instituicao \n Interesting: $vagasInteresse"
                        } else {
                            ""
                        }
                    }
                    "Mestrado", "Doutorado" -> {
                        if (tituloMonografia.isNotEmpty() && orientador.isNotEmpty()) {
                            " Name: $nome \n E-mail: $email \n " +
                                    "Phone: $telefone \n Phone type: $telefoneTipo \n  Smartphone: $telefoneCelular \n " +
                                    "Sex: $sexo \n Birth: $data \n Education: $formacao \n Year Education: $anoFormacao \n " +
                                    "Instituation: $instituicao \n Monography title: $tituloMonografia \n " +
                                    "orientador: $orientador \n Interesting: $vagasInteresse"
                        } else {
                            ""
                        }
                    } else -> {
                        " Name: $nome \n E-mail: $email \n" +
                                " Phone: $telefone \n Phone type: $telefoneTipo \n  Smartphone: $telefoneCelular \n " +
                                "Sex: $sexo \n Birth: $data \n Education: $formacao \n Year Education: $anoFormacao \n " +
                                "Interesting: $vagasInteresse"
                    }
            }
                if (mensagem.isNotEmpty()) {
                    Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
            }

        }

        amb.clearBt.setOnClickListener {
            amb.nomeInteiroEt.setText("")
            amb.emailEt.setText("")
            amb.telefoneEt.setText("")
            amb.celularEt.setText("")
            amb.celularEt.visibility = View.GONE
            amb.vagasEt.setText("")
            amb.emailRb.setChecked(false)
            amb.telefoneRg.check(amb.comercialRb.id)
            amb.sexoRg.check(amb.masculinoRb.id)
            amb.nascimentoTv.setText("")
            amb.formacaoSp.setSelection(0)
            amb.FormacaoEt.setText("")
            amb.instituicaoEt.setText("")
            amb.tituloEt.setText("")
            amb.orientadorEt.setText("")
            amb.FormacaoEt.visibility = View.GONE
            amb.instituicaoEt.visibility = View.GONE
            amb.tituloEt.visibility = View.GONE
            amb.orientadorEt.visibility = View.GONE
        }
    }
}