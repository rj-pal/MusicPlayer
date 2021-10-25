package rjp.player;

class Song {
    private final String title;
    private final double duration;
    private String artist = "N/A";
    private final MusicData song;

    Song(String title, double duration, double songData) {
        this.title = title;
        this.duration = duration;
        this.song = new MusicData(songData);
    }

    Song(String title, double duration, String artist) {
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.song = new MusicData();
    }

    Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
        this.song = new MusicData();
    }

    String getTitle() {
        return title;
    }

    String getArtist() {
        return artist;
    }

    double getDuration() {
        return duration;
    }

    MusicData getMusicData() {
        return this.song;
    }

    String[] getSong() {
        return new String[]{getTitle(), Double.toString(getDuration())};
    }
    // the toString method used ONLY in the Player class play() method to display info to the users while listening.

    public String toString() {
        return title + ": " + getDuration();
    }

    static final class MusicData {
        private final double data;

        MusicData() {
            this.data = Math.random();
        }

        MusicData(double songData) {
            this.data = songData;
        }

        public String toString() {
            return "MusicData{" +
                    "data=" + data +
                    '}';
        }
    }

}
