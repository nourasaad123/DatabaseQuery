package com.example.mydatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.mydatabase.ui.theme.MyDatabaseTheme
import java.io.File.separator
import java.util.Random

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val database = Room.databaseBuilder(
                        context = context, klass = DBHandler::class.java,
                        "DBHandler"
                    ).allowMainThreadQueries().build()
                    val userDao = database.userDao()
                    var data by remember { mutableStateOf("") }
                    var username by remember { mutableStateOf("") }
                    var age by remember { mutableStateOf(0) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(40.dp)
                            .verticalScroll(rememberScrollState())
                    ) {

                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            modifier = Modifier.padding(16.dp), label = { (Text("Username")) }
                        )
                        OutlinedTextField(
                            value = age.toString(),
                            onValueChange = { age = it.toIntOrNull() ?: 0 },
                            modifier = Modifier.padding(16.dp), label = { (Text("Age")) }
                        )

                        Button(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            onClick = {
                                val user = User(name = username, age = age)
                                userDao.addUser(user)
                               // Toast.makeText(context, "data added", Toast.LENGTH_LONG).show()
                            }) {
                            Text("Insert")
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            onClick = {
                                data = userDao.getAllUsers().joinToString(separator = "\n")

                            }) {
                            Text("Retrieve")
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            onClick = {
                                val user = User(name = username, age = age)

                                userDao.deleteUser(user)
                                //deleteUserManually("noura")
                            }) {
                            Text("Delete")
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            onClick = {
                                val list = userDao.getAllUsers()
                                userDao.updateUser(list[0].copy(2))

                            }) {
                            Text("Update")
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(text = data)
                    }
                }
            }
        }


    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDatabaseTheme {

    }
}