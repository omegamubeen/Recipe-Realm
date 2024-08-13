package com.manaknight.assetinventory.data.local

import android.app.backup.BackupAgentHelper
import android.app.backup.BackupDataInput
import android.app.backup.BackupDataOutput
import android.app.backup.SharedPreferencesBackupHelper
import android.content.Context
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.Toast

class MyPrefsBackupAgent(private val context: Context): BackupAgentHelper() {

    override fun onCreate() {
        val helper = SharedPreferencesBackupHelper(context, "prefs")
        addHelper("prefsBackup", helper)
        Log.d("shamal","onCreate")
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onBackup(
        oldState: ParcelFileDescriptor?,
        data: BackupDataOutput?,
        newState: ParcelFileDescriptor?
    ) {
        super.onBackup(oldState, data, newState)
        Toast.makeText(this, "onBackup", Toast.LENGTH_SHORT).show()
        Log.d("shamal","onBackup")
    }

    override fun onRestore(
        data: BackupDataInput?,
        appVersionCode: Int,
        newState: ParcelFileDescriptor?
    ) {
        super.onRestore(data, appVersionCode, newState)
        Toast.makeText(this, "onRestore", Toast.LENGTH_SHORT).show()
        Log.d("shamal","onRestore")
    }

    override fun onRestoreFinished() {
        super.onRestoreFinished()
        Toast.makeText(this, "onRestoreFinished", Toast.LENGTH_SHORT).show()
        Log.d("shamal","onRestoreFinished")
    }
}