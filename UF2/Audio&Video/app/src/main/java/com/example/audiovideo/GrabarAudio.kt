package com.example.audiovideo

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException

class GrabarAudio : AppCompatActivity() {

    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: String? = null
    private var isRecording = false

    companion object {
        private const val REQUEST_PERMISSION_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grabar_audio)

        val startRecordButton = findViewById<Button>(R.id.start_record_button)
        val stopRecordButton = findViewById<Button>(R.id.stop_record_button)
        val customButton = findViewById<Button>(R.id.custom_button)

        stopRecordButton.visibility = Button.GONE

        startRecordButton.setOnClickListener {
            if (checkPermissions()) {
                if (!isRecording) {
                    startRecording()
                    startRecordButton.isEnabled = false
                    stopRecordButton.visibility = Button.VISIBLE
                    isRecording = true
                }
            } else {
                requestPermissions()
            }
        }

        stopRecordButton.setOnClickListener {
            stopRecording()
            startRecordButton.isEnabled = true
            stopRecordButton.visibility = Button.GONE
            isRecording = false
        }

        customButton.setOnClickListener {
            // Custom functionality
        }
    }

    private fun startRecording() {
        outputFile = Environment.getExternalStorageDirectory().absolutePath + "/audio_record.3gp"
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputFile)
            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            start()
        }
        Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show()
    }

    private fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show()
    }

    private fun checkPermissions(): Boolean {
        val writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val recordPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        return writePermission == PackageManager.PERMISSION_GRANTED && recordPermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO),
            REQUEST_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}