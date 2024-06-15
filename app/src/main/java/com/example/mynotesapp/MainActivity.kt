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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotesapp.data.Note
import com.example.mynotesapp.presentation.NoteViewModel
import com.example.mynotesapp.ui.theme.MyNotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesAppTheme {

                var title by remember {
                    mutableStateOf("")
                }
                var content by remember {
                    mutableStateOf("")
                }
                var favColor by remember {
                    mutableStateOf("")
                }

                val note = Note(
                    title = title,
                    content = content,
                    favColor = favColor
                )

                var noteList by remember {
                    mutableStateOf(listOf<Note>())
                }

                viewModel.getAllNotes().observe(this) {
                    noteList = it
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(value = title, onValueChange = { title = it })
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(value = content, onValueChange = { content = it })
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(value = favColor, onValueChange = { favColor = it })
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.getUpsert(note) }) {
                            Spacer(modifier = Modifier.height(26.dp))
                            Text(text = "Click")
                        }

                        LazyColumn {
                            items(noteList) { index ->
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { viewModel.getDelete(index) })
                                {
                                    Text(text = index.title)
                                    Text(text = index.content)
                                }
                            }
                        }


                    }


                }
            }
        }
    }
}
