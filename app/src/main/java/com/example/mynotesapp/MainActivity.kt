package com.example.mynotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.ui.theme.MyNotesAppTheme
import com.example.mynotesapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var title by remember {
                        mutableStateOf("")
                    }
                    var desp by remember {
                        mutableStateOf("")
                    }
                    val note = Note(
                        title = title,
                        desp = desp
                    )

                    var noteList by remember {
                        mutableStateOf(listOf<Note>())
                    }

                    viewModel.getAllNotes().observe(this) {
                        noteList = it

                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(value = title, onValueChange = { title = it })
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = desp, onValueChange = { desp = it })
                        Spacer(modifier = Modifier.height(20.dp))

                        Button(onClick = { viewModel.getUpsertNote(note) }) {
                            Text(text = "Click", fontSize = 24.sp)
                        }

                        LazyColumn() {
                            items(noteList) { index ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.getDeleteNote(index) }
                                        .padding(16.dp),
                                    elevation = CardDefaults.cardElevation(20.dp)
                                ) {
                                    Text(text = index.title)
                                    Text(text = index.desp)
                                }
                            }
                        }


                    }

                }
            }
        }
    }
}
