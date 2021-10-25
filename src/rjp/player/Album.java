package rjp.player;

import java.util.ArrayList;
import java.util.List;

class Album implements Comparable<Album> {
    private final String albumName;
    private final String artistName;
    private final SongList songs;

    Album(String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.songs = new SongList();
    }

    public int compareTo(Album o) { // For alphabetizing albums in the Player Class
        int artist = getArtistName().compareTo(o.getArtistName());
        if (artist == 0) {
            return getAlbumName().compareTo(o.getAlbumName());
        }
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String toString() { //Default toString Method.
        return "Album{" +
                "albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", songs=" + songs.toString() +
                '}';
    }

    /* String methods are copies of the Song Object. The Object itself will never be passed to the User Interface
       And only Song and Album Objects are available in the Player Class after a Player Object is instantiated.
       The String methods will be used display the info to the user and for searching purposes.
     */

    public List<String[]> getSongListInfo() {
        List<String[]> songInfo = new ArrayList<>();
        for (Song song: songs.getAlbumSongList()) {
            songInfo.add(song.getSong());
        }
        return songInfo;
    }

    public List<String> getSongListSongNames() {
        List<String> songNames = new ArrayList<>();
        for (Song song: songs.getAlbumSongList()) {
            songNames.add(song.getTitle());
        }
        return songNames;
    }

    public List<String> getSongDurations() { //In case the player needs a list of song lengths.
        List<String> songDurations = new ArrayList<>();
        for (Song song: songs.getAlbumSongList()) {
            songDurations.add(Double.toString(song.getDuration()));
        }
        return songDurations;
    }

    List<Song> getSongs() { // return copy of the list to the Player case to encapsulate.
        return new ArrayList<>(songs.getAlbumSongList());
    }

    // Five methods for adding songs to Album or Playlist- package-private (protected) for encapsulation
    boolean addSong(String songName, double duration) {
        Song song = new Song(songName, duration, this.artistName);
        return addSong(song);
    }

    boolean addSong(String songName, double duration, String artistName) { //In case it a mixed/compilation album
        Song song = new Song(songName, duration, artistName);
        return addSong(song);
    }

    boolean addSong(Song newSong) {
        return this.songs.add(newSong);
    }

    boolean verifySongForPlayList(int track, List<Song> playList) {
        Song songToAdd = songs.findSong(track);
        if (songToAdd != null)
            return playList.add(songToAdd);
        return false;
    }

    boolean verifySongForPlayList(String songName, List<Song> playList) {
        Song songToAdd = songs.findSong(songName);
        if (songToAdd != null)
            return playList.add(songToAdd);
        return false;
    }



    // class of song list which is private to this particular album
    private static class SongList {
        private final ArrayList<Song> albumSongList = new ArrayList<>();

        private ArrayList<Song> getAlbumSongList() {
            return albumSongList;
        }

        private Song findSong(String name) {
            for (Song song: albumSongList) {
                if (song.getTitle().toLowerCase().equals(name.toLowerCase())) // Could use equalsIgnoreCase here
                    return song;
            }
            return null;
        }

        private Song findSong(int track) {
            if (track <= albumSongList.size() && track > 0) {
                return albumSongList.get(track-1);
            }
            return null;
        }

        private boolean add(Song song) {
            // Protection against double adding by song title: this also protects against double adding by Song Object.
            if (findSong(song.getTitle()) != null)
                return false;
//            if (this.albumSongList.contains(song))
//                return false;
            return this.albumSongList.add(song);
        }

//        public String toString() {
//            return "SongList{" +
//                    "albumSongList=" + albumSongList +
//                    '}';
//        }
    }


}
