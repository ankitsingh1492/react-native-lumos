package com.reactnativelumos

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class LumosModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private  var cameraManager: CameraManager
    private lateinit var cameraId: String

    init {
       val isFlashAvailable = reactContext.packageManager
      .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
      if (!isFlashAvailable) {
         //showNoFlashError()
      }
      cameraManager = reactContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager
      try {
         cameraId = cameraManager.cameraIdList[0]
      } catch (e: CameraAccessException) {
         e.printStackTrace()
      }
    }

    override fun getName(): String {
        return "Lumos"
    }

    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {
      promise.resolve(a * b)
    }

    @ReactMethod
    fun lumos() {
      switchFlashLight(true)
    }

    @ReactMethod
    fun nox() {
      switchFlashLight(false)
    }

    private fun switchFlashLight(status: Boolean) {
      try {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraId, status)
         }
      } catch (e: CameraAccessException) {
         e.printStackTrace()
      }
   }


}
