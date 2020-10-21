package com.silver.baskeballapp

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.silver.baskeballapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val SUBTRACT = "SUBTRACT"
        const val ADD = "ADD"
        const val LOCAL = "local"
        const val VISITOR = "visitante"
    }

    private lateinit var textScoreLocal: TextView
    private lateinit var textScoreVisitor: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textScoreLocal = binding.txtScoreLocal
        textScoreVisitor = binding.txtScoreVisitor

        // LOCAL

        binding.btnAddOneLocal.setOnClickListener {
            updateScore(1, LOCAL, ADD)
        }

        binding.btnAddTwoLocal.setOnClickListener {
            updateScore(2, LOCAL, ADD)
        }

        binding.btnSubtractOneLocal.setOnClickListener {
            updateScore(1, LOCAL, SUBTRACT)
        }

        // VISITOR

        binding.btnAddOneVisitor.setOnClickListener {
            updateScore(1, VISITOR, ADD)
        }

        binding.btnAddTwoVisitor.setOnClickListener {
            updateScore(2, VISITOR, ADD)
        }

        binding.btnSubtractOneVisitor.setOnClickListener {
            updateScore(1, LOCAL, SUBTRACT)
        }

        binding.btnReset.setOnClickListener {
            textScoreLocal.text = "0"
            textScoreVisitor.text = "0"
        }

        binding.btnResult.setOnClickListener {
            openResultMatch()
        }
    }

    private fun openResultMatch() {
        val pointLocal = textScoreLocal.text.toString().toInt()
        val pointVisitor = textScoreVisitor.text.toString().toInt()
        var result = ""
        var image = R.drawable.thinking
        if (pointLocal == pointVisitor) {
            result = "Fue un empate"
        } else if (pointLocal < pointVisitor) {
            result = "Gano el equipo visitante"
            image = R.drawable.sad
        } else {
            result = "Gano el equipo local"
            image = R.drawable.happy
        }
        val marker = "${pointLocal} - ${pointVisitor}"
        val match = Match(
            pointLocal = pointLocal,
            pointVisitor = pointVisitor,
            result = result,
            marker = marker,
            image = image
        )
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(ResultActivity.MATCH_KEY, match)
        startActivity(intent)
    }

    private fun updateScore(point: Int, where: String, type: String) {
        var txtScore = textScoreVisitor.text.toString()
        if (where == LOCAL) txtScore = textScoreLocal.text.toString()
        var parseScore = txtScore.toInt()
        when (type) {
            "ADD" -> {
                parseScore += point
                if (where == LOCAL) {
                    textScoreLocal.text = parseScore.toString()
                } else {
                    textScoreVisitor.text = parseScore.toString()
                }
            }
            "SUBTRACT" -> {
                if (parseScore > 0) {
                    parseScore -= point
                    if (where == LOCAL) {
                        textScoreLocal.text = parseScore.toString()
                    } else {
                        textScoreVisitor.text = parseScore.toString()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "No puede haber una puntuación ${where} negativo.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}