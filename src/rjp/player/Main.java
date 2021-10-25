package rjp.player;
import rjp.playerUI.UIPlayer;


public class Main {
    public static Player myPlayer = new Player();

    public static void main(String[] args) {
//        UIPlayer play = new UIPlayer(myPlayer);
//        play.menu();
//        System.exit(0);

    // Adding a player directly by accessing the methods of myPlayer
//        Player myPlayer = new Player();
//        Album boston = new Album("Boston", "Lifetime");
//        System.out.println(boston.getAlbumName());
//        System.out.println(boston.getSongs());
//        Song feel = new Song("More than a Feeling", 6.34);
//        Song feels = new Song("More than a Feeling", 6.37);
////        feels = feel;
//        System.out.println(boston);
//        System.out.println(feel);
//        boston.addSong(feel);
//        boston.addSong(feels);
//        boston.addSong("More than a Feeling", 6.34);
//        System.out.println(boston.getSongs());
//        System.out.println(myPlayer.addAlbum(boston));
//        System.out.println(myPlayer.addAlbum(boston));
//        Album bo = new Album("Boston", "Lifetime");
//        System.out.println(myPlayer.addAlbum(bo));



        // Cannot instantiate a private inner class object
//        Album.SongList bostonSongs = boston.new SongList();

//        feel = null;
//        boston.addSong(feel);
//        System.out.println(boston);
//        System.out.println(boston.getSongs());


        // I WANT TO ENCAPSULATE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        myPlayer.addAlbum("Pop Mart Live", "U2");

        myPlayer.getMyAlbums().get(0).addSong("Last Night on Earth", 5.24);
        myPlayer.getMyAlbums().get(0).addSong("Where the Streets Have no Name", 7.34);
        myPlayer.getMyAlbums().get(0).addSong("With or Without you", 6.37);
        myPlayer.getMyAlbums().get(0).addSong("Please", 7.56);
        System.out.println(myPlayer.getMyAlbums().get(0).addSong("Please", 7.56));
//        System.exit(0);


        Album u2 = new Album ("War", "U2");

        /*Album nullTest = new Album(null, null);
        myPlayer.addAlbum(new Album("",""));
        myPlayer.addAlbum(new Album(null, null));
        myPlayer.addAlbum(nullTest);
        myPlayer.getAlbumSongListInfo(null, null);

        System.exit(4);*/

        /* the code below will return true, but War will not be added because of encapsulation
        System.out.println(myPlayer.getMyAlbums().addAlbum(u2));
        myPlayer.myDownloadedAlbums();*/
        u2.addSong("Sunday Bloody Sunday", 7.55);
        u2.addSong("New Year's Day", 5.39);


        // Directly access a method here just to remove error message by IntelliJ
        System.out.println(myPlayer.addSong("War", new Song("11 O'clock Tick Tock", 3.45)));
        System.out.println(myPlayer.addSong("War", new Song("11 O'clock Tick Tock", 3.45)));
        Song u2Song = new Song ("Gloria", 8.56);
        System.out.println(u2.addSong(u2Song));
        System.out.println(u2.addSong(u2Song));
        u2Song = new Song("Two Hearts", 2.22, "U2");
        System.out.println(u2.addSong(u2Song));
        myPlayer.addAlbum(u2);

//        System.exit(0);

        u2 = new Album("Achtung Baby!", "U2");
//        u2.albumName;
        Song song = new Song("Zoo Station", 4.36);
        u2.addSong(song);
        song = new Song("Even Better Than the Real Thing", 3.41);
        u2.addSong(song);
        song = new Song("One", 4.36);
        u2.addSong(song);
        song = new Song("Until the End of the World", 4.39);
        u2.addSong(song);
        song = new Song("Who's Gonna Ride Your Wild Horses", 5.16);
        u2.addSong(song);
        song = new Song("So Cruel", 5.49);
        u2.addSong(song);
        song = new Song("The Fly", 4.29);
        u2.addSong(song);
        song = new Song("Mysterious Ways", 4.04);
        u2.addSong(song);
        song = new Song("Tryin' to Throw Tour Arms Around the World", 3.53);
        u2.addSong(song);
        song = new Song("Ultraviolet (Light my Way)", 5.31);
        u2.addSong(song);
        song = new Song("Acrobat", 4.30);
        u2.addSong(song);
        song = new Song("Love is Blindness", 4.23);
        u2.addSong(song);
        myPlayer.addAlbum(u2);

        /* There is no validation for adding a song object directly: Assume it is new song
        !!!! No wait, I think I added validation by checking for a null value before adding !!!!!
        System.out.println(u2.addSong(new Song("Even Better than the Real Thing", 4.58)));*/

        /* Maybe keep option to addAlbum an album with the same name but different artist name
        Album test = new Album("War", "Hutwo");
        System.out.println(myPlayer.addAlbum(test));
        System.out.println(test.addSong(new Song("Hohoho", 8.8)));
        System.out.println("GGGGGGGGGGG");*/

        /*Test Passed: Only the first addSong passes!!!!!!
        System.out.println(myPlayer.addSong("War", new Song("I Will Follow", 3.33)));
        System.out.println(myPlayer.addSong("War", new Song("I will follow", 3.99)));
        System.out.println(myPlayer.addSong(u2, new Song("The Fly", 7.25)));
        System.out.println(myPlayer.addSong("GWar", new Song("I will follow", 3.99)));*/


        /* Test for encapsulation: Cannot access the song list directly because it has private access!!!!
        Album.SongList songlist = new Album.SongList();
        songlist.addAlbum(new Song("Mysterious Ways", 4));
        songlist.addAlbum(new Song("Light my Way", 4.45));
        System.out.println(songlist.getAlbumSongList());*/

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        System.out.println(album.addSong("Soldier of fortune", 3.13));
        System.out.println(album.addSong("Soldier of fortune", 3.13));
        System.out.println(album.addSong("Soldier of fortune", 3.13989899));

        Song test = new Song("FADE ", 9.9);
        System.out.println(album.addSong(test));
        System.out.println(album.addSong(test));

        myPlayer.addAlbum(album);
//        System.exit(0);

        /* Test passed: Returns False: Cannot addAlbum a second object
        System.out.println(myPlayer.addAlbum(album));*/



        album = new Album("For Those About to Rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        myPlayer.addAlbum(album);

        album = new Album("For Those About t Rock", "UA");
        myPlayer.addAlbum(album);
        myPlayer.addAlbum(album);

        album = new Album("For Tho2bout t Rock", "U2");
        myPlayer.addAlbum(album);


        /* Again, No, cannot addAlbum by using the list addAlbum method because of encapsulation i.e. getMethod
        returns a hard copy of the list, but returns true, even though it doesn't addAlbum to Players album list
        System.out.println(myPlayer.getMyAlbums().addAlbum(new Album("Back in Black", "AC/DC")));*/

        myPlayer.addAlbum(new Album("Back in Black", "AC/DC"));
        myPlayer.addAlbum(new Album("Back in Black", "AC/DC"));
        myPlayer.addAlbum("Back in Black", "AC/DC");

////        System.out.println(Arrays.toString(myPlayer.myAlbumTest()));
//        System.out.println(myPlayer.myAlbumInfo());
//        for (String[] string: myPlayer.myAlbumInfo()) {
//            System.out.println(Arrays.toString(string));
//        }



        System.out.println(myPlayer);
        System.out.println(u2.getClass());
        System.exit(0);

        UIPlayer player = new UIPlayer(myPlayer);

        player.menu();

        System.exit(0);

        player.menu();


//        myPlayer.createPlayList();

//        System.out.println(myPlayer.myPlayLists.toString());
//
//        System.out.println(myPlayer.myPlayLists.toString());

//        myPlayer.addMultipleAlbums();
//        myPlayer.addAlbum();

        /* Below are some test code*/


//        myPlayer.addSong("Achtung Baby!", new Song("Mysterious Ways", 4.59));

        /*System.out.println("Not here Not here");*/
//        myPlayer.addSong("Blahton", new Song("Boo", 2));

        /*System.out.println("Could you addAlbum me? ?????");
        myPlayer.addAlbum("Stormbringer", "Deep Purple Live");*/

//        myPlayer.myDownloadedAlbums();

        /*myPlayer.getMyAlbums().get(2).displayAlbumTrackNames();
        System.out.println(myPlayer.getMyAlbums().get(2).getAlbumSongList().get(0));*/

    }

}
