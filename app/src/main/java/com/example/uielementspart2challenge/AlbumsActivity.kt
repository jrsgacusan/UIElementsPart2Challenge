package com.example.uielementspart2challenge

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import com.example.uielementspart2challenge.models.Album

var albumSongs = ArrayList<String>()
var albumURI = String
lateinit var albumAdapter: ArrayAdapter<Album>
class AlbumsActivity : AppCompatActivity() {
    lateinit var title: String
    lateinit var listView : ListView
    lateinit var databaseHandler: SongsDatabaseHandler
    lateinit var albums: MutableList<Album>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        listView = findViewById(R.id.listView)
        //get the table handler
        databaseHandler = SongsDatabaseHandler(this)
        //get the records
        albums = databaseHandler.readAlbum()
        //attach it to the adapter
        albumAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albums)
        listView.adapter = albumAdapter

        registerForContextMenu(listView)

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, NewAlbumDetailsActivity::class.java)
            val album_id = albums[position].id
            intent.putExtra("album_id", album_id)
            startActivity(intent)
        }






//        //Map the Grid View
//        val GridView = findViewById<GridView>(R.id.gridView) as GridView
//        //Attach the adapter to the Grid View
//        GridView.adapter = ImageAdapter(applicationContext)
//        //Item click listener for the Grid View
//        GridView.onItemClickListener = AdapterView.OnItemClickListener{parent, v, position, id ->
//            val intent = Intent(this, AlbumDetailsActivity::class.java)
//            var uri: String
//            if (position == 0) {
//                uri = "@drawable/divide_cover"
//                title = "Divide"
//                albumSongs.clear()
//                albumSongs.addAll(resources.getStringArray(R.array.divide))
//            } else if (position == 1) {
//                uri = "@drawable/abbey_road_cover"
//                title = "Abbey Road"
//                albumSongs.clear()
//                albumSongs.addAll(resources.getStringArray(R.array.abbeyRoad))
//            } else {
//                uri = "@drawable/scorpion_cover"
//                title = "Scorpion"
//                albumSongs.clear()
//                albumSongs.addAll(resources.getStringArray(R.array.scorpion))
//            }
//            intent.putExtra("imageUri",  uri)
//            intent.putExtra ("albumTitle", title)
//            startActivity(intent)
//
//
//        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.album_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addAlbum -> {
                val intent = Intent(this, AddAlbumActivity::class.java)
                startActivity(intent)
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit_album_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.editAlbum -> {
                val intent = Intent(this, EditAlbumActivity::class.java)
                val album_id = albums[info.position].id
                intent.putExtra("album_id", album_id)
                startActivity(intent)
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }




}



