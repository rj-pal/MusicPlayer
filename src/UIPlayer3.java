import rjp.player.Player;
import rjp.playerUI.UIPlayer;

import java.util.*;

public class UIPlayer3 {

    public Scanner scanner = new Scanner(System.in);
    public Player myPlayer;
    String playerName;
    public List<String[]> myAlbums;
    public List<String> myPlaylists;
//    public Map<String, List<String>> myArtists;

    // Strings to avoid repetition.
    String menu = "Returning to the main menu.\n", enter = "Press Enter to continue", createError = "The album was " +
            "successfully created, but no songs were added. Please select the album from the main menu and add songs " +
            "individually.\n" + menu;


    public UIPlayer3 (Player myPlayer) {
        this.myPlayer = myPlayer;
        this.playerName = myPlayer.getName();

        this.myAlbums = myPlayer.myAlbumInfo();
        this.myPlaylists = myPlayer.myPlayListNames();
//        this.myArtists = myPlayer.getMyArtists();

    }

    public void printMenu() {
        String titleFormat = "%45s%s%n";
        String title = "***Welcome to " + playerName + "!***";
        System.out.printf(titleFormat, "", title);

        System.out.println("1. Listen Now");

        String format = "%-30s%-10s%-30s%-10s%-50s%s%n";

        System.out.printf(format, "2. Recently Added Albums", "|", "7. Add an Album", "|", "11. Add an Album with " +
                "multiple Songs ", "|");
        System.out.printf(format, "3. Artists", "|", "8. Add a Song", "|", "12. Change the name of your music " +
                "player ", "|");
        System.out.printf(format, "4. Albums", "|", "9. Create a Playlist", "|", "13. Change the name of a " +
                "playlist", "|");
        System.out.printf(format, "5. Songs", "|", "10. Update a Playlist", "|", "14. Quit", "|");
        System.out.printf(format, "6. Playlists", "", "", "", "", "");

    }

    public void menu() {
        boolean quit = false;
        printMenu();
        while (!quit) {
            try {
                System.out.print("\nPlease make your selection: ");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 0:
                    case 14: {
                        System.out.println("Quitting " + playerName + ". Thank you for listening.");
                        quit = true;
                        break;
                    } case 1: {
                        listen(scanner);
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 2: {
                        displayRecent();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 3: {
                        displayArtists();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 4: {
                        displayAlbums();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 5: {
                        displaySongs();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 6: {
                        displayPlaylists();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 7: {
                        createAlbum(scanner);
                        this.myAlbums = myPlayer.myAlbumInfo();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 8: {
                        addSongToAlbum(scanner);
                        this.myAlbums = myPlayer.myAlbumInfo();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 9: {
                        addPlaylist(scanner); //Method updatePlaylist used to add song after creating playlist
                        this.myPlaylists = myPlayer.myPlayListNames();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 10: { //Method updatePlaylist used to add song after selecting playlist
                        int actionCode = 1; // actionCode 1 needed to move on to updatePlaylist method
                        getPlaylistName(scanner, actionCode);
                        this.myPlaylists = myPlayer.myPlayListNames();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 11: {
                        createAlbumWithMultipleSongs(scanner);
                        this.myAlbums = myPlayer.myAlbumInfo();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 12: {
                        namePlayer(scanner);
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } case 13: {
                        int actionCode = 0; //actionCode 0 needed to update the name of playlist only
                        getPlaylistName(scanner, actionCode);
                        this.myPlaylists = myPlayer.myPlayListNames();
                        System.out.println(enter);
                        scanner.nextLine();
                        printMenu();
                        break;
                    } default:
                        System.out.println("Please only enter a valid choice.");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Error: Please only enter a valid choice.");
//                System.out.println("An error occurred and the player is shutting down.");
                scanner.nextLine();
            }

        }

    }

    public void listen (Scanner scanner) {
        while (true) {
            System.out.println("Enter 1 to listen to an album or 2 to listen to a playlist: ");
            int selection;
            try {
                selection = scanner.nextInt();
                scanner.nextLine();
                if (selection == 1) {
                    displayAlbumsShort();
                    String[] albumDetails = getAlbumArtistFromUser(scanner);
                    if (myPlayer.findAlbum(albumDetails[0], albumDetails[1])) {
                        myPlayer.listenAlbum(albumDetails[0], albumDetails[1]);
                    } else {
                        System.out.println("An error occurred. The album could not be found.\n");
                    }
                    System.out.println(menu);
                    return;
                } else if (selection ==2) {
                    getPlaylistName(scanner, 2);
                    return;
                } else {
                    System.out.println("Invalid input. Please select again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Please select again.");
                scanner.nextLine();
            }
        }
    }

    /*
     * There are nine methods used to display Albums, Songs (with or without duration), PlayLists, Tracks, etc.
     */
    public void displayTracks(List<String[]> songList) {
        if (songList.isEmpty()) {
            System.out.println("No Tracks\n");
        } else {
            int j = 1;
            StringBuilder tracks = new StringBuilder("Tracks\n");
            for (String[] song : songList) {
                String trackName = j + ". " + song[0];
                String trackDuration = song[1];
                tracks.append(String.format("%-50s%s%n", trackName, trackDuration));
                j++;
            }
            System.out.println(tracks);
        }
    }

    public void displayTracksShort(List<String> songList) {
        if (!songList.isEmpty()) {
            int j = 1;
            for (String song : songList) {
                System.out.print(j + ". " + song + "\t\t");
                if (j % 5 == 0) System.out.println();
                j++;
            }
            System.out.println();
        } else {
            System.out.println("No Tracks. This playlist is empty");
        }
    }

    public void displayTracksShortWithAlbumArtistInfo(String albumName, String artistName) {
        System.out.print(albumName + " by " + artistName);
        List<String> songList = myPlayer.getAlbumSongListSongNames(albumName, artistName);
        if (!(songList.isEmpty())) {
            if (songList.size() == 1) {
                System.out.print(" with " + songList.size() +  " song: \n");
            } else {
                System.out.print(" with " + songList.size() +  " songs: \n");
            }
            displayTracksShort(songList);
        }
        else {System.out.print(" with no songs.\n");}
        System.out.println();

    }

    public void displayRecent() {
        System.out.println("Recently Added Albums for " + playerName + ":\n");
        List<String[]> albumList = myAlbums;
        for (int i = albumList.size()-1; i >= 0; i--) { //Goes through array list in reverse
            String[] tempAlbum = albumList.get(i);
            String albumName = tempAlbum[0]; String artistName = tempAlbum[1];
            System.out.println("<" + albumName + "> : " + artistName);
            displayTracks(myPlayer.getAlbumSongListInfo(albumName, artistName));
        }
    }

    public void displayAlbums()  {
        System.out.println("Albums for " + playerName + ":\n");
        List<String[]> mySortedAlbums = myPlayer.myAlbumInfoSorted();
        for (String[] album : mySortedAlbums) {
            String albumName = album[0]; String artistName = album[1];
            System.out.println("<" + albumName + "> : " + artistName);
            displayTracks(myPlayer.getAlbumSongListInfo(albumName, artistName));
        }
    }

    public void displayAlbumsShort() {
        int j =0;
        String format = "%-35s";
//        String format = "%-35s%-10s";

        for (String[] album: myAlbums) {
            System.out.printf(format, album[0] + " by " + album[1]);
            j ++;
            if (j%4==0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public void displayArtists() {
        System.out.println("Albums by Artist for " + playerName + ":\n");
        for (Map.Entry<String, List<String>> entry : myPlayer.getMyArtists().entrySet()) {
            String artistName = entry.getKey();
            System.out.println("<" + artistName + ">");
            List<String> sortedAlbumsForArtist = entry.getValue();
            Collections.sort(sortedAlbumsForArtist);
            for (String albumName : sortedAlbumsForArtist) {
                displayTracksShortWithAlbumArtistInfo(albumName, artistName);
            }
        }
    }

    public void displaySongs() {
        System.out.println("Songs for " + playerName + ":\n");
        String format = "%-48s%-50s%s%n";
        System.out.printf(format, "Song Title:", "Album:", "Artist:\n");
        for (String[] albums: myPlayer.myAlbumInfoSorted()) {
            String albumName = albums[0];
            String artistName = albums[1];
            for (String songName: myPlayer.getAlbumSongListSongNames(albumName, artistName)) {
                System.out.printf(format, songName, albumName, artistName);
            }
        }
        System.out.println();
    }

    public void displayPlaylists() {
        List<String> playlists = myPlaylists;
        if (playlists.isEmpty()) {
            System.out.println("There are no available playlists.\n");
            return;
        }
        System.out.println("Playlists for " + playerName +":\n");
        for (String playlistName: playlists) {
            System.out.println(playlistName);
            displayTracksShort(myPlayer.getPlayListSongNames(playlistName));
            System.out.println();
        }
    }

    public boolean validateString(String string) {
        if (string.length() > 47) {
            System.out.println("You exceeded the maximum characters allowed. Try again.\n");
            return false;
        } else if (string.isEmpty()) {
            System.out.println("You must include at lease one character. Try again.\n");
            return false;
        }
        return true;
    }

    public Object[] getSongFromUser(Scanner scanner) { //There is no validation to check if a song is double added.
        while (true) {
            String songName;
            double songDuration;
            System.out.println("Enter the name of the song: ");
            songName = scanner.nextLine().trim();
            if (!validateString(songName)) {
                continue;
            }
            System.out.println("Enter the duration of the song: ");
            try {
                songDuration = scanner.nextDouble();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid duration value was entered. Please try again.\n");
                scanner.nextLine();
                continue;
            }
            return new Object[]{songName, songDuration};
        }
    }

    public String[] getAlbumArtistFromUser(Scanner scanner) {
        while (true) {
            String artistName, albumName;
            System.out.println("Enter the name of the album: ");
            albumName = scanner.nextLine().trim();
            if (!validateString(albumName)) {
                continue;
            }
            System.out.println("Enter the name of the artist: ");
            artistName = scanner.nextLine().trim();
            if (!validateString(artistName)) {
                System.out.print("Please reenter the name of the artist and album.\n");
                continue;
            }
            return new String[]{albumName, artistName};
        }
    }

    public String[] verifyAlbum (Scanner scanner) {

        String[] albumDetails = getAlbumArtistFromUser(scanner);
        String albumName = albumDetails[0], artistName = albumDetails[1];
        if (myPlayer.findAlbum(albumName, artistName)) {
            System.out.println(albumName + " by " + artistName + " is already in your Music Player. " +
                    "Adding this album again may cause some errors to occur.\n");
        } else if (myPlayer.findAlbum(albumName)) {
            System.out.println("An album with the same name by a different artist already exists.\n");
        } else {
            return new String[]{albumName, artistName};
        }
        while (true) {
            System.out.println("Are you sure you would like to continue to add this album to your library? (Y or N)?");
            String selection = scanner.nextLine().trim();
            if (selection.equalsIgnoreCase("Y") || selection.equalsIgnoreCase("yes")) {
                System.out.println("Proceeding with the updating process");
                return new String[]{albumName, artistName};
            } else if (selection.equalsIgnoreCase("N") || selection.equalsIgnoreCase("no")) {
                System.out.println("The album was not added to " + playerName + ".\n");
                return null;
            } else {
                System.out.println("Invalid input. An error occurred. Please enter Y or N only.");
                System.out.println(enter);
                scanner.nextLine();
            }
        }
    }

    public void addSongToAlbum(Scanner scanner) {
        Object[] songDetails = getSongFromUser(scanner);
        String songName = (String) songDetails[0];
        double songDuration = (double) songDetails[1];
        String albumName, artistName;
        while (true) {
            System.out.println("Select the album you wish to add the song to:");
            String[] albumDetails = getAlbumArtistFromUser(scanner);
            albumName = albumDetails[0];
            artistName = albumDetails[1];
            if (myPlayer.findAlbum(albumName, artistName)) {
                break;
            } else {
                System.out.println("The album could not be found. Please try again.\n");
            }
        }
        // Uses getAlbum in Player class, so if there are two albums with the same name, adds to first on the list.
        if (myPlayer.addSong(albumName, artistName, songName, songDuration)) {
            System.out.println("The song was added to " + albumName + ".\n");
            System.out.println(playerName + " was updated.");
        } else {
            System.out.println("The song could not be added.\n");
            System.out.println(playerName + " was not updated.");
        }
    }

    public void createAlbum (Scanner scanner) {
        String[] albumDetails = verifyAlbum(scanner);
        if (albumDetails == null) {
            System.out.println(playerName + " was not updated. " + menu);
            return;
        }
        myPlayer.addAlbum(albumDetails[0], albumDetails[1]);
        System.out.println(playerName + " was updated. " + menu);
    }

    public void createAlbumWithMultipleSongs(Scanner scanner) {
        String[] albumDetails = verifyAlbum(scanner);
        if (albumDetails == null) {
            System.out.println(playerName + " was not updated." + menu);
            return;
        }
        String albumName = albumDetails[0], artistName = albumDetails[1];
        if (myPlayer.addAlbum(albumName, artistName)) {
            System.out.println("How many songs do you want to add to your album?");
            int songNumber = scanner.nextInt();
            try { // If an invalid number is entered, the method will return to main menu.
                if (songNumber > 50) {
                    System.out.println("There is a limit of 50 songs. " + createError);
                    return;
                } else if (songNumber <= 0) {
                    System.out.println("Error. You must add a valid number of songs. " + createError);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. " + createError);
                return;
            }
            scanner.nextLine();
            for (int i = 0; i < songNumber; i++) {
                Object[] tempSong = getSongFromUser(scanner);
                if (myPlayer.addSong(albumDetails[0], (String) tempSong[0], (double) tempSong[1])) {
                    System.out.println("Song " + (i+1) + " of " + songNumber + " was added.");
                } else {
                    System.out.println("The song wasn't added. Try again");
                    i--;
                }
            }
            StringBuilder added = new StringBuilder("The Album " + albumName + " by " + artistName +
                    " was created and ");
            if (songNumber == 1) {
                added.append(songNumber).append(" was added. Update complete.");
            } else {
                added.append(songNumber).append(" were added. Update complete.");
            }
            System.out.println(added);
        } else System.out.println(playerName + " was not updated.");
        System.out.println(menu);

    }

    public void namePlayer(Scanner scanner) {
        String playerName;
        while (true) {
            System.out.println("Enter a new name for your music player: ");
            playerName = scanner.nextLine().trim();
            if (!validateString(playerName)) {
                continue;
            }
            break;
        }
        myPlayer.setName(playerName);
        System.out.println(this.playerName + " was changed to " + playerName + ".");
        this.playerName = playerName;
    }

    public void addPlaylist(Scanner scanner){
        System.out.println("Enter the name of the playlist: ");
        String playlistName = scanner.nextLine().trim();
        if (myPlayer.createPlayList(playlistName)) {
            System.out.println("Your new playlist was created. Please add a song now.\n");
            updatePlaylist(scanner, playlistName);
        } else {
            System.out.println("An error occurred. Make sure the playlist name is unique and not duplicated.\n" + menu);
        }
    }

    public void getPlaylistName(Scanner scanner, int actionCode) { // method used to select a playlist for adding songs
        // and for changing the name of the player.
        while (true) {
            displayPlaylists();
            List<String> playlists = myPlaylists;
            if (playlists.isEmpty()) {
                System.out.println("Please create a playlist first before listening or updating.\n" + menu);
                return;
            }
            System.out.println("Please select a playlist from your playlists above: ");
            String playListName = scanner.nextLine().trim();
            if (playlists.contains(playListName)) { // playlist validation for updating existing playlist object
                // actionCode 0 is for renaming the playlist.
                // actionCode 1 is for adding a song to the playlist.
                // actionCode 2 is for listening to the playlist.
                if (actionCode == 0) { // Only passed from menu() method case 11.
                    System.out.println("Please enter the new name for your playlist");
                    String newPlaylistName = scanner.nextLine().trim();
                    myPlayer.setPlaylistName(playListName, newPlaylistName);
                    System.out.println(playListName + " was changed to " + newPlaylistName + ".\n" + menu);
                    break;
                }
                else if (actionCode == 1) { // Only passed from menu() method case 9.
                    updatePlaylist(scanner, playListName);
                    break;
                }
                else if (actionCode == 2) { // Only passed from listen() method which is from menu() case 1.
                    if (myPlayer.listenPlayList(playListName)) {
                        System.out.println(menu);
                    } else {
                        System.out.println("An error occurred. The playlist could not be played.\n" + menu);
                    }
                    return;
//                    if (myPlayer.listenPlayList(playListName) == null) {
//                        System.out.println("An error occurred. The playlist could not be played.\n" + menu);
//                    } else {
//                        play(playListName, myPlayer.listenPlayList(playListName));
//                    }
//                    return;
                } // application shutdown in case actionCode is changed.
                else {
                    System.out.println("A System error occurred. Quitting application now.");
                    System.exit(1);
                }
            } else {
                System.out.println("\nInvalid playlist. Make sure spelling and capitalizing are exact.\n\n" + enter);
                scanner.nextLine();
            }
        }


    }

//    public void search(Scanner scanner) {
//        System.out.println("Enter the name of the Artist or Album you wish to find: ");
//        String selection = scanner.nextLine();
//
//
//    }

    public void updatePlaylist(Scanner scanner, String playListName) {
        boolean quit = true;
        do {
            boolean selectAgain = false;

            while (true) {
                System.out.println("Please add a song to your playlist.\nIf you'd like to quit now, please type " +
                        "'Quit' to return to the main menu, or press 'Enter' to continue: ");
                String selection = scanner.nextLine().trim();
                if (selection.equalsIgnoreCase("quit")) {
                    System.out.println("\nThe playlist was not updated.\n" + menu);
                    return;
                } else if (selection.isEmpty()) {
                    System.out.println();
                    break;
                } else {
                    System.out.println("\nInvalid input. Please try again.\n");
                }
            }
            System.out.println("Please select an album and artist from the list below.\n");

            displayAlbumsShort();


            String[] albumDetails =getAlbumArtistFromUser(scanner);
            String  nameOfAlbum = albumDetails[0], nameOfArtist = albumDetails[1];

            if (myPlayer.findAlbum(nameOfAlbum, nameOfArtist)) {
                String albumName = myPlayer.getAlbumName(nameOfAlbum); // get the exact names because all searches
                String artistName = myPlayer.getArtistName(nameOfAlbum); // ignore the case.
                if (myPlayer.getAlbumSongListInfo(albumName, artistName).isEmpty()) {
                    System.out.println("This Album has no available songs. Please select a different album.\n");
                    System.out.println(enter);
                    scanner.nextLine();
                    quit = false;
                    continue;
                }
                boolean stop = false;
                while (!stop) {
                    displayTracksShortWithAlbumArtistInfo(albumName, artistName);
                    System.out.println("Please choose a song from the album above by entering the name or " +
                            "track number.");
                    System.out.print("(Type 'Select Again' to select a different album): ");
                    String songSelection = scanner.nextLine().trim();
                    if (songSelection.equalsIgnoreCase("select again")) {
                        stop = true;
                        selectAgain = true;
                        continue;
                    }
                    if (isInteger(songSelection)) {  // isInteger method to allow adding by name (string)
                        // or track number (int) to avoid throwing an error.
                        int trackNumber = Integer.parseInt(songSelection);
                        if (myPlayer.updatePlayList(trackNumber, playListName, albumName)) {
                            System.out.println("\nThe song was added.\n");
                            stop = true;
                        } else {
                            System.out.println("\nThe track number was not found. Please select a track again.\n");
                        }
                    } else {
                        if (myPlayer.updatePlayList(songSelection, playListName, albumName)) {
                            System.out.println("\nThe song was added.\n");
                            stop = true;
                        } else {
                            System.out.println("\nThe song was not found. Please select a song again.\n");
                        }
                    }
                }
            } else {
                System.out.println("\nAn error occurred. The album was not found. The playlist was not updated.\n" +
                        "Please try again to add a song from an available album.\n");
                System.out.println(enter);
                scanner.nextLine();
                quit = false;
                continue;
            }

            if (selectAgain) {
                System.out.println("\nPlease select a different album.\n");
                quit = false;
                continue;
            }

            boolean stop = false;
            while (!stop) {
                System.out.println("Would you like to add another song? (Yes or No)");
                String selection = scanner.nextLine().trim();
                if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")) {
                    System.out.println("Your playlist was updated. Returning to the album menu.");
                    quit = false;
                    stop = true;
                } else if (selection.equalsIgnoreCase("no") || selection.equalsIgnoreCase("n")) {
                    System.out.println("Your playlist was updated.\n" + menu);
                    quit = true;
                    stop = true;
                } else {
                    System.out.println("Invalid selection. Try again.\n");
                }
            }
        } while (!quit);
    }

    public boolean isInteger(String str) { // slightly modified method found on StackOverflow.
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(i) == '-') {
            System.out.println("Error. Please enter only valid track numbers.");
            return false;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Player test = new Player();
        UIPlayer3 testPlayer = new UIPlayer3(test);

        test. addAlbum("hot", "Snakes");
        List<String[]> albums = test.myAlbumInfo();
        System.out.println(Arrays.toString(albums.get(0)));


        UIPlayer3 player = new UIPlayer3(new Player("JoJo"));
        System.out.println(player);
//        player.menu();

    }



}
