package com.jalfaro.lovelypets.ui.screens.input

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jalfaro.lovelypets.BuildConfig
import com.jalfaro.lovelypets.R
import com.jalfaro.lovelypets.models.Race
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects

@Composable
fun InputScreen(
    onNavigateUp: () -> Unit,
    viewModel: InputViewModel,
) {
    /*** Camera Actions ***/
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
            viewModel.setPicture(uri)
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    /*** Camera Actions **/
    /*** Race Dropdownlist  ***/
    val races by remember { viewModel.races }.collectAsState(initial = emptyList())
    var selectedRace by remember { mutableStateOf(Race(0,"")) }
    LaunchedEffect(Unit) {
        viewModel.loadRaces()
        selectedRace = viewModel.races.value[0]
    }
    var expanded by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    /*** Race Dropdownlist  ***/
    /*** PetName InputText ***/
    var petName by remember { mutableStateOf("") }
    /*** PetName InputText ***/

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.display_title),
                        fontSize = 25.sp,
                        style = MaterialTheme.typography.bodyMedium
                    ) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.nav_back_content_description),
                        )
                    }
                }
            )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingValue.calculateTopPadding(), start = 16.dp, end = 16.dp)
            ) {
                OutlinedTextField(
                    value = petName,
                    onValueChange = {
                        petName = it
                        viewModel.setPetName(it)
                    },
                    label = { Text(text = stringResource(R.string.pet_name)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box() {
                    OutlinedTextField(
                        value = selectedRace.name,
                        onValueChange = {
                            selectedRace = races.find { it.name.equals(it)} ?: Race(0,"")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                //This value is used to assign to the DropDown the same width
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = {Text(stringResource(R.string.race))},
                        trailingIcon = {
                            Icon(icon,"contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                    ) {

                        races.forEach { race ->
                        DropdownMenuItem(onClick = {
                            selectedRace = race
                            viewModel.setRace(race)
                            expanded = false
                        },
                            text = {
                                Text(text = race.name)
                            })
                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(uri)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    } ,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddAPhoto,
                        contentDescription = stringResource(id = R.string.camera_content_description),
                        modifier = Modifier
                            .padding(end = 20.dp)
                    )
                    Text(stringResource(id = R.string.take_picture))
                }
                if (capturedImageUri.path?.isNotEmpty() == true) {
                    Image(
                        modifier = Modifier
                            .padding(16.dp, 8.dp),
                        painter = rememberImagePainter(capturedImageUri),
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.add_pet)) },
                onClick = {
                   viewModel.savePet()
                    capturedImageUri = Uri.EMPTY
                    selectedRace = races[0]
                    petName = ""
                    Toast.makeText(context, "Pet saved successfully", Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_pet)
                    )
                }
            )
        }
    )
}

@Composable
@Preview
fun InputScreenPreview() {
    InputScreen(
        onNavigateUp = { },
        viewModel = hiltViewModel()
    )
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
    return image
}
