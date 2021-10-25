package rjp.player;

import java.util.*;

// ******************************
// Player Class for behind the scenes of music player - in accordance with UIPlayer class
// ArrayList of Album Objects will allow for ordering the albums as they are added to keep track of ordering.
// TreeSet of PlayList Objects will not allow for duplicate objects, and order is not important.
// TreeMap is used to keep a record of the albums associated with each artist.
// ******************************

public class Player { // Use arraylist to keep track of albums, tree set for playlists, map for artist/album list.
    private String name;
    private final List<Album> myAlbums;
    private final Set<PlayList> myPlayLists;
    private final Map<String, List<String>> myArtists;

    public Player(String name) {
        this.name = name;
        this.myAlbums = new ArrayList<>();
        this.myPlayLists = new TreeSet<>();
        this.myArtists = new TreeMap<>();
    }

    public Player() {
        this("My Music Player");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { // allow for a name change to personalize music player.
        this.name = name;
    }

    protected List<Album> getMyAlbums() { // return a copy of the album list.
        return new ArrayList<>(myAlbums);
    }

    public Map<String, List<String>> getMyArtists() { // return a copy of the artist map
        return new TreeMap<>(myArtists);
    }

    public void createAlbum(String name, String artist) {

    }


    /*
     * The following String Array or String methods are to ensure that the UIPlayer does not have access to Album
     * or Song Objects, so the user only interacts with the Player method via String Objects. These are all used for
     * confirmation of the user input, and for display methods in the UIPlayer Class. I am trying to simulate a
     * situation where the UI Player Class is like a User Interface on the screen in an app or web app, where that
     * Interface has no access to the inner workings of the Album and Song classes.
     */


    public List<String[]> myAlbumInfo() { // a copy of the Album list for the UI Class.
        List<String[]> albums = new ArrayList<>();
        for (Album tempAlbum: getMyAlbums()) {
            String[] albumInfo = new String[]{tempAlbum.getAlbumName(), tempAlbum.getArtistName()};
            albums.add(albumInfo);
        }
        return albums;
    }

    public List<String[]> myAlbumInfoSorted() { // a sorted copy of he Album list for the UI Class.
        List<String[]> albums = new ArrayList<>();
        ArrayList<Album> myAlbums = new ArrayList<>(getMyAlbums());
        Collections.sort(myAlbums);
        for (Album tempAlbum: myAlbums) {
            String[] albumInfo = new String[]{tempAlbum.getAlbumName(), tempAlbum.getArtistName()};
            albums.add(albumInfo);
        }
        return albums;
    }

    public List<String> myPlayListNames() { // return a list of playlist names, not the playlist object
        List<String> playListNames = new ArrayList<>();
        for (PlayList playlist: myPlayLists) {
            playListNames.add(playlist.getPlayListName());
        }
        return playListNames;
    }

    public List<String> getPlayListSongNames(String playListName) {
        LinkedList<String> tempPlaylist = new LinkedList<>();
        for (PlayList playlist : myPlayLists) {
            if (playlist.getPlayListName().toLowerCase().equals(playListName.toLowerCase())) {
                for (Song song: playlist.getPlayList()) {
                    tempPlaylist.add(song.getTitle());
                }
            }
        }
        return tempPlaylist;
    }

    /*
     * The following methods, used in UIPlayer, are always used after findAlbum() checks if the album is not null, or
     * used with a method that called getMyAlbums() or getMyArtist()- thus the NullPointException should not be thrown.
     */

    public List<String[]> getAlbumSongListInfo(String albumName, String artist) { // a copy of the song list.
        return Objects.requireNonNull(getAlbum(albumName, artist), "System error.").getSongListInfo();
    }

    public List<String> getAlbumSongListSongNames(String albumName, String artist) { // a copy of the song list names.
        return new ArrayList<>(Objects.requireNonNull(getAlbum(albumName, artist), "System error.").
                getSongListSongNames());

    }
    // used to get exact names of album or artist because my findAlbum() is not case-sensitive.
    public String getAlbumName (String name) {
        return Objects.requireNonNull(getAlbum(name), "System error.").getAlbumName();
    }

    public String getArtistName (String name) {
        return Objects.requireNonNull(getAlbum(name), "System error.").getArtistName();
    }


//    public List<String> getAlbumSongListDurations (String albumName, String artist) {
////        return new ArrayList<>(Objects.requireNonNull(getAlbum(albumName, artist)).getSongDurations());
//        Album tempAlbum = getAlbum(albumName, artist);
//        if (tempAlbum != null)
//            return tempAlbum.getSongDurations();
//        else return null;
//    }

//    public List<Song> getAlbumSongList (String albumName, String artist) {
//        return Objects.requireNonNull(getAlbum(albumName, artist)).getSongs();
//    }


    /* Method for checking if an album has no songs- is not a null object because an empty list is initialized upon
       initialization of an album object. getAlbum is not null when this method is called in the UIPlayer class because
       a null check is performed before called isEmpty.
     */





    /*
     * Overloaded in case there are two albums with the same name, but with a different artist...
     *  It is used to start to play the album. Used with findAlbum() to search albums and return null if not found.
     */
    private Album getAlbum(String albumName, String artistName) {
        for (Album album : myAlbums) {
            if (album.getAlbumName().equalsIgnoreCase(albumName) && album.getArtistName().equalsIgnoreCase(artistName)){
                return album;
            }
        }
        return null;
    }

    private Album getAlbum(String name) { // get and find Album queries by album name, and not the object itself.
        for (Album album : myAlbums) {
            if (album.getAlbumName().equalsIgnoreCase(name)) {
                return album;
            }
        }
        return null;
    }

    private List<Song> getPlayList(String playListName) {
        for (PlayList playlist : myPlayLists) {
            if (playlist.getPlayListName().toLowerCase().equals(playListName.toLowerCase())) {
                return playlist.getPlayList();
            }
        }
        return null;
    }

    public boolean findAlbum(String name, String artist) { // Method to verify non-null object used in the UIPlayer.
        return getAlbum(name, artist) != null;
    }


    public boolean findAlbum(String name) { // Method to verify non-null object used in the UIPlayer. Calls getAlbum().
        return getAlbum(name) != null;
    }


//    protected boolean findArtist(String name) { // Method to search for available artists- to addAlbum new albums.
//        for (Map.Entry<String, List<String>> entry : myArtists.entrySet()) {
//            if (entry.getKey().equalsIgnoreCase(name)) {
//                return true;
//            }
//        }
//        return false;
//    }


    /*
     * Overloaded method for adding an album. Goes to adding the album by Album object. Also uses addToArtistMap method.
     */
    public boolean addAlbum(String album, String artist) {
        return addAlbum(new Album(album, artist));
    }

    public boolean addAlbum(Album newAlbum) { // Check to make sure the album object itself is not double added.
        for (Album album : myAlbums) {        // This method allows for double adding if the object is different.
            if (newAlbum.equals(album)) {     // Any additionally added albums will be just the object itself, but when
                return false;                 // that album is played, because the interface will display that all copies
                // of the first album have the same song tracks, because the array list just access the info for songs
                // in the first list. If you tried to play the album directly, which you can't because of encapsulation,
                // then you would have an empty object.
            }
        }
        addToArtistMap(newAlbum.getArtistName(), newAlbum.getAlbumName()); // see method below.
        return this.myAlbums.add(newAlbum);
    }

    private void addToArtistMap (String artist, String album) { // Used to keep track of artist and their albums.
        List<String> tempAlbumList = new ArrayList<>();         // Add an empty list to artist map if new entry,
        myArtists.putIfAbsent(artist, tempAlbumList);           // update the map if artist entry exists.
        myArtists.get(artist).add(album);
    }

    /*
     * Overloaded method for adding a song to an album. All go to adding a song by Album and Song object.
     */
    public boolean addSong(String albumName, String artistName, String songName, Double songDuration) {
        return addSong(getAlbum(albumName, artistName), new Song(songName, songDuration, artistName));
    }

    public boolean addSong(String albumName, String songName, Double songDuration) {
        return addSong(getAlbum(albumName), new Song(songName, songDuration));
    }

    public boolean addSong(String albumName, Song song) {
        return addSong(getAlbum(albumName), song);
    }

    public boolean addSong(Album album, Song song) {
        if (album != null) {
            return album.addSong(song);
        }
        return false;
    }

    public boolean listenAlbum(String albumName, String artistName) {
        Album listenAlbum = getAlbum(albumName, artistName);
        assert listenAlbum != null; // verified album exists in UIPlayer Class before this method is called.
        return play(listenAlbum.getAlbumName(), listenAlbum.getSongs());
    }

    public boolean listenPlayList(String name) {
        List<Song> playlist = getPlayList(name); // calls the private Album getPlayList method.
        assert playlist != null; // verified playlist exists in UIPlayer Class before this method is called.
        return play(name, playlist);

    }

    private void playSong (Song song) {
//        String songName; String artistName; double songLength; Song.MusicData songData;
//        songName = song.getTitle();
//        artistName = song.getArtist();
//        songLength = song.getDuration();
//        songData = song.getMusicData();
        String format = "%-14s%-50s%-15s%s%n";
        System.out.printf(format, "\nNow Playing- ", song.getTitle(), song.getArtist(), song.getDuration());
//        System.out.println("\nNow Playing- " + songName + ":\t" + artistName + "\t" + songLength);
        System.out.println("High Res LossLess: " + song.getMusicData().toString());

    }

    private boolean play(String name, List<Song> tracks) {
        Scanner scanner = new Scanner(System.in);
        String playInstructions = "Instructions for the Music Player:\n1- Back\t 2- Play From Start\t 3- Forward\t " +
                "4- Play track Again\n5- Show Tracks Now Playing\t 9- Show These Commands Again\t 0- Stop";
//        String playInstructions = "[<< ] 1- Back\t[> ] 2- Play From Start\t[ >>] 3- Forward\t" +
//                "[< ] 4- Play Current Track Again\t[x] 0- Stop\t 9- Show these Controls Again";
        String playControls = "[<< 1]\t[>  2]\t[3 >>]\t[<  4]\t[ x 0]";
        boolean quit = false;
        boolean goingForward = true;
        if (tracks.isEmpty()) {
            System.out.println("This album or playlist contains no songs.");
            return false;
        }
        System.out.println(playInstructions);
        System.out.println("\nPress 'Enter' to Continue.");
        scanner.nextLine();


        printNowListening(name, tracks);

        ListIterator<Song> listIterator = tracks.listIterator();
        playSong(listIterator.next());
//        System.out.println("\nNow Playing- " + listIterator.next().getMusicData());

        while (!quit) {
            System.out.println(playControls);
            System.out.print("\nEnter you action:  ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0:
                    System.out.println("\nQuitting your listening session.\n");
                    quit = true;
                    break;
                case 1:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        playSong(listIterator.previous());
//                        System.out.println("\nNow Playing- " + listIterator.previous());
                    } else {
                        System.out.println("We are at the start of the list");
                        goingForward = true;
                    }
                    break;
                case 2:
                    goingForward = true; // set true in case
                    listIterator = tracks.listIterator();
                    playSong(listIterator.next());
//                    System.out.println("\nNow Playing- " + listIterator.next());
                    break;

                case 3:
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {
                        playSong(listIterator.next());
//                        System.out.println("\nNow Playing- " + listIterator.next());
                    } else {
                        System.out.println("Reached the end of the list");
                        goingForward = false;
                    }
                    break;
                case 4:
                    if (!(listIterator.hasPrevious()) && (goingForward) || !(listIterator.hasNext()) && !(goingForward)) {
                        System.out.println("You are at the beginning or end of the list and cannot replay now");
                        break;
                    }
                    if (goingForward) {
                        playSong(listIterator.previous());
//                        System.out.println("\nNow Playing- " + listIterator.previous());
                        goingForward = false;
                    } else {
                        playSong(listIterator.next());
//                        System.out.println("\nNow Playing- " + listIterator.next());
                        goingForward = true;
                    }
                    break;
                case 5:
                    printNowListening(name, tracks);
                    break;
                case 9:
                    System.out.println(playInstructions);
                    break;
                default:
                    System.out.println("Invalid Input.");
            }
        }
        return true;
    }

    public void printNowListening(String name, List<Song> tracks) {
        System.out.println("Now Listening to: " + name);
        Iterator<Song> songIterator = tracks.iterator();
        System.out.println("*************************************************************************");
        int i = 1;
        while (songIterator.hasNext()) {
//            System.out.println("\t" + songIterator.next());
            Song song = songIterator.next();
            String format = "%-4s%-50s%-15s%s%n";
            System.out.printf(format, i +  ".", song.getTitle(), song.getArtist(), song.getDuration());
            i++;
        }
        System.out.println("*************************************************************************");
    }

    public boolean createPlayList(String name) {
        PlayList play = new PlayList();
        play.setPlayListName(name); // Here I use a setter, but could just set the name directly like above
        return myPlayLists.add(play);
    }

    public boolean updatePlayList(String songName, String playlistName, String albumName) {
        Album tempAlbum = getAlbum(albumName);
        for (PlayList list: myPlayLists) {
            if (list.getPlayListName().equalsIgnoreCase(playlistName)) {
                assert tempAlbum != null; // UIPlayer class checks if album is non-null before calling this method
                return list.addSongToPlayList(songName, tempAlbum);
            }
        }
        return false;
    }

    public boolean updatePlayList(int trackNumber, String playlistName, String albumName) {
        Album tempAlbum = getAlbum(albumName);
        for (PlayList list: myPlayLists) {
            if (list.getPlayListName().equalsIgnoreCase(playlistName)) {
                assert tempAlbum != null; // UIPlayer class checks if album is non-null before calling this method
                return list.addSongToPlayList(trackNumber, tempAlbum);
            }
        }
        return false;
    }

    public void setPlaylistName (String oldPlaylistName , String newPlaylistName) { //
        for (PlayList list: myPlayLists) {
            if (list.getPlayListName().equalsIgnoreCase(oldPlaylistName)) {
                list.setPlayListName(newPlaylistName);
            }
        }
    }


    /* Play List Inner Class Starts here

    // ******************************
    // Player Class contains three main methods:
    //  1. Add a Song to the playlist - verifies Album: calls album methods and calls its method 2
    //  2. Find a Song - verifies song: calls methods from album class
    //  3. Boolean method to check if string is an integer
    // ******************************
     *
     */
    private static class PlayList implements Comparable<PlayList> {
        private final List<Song> playList = new LinkedList<>();
        private String playListName;

        // sets name at creation and allows for changing the playlist name in UIPlayer
        private void setPlayListName(String playListName) {
            this.playListName = playListName;
        }

        // getters for name and list
        private String getPlayListName() {
            return playListName;
        }

        private List<Song> getPlayList() {
            return new LinkedList<>(playList);
        }

        private boolean addSongToPlayList(String songName, Album tempAlbum) {
             return tempAlbum.verifySongForPlayList(songName, this.playList);
        }

        private boolean addSongToPlayList(int trackNumber, Album tempAlbum) {
            return tempAlbum.verifySongForPlayList(trackNumber, this.playList);

        }

        public int compareTo(PlayList o) {
            return getPlayListName().compareTo(o.getPlayListName());
        }

        //        public String toString() {
//            return "PlayList{" +
//                    "myPlayList=" + playList +
//                    ", playListName='" + playListName + '\'' +
//                    '}';
//        }

    }

}




//        private void addSongToPlayList(Scanner scanner) {
//            if (addPlaylist.isEmpty()) {
//                for (Album album : myAlbums) {
//                    System.out.println(album.getAlbumName());
//                }
//                System.out.println("Please addAlbum a song to your addPlaylist. Choose an album from above. ");
//            } else {
//                System.out.println("Please enter the name of an album to addAlbum more songs: ");
//            }
//            String nameOfAlbum = scanner.nextLine();
//            Album tempAlbum = getAlbum(nameOfAlbum);
//
//            if (tempAlbum != null)
//                findSongsForPlayList(tempAlbum, scanner);
//            else {
//                System.out.println("The album was not found. Try your search again");
//                addSongToPlayList(scanner);
//            }
//        }

//        private void findSongsForPlayList(Album tempAlbum, Scanner scanner) {
//
//            System.out.print("Please choose a song from ");
//            tempAlbum.displayAlbumTrackNames();
//
//            if (tempAlbum.getSongs().isEmpty()) {
//                System.out.println("This Album has no available songs. Please select a different album\n");
//                addSongToPlayList(scanner);
//            }
//
//            System.out.println("Enter the name of a song or track number: ");
//            String songName = scanner.nextLine();
//
//            // isInteger method to allow adding by name or track number to avoid throwing an error
//            // Song is added to a playlist based on name (string) or track (int)
//            if (!(isInteger(songName))) {
//                if (tempAlbum.verifySongForPlayList(songName, this.addPlaylist))
//                    System.out.println("The song was added.\n");
//                else {
//                    System.out.println("The song was not found. Please select a song again.\n");
//                    findSongsForPlayList(tempAlbum, scanner);
//                }
//            } else {
//                int trackNumber = Integer.parseInt(songName);
//                if (tempAlbum.verifySongForPlayList(trackNumber, this.addPlaylist)) {
//                    System.out.println("The song was added.\n");
//                }
//                else {
//                    System.out.println("The track number was not found. Please select a track again.\n");
//                    findSongsForPlayList(tempAlbum, scanner);
//                }
//            }
//            if (addPlaylist.size() == 1) {
//                System.out.println("Your " + playListName + " addPlaylist was created.");
//            }
//            else {
//                System.out.println("Your " + playListName + " addPlaylist was updated.");
//            }
//            System.out.println("Would you like to addAlbum another song? Yes or No");
//            String selection = scanner.nextLine();
//            if (selection.equalsIgnoreCase("yes")) {
//                addSongToPlayList(scanner);
//            } else if (!selection.equalsIgnoreCase("no")) {
//                System.out.println("Sorry, your selection wasn't valid. I will interpret youra nswer as no");
//            }
////            menuPlayList(addPlaylist);
//
//        }

        // Method to check check if integer was inputted or not- modified from StackOverflow find
//        public boolean isInteger(String str) {
//            if (str == null) {
//                return false;
//            }
//            int length = str.length();
//            if (length == 0) {
//                return false;
//            }
//            int i = 0;
//            if (str.charAt(i) == '-') {
//                System.out.println("Error. Please enter only valid track numbers.");
//                return false;
//            }
//            for (; i < length; i++) {
//                char c = str.charAt(i);
//                if (c < '0' || c > '9') {
//                    System.out.println("Error. Please enter only the track number or the name of the song.");
//                    return false;
//                }
//            }
//            return true;
//        }



/* This Code is a player for a playlist only: here as back-up for now

        public void printMenu() {
            System.out.println("\nWelcome to " + playListName + " Playlist. Please make a selection: ");
            System.out.println("0 - to quit\n" +
                    "1 - to play/to go back to the beginning\n" +
                    "2 - to skip back\n" +
                    "3 - to skip forward\n" +
                    "4 - to replay current song\n" +
                    "5 - to addAlbum more songs\n" +
                    "6 - to remove a song\n" +
                    "7 - to see your addPlaylist and see these selections again\n" +
                    "8 - to see your albums\n" +
                    "9 - to go back to the player menu\n" +
                    "10 - Play the addPlaylist using a different way");
        }
 */




/* The code below could be used in a later interface*/

//    public void addMultipleAlbums() {
//        System.out.println("\nYou can addAlbum some albums to your player.");
//        addAlbum();
//
//        Scanner scanner = new Scanner(System.in);
//        boolean quit = true;
//        while (quit) {
//            System.out.println("Would you like to addAlbum another album? Type 1 for Yes/Type 2 for No.");
//            int q = scanner.nextInt();
//            scanner.nextLine();
//            if (q == 2) quit = false;
//            else addAlbum();
//        }
//
//    }

//    public void addAlbum() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter the name of the artist: ");
//        String artist = scanner.nextLine();
//        System.out.println("Enter the name of the album: ");
//        String name = scanner.nextLine();
//
//        Album newAlbum = new Album(name, artist);
//
//        if (!findAlbum(name)) {
//            System.out.println("How many songs do you want to addAlbum?");
//            int songs = scanner.nextInt();
//            scanner.nextLine();
//
//            for (int i = 0; i < songs; i++) {
//                System.out.print("Enter the song name: ");
//                String song = scanner.nextLine();
//                System.out.print("Enter the time of the song: ");
//                double time = scanner.nextDouble();
//                scanner.nextLine();
//                if (newAlbum.addSong(song, time))
//                    System.out.println("The song " + song +  " was added.");
//                else System.out.println("The song " + song + " wasn't added.");
//            }
//            addAlbum(newAlbum);
//        }
//        else System.out.println("This album already exists in your player.");
//
//    }
